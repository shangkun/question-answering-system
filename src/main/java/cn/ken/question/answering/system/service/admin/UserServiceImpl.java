package cn.ken.question.answering.system.service.admin;

import cn.ken.question.answering.system.mapper.admin.UserMapper;
import cn.ken.question.answering.system.memorydb.Admin;
import cn.ken.question.answering.system.model.admin.UserRequest;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.utils.*;
import cn.ken.question.answering.system.common.Constant;
import cn.ken.question.answering.system.model.admin.Role;
import cn.ken.question.answering.system.model.admin.User;
import cn.ken.question.answering.system.model.admin.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 用户服务实现类 <br/>
 */
@Service
public class UserServiceImpl extends Base implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public String add(User user) {
        String result = assemble(user);
        if(result!=null){
            return result;
        }
        userMapper.insert(user);
        Admin.userMap.put(user.getId(),user);
        return null;
    }

    @Override
    public boolean isRepeat(User user) {
        if(StringUtils.isEmpty(user.getId()) && !StringUtils.isEmpty(user.getAccount()) && userMapper.countByAccount(user.getAccount())>0){
            return true;
        }
        if(!StringUtils.isEmpty(user.getId()) && !StringUtils.isEmpty(user.getAccount()) && userMapper.countByIdAndAccount(user.getId(), user.getAccount())>0){
            return true;
        }
        return false;
    }

    /**
     * 数据组装
     * @param user
     * @return
     */
    public String assemble(User user){
        if(!StringUtils.lengthCheck(user.getAccount(),5,100)){
            return "账号长度不能小于5或大于100";
        }
        if(!StringUtils.lengthCheck(user.getName(),1,100)){
            return "用户名长度不能小于1或大于100";
        }
        if(!StringUtils.lengthCheck(user.getEmail(),1,100)){
            return "邮箱长度不能小于0或大于100";
        }
        if(!StringUtils.lengthCheck(user.getPhone(),1,20)){
            return "手机号长度不能小于0或大于100";
        }
        if(!roleService.isRoleExists(user.getRoleId())){
            return "角色id不正确";
        }
        if(isRepeat(user)){
            return "账号重复";
        }
        if(StringUtils.isEmpty(user.getId())){
            user.setId(IdWorker.getInstance().nextId());
            user.setStatus(1);
            user.setLoginCount(0);
            user.setModifyTime(new Date());
        }
        return null;
    }

    @Override
    public String deleteById(String id) {
        if(id.equals(Constant.DEFAULT_USER)){
            return "不能删除系统默认管理员";
        }
        userMapper.deleteById(id);
        Admin.userMap.remove(id);
        return null;
    }

    @Override
    public String deleteByIdList(List<String> idList) throws Exception {
        if(idList.contains(Constant.DEFAULT_USER)){
            return "不能删除系统默认管理员";
        }
        userMapper.deleteByIdList(idList);
        Admin.removeUserMapByList(idList);
        return null;
    }

    @Override
    public String update(User user) {
        if(user.getId().equals(Constant.DEFAULT_USER)){
            return "不能修改系统默认管理员";
        }
        String result = assemble(user);
        if(result!=null){
            return result;
        }
        userMapper.update(user);
        Admin.userMap.put(user.getId(),user);
        return null;
    }

    @Override
    public String updatePwd(User user) throws Exception {
        if(user.getId().equals(Constant.DEFAULT_USER)){
            return "不能修改系统默认管理员";
        }
        if(StringUtils.isEmpty(user.getId()) || StringUtils.isEmpty(user.getPassword())){
            return "用户id与密码不能为空";
        }
        userMapper.updatePwd(user);
        return null;
    }

    @Override
    public User getById(String id) {
        User user = userMapper.selectById(id);
        Role role = roleService.getRole(user.getRoleId());
        user.setRole(role);
        return user;
    }

    @Override
    public PageData<List<User>> getByAttribute(UserRequest request) {
        PageData pageData = new PageData(0);
        int total = userMapper.countByAttribute(request);
        if(total==0){
            pageData.setData(Collections.emptyList());
            return pageData;
        }
        pageData.setTotal(total);
        List<User> users = userMapper.getByAttribute(request);
        pageData.setData(users);
        return pageData;
    }

    @Override
    public UserResponse login(HttpServletRequest httpServletRequest,UserRequest request) {
        UserResponse response = new UserResponse();
        if(!validate(request.getValidateCode(),httpServletRequest)){
            response.setMessage("验证码错误!");
            return response;
        }
        if(StringUtils.isEmpty(request.getAccount()) || StringUtils.isEmpty(request.getPassword())){
            response.setMessage("账号密码不能为空!");
            return response;
        }
        User user = userMapper.login(request);
        if(user==null){
            response.setMessage("该账号不存在!");
            return response;
        }else if(!user.getPassword().equals(request.getPassword())){
            response.setMessage("密码错误!");
            return response;
        }else if(user.getStatus()==0){
            response.setMessage("该账号已被禁用!");
            return response;
        }else{
            //查询并设置用户信息、角色信息、权限信息与访问令牌
            User user1 = new User();
            if(user.getLastLoginTime()==null){
                user1.setLastLoginTime(new Date());
            }else{
                user1.setLastLoginTime(user.getLastLoginTime());
            }
            if(StringUtils.isEmpty(user.getLastLoginIp())){
                user1.setLastLoginIp(IPUtils.getIpAddress(httpServletRequest));
            }else{
                user1.setLastLoginIp(user.getLastLoginIp());
            }
            user1.setId(user.getId());
            user1.setAccount(user.getAccount());
            user1.setLoginCount(user.getLoginCount()+1);
            user1.setName(user.getName());
            response.setUser(user1);
            String token = IdWorker.getInstance().nextId();
            response.setAccessToken(token);
            response.setRole(roleService.getRole(user.getRoleId()));
            redisUtils.set(Constant.ACCESS_TOKEN_WITH_PREFIX+token,gson.toJson(user1), Constant.TIMEOUT);

            //修改最后登录时间与登录ip
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IPUtils.getIpAddress(httpServletRequest));
            userMapper.updateLoginInfo(user);
            return response;
        }
    }


    /**
     * 验证
     * @param code
     * @param request
     * @return
     */
    private boolean validate(String code,HttpServletRequest request){
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        String sessionId = session.getId();
        if (cookies != null && cookies.length>0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("JSESSIONID") && !StringUtils.isEmpty(cookie.getValue())){
                    sessionId = cookie.getValue();
                }
            }
        }
        String redisCode = redisUtils.get(Constant.SESSION_ID + sessionId);
        if(StringUtils.isEmpty(redisCode) || !redisCode.equals(StringUtils.toLowerCaseLocal(code))){
            return false;
        }
        return true;
    }

    @Override
    public String logout(HttpServletRequest httpServletRequest) {
        if(StringUtils.isEmpty(httpServletRequest.getHeader(Constant.ACCESS_TOKEN))){
            return "令牌不能为空";
        }
        redisUtils.delete(Constant.ACCESS_TOKEN_WITH_PREFIX+httpServletRequest.getHeader(Constant.ACCESS_TOKEN));
        return null;
    }
}
