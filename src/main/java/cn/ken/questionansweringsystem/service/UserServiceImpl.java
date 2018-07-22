package cn.ken.questionansweringsystem.service;

import cn.ken.questionansweringsystem.common.Constant;
import cn.ken.questionansweringsystem.mapper.UserMapper;
import cn.ken.questionansweringsystem.model.PageData;
import cn.ken.questionansweringsystem.model.User;
import cn.ken.questionansweringsystem.model.request.UserRequest;
import cn.ken.questionansweringsystem.model.response.UserResponse;
import cn.ken.questionansweringsystem.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    private RedisUtils redisUtils;

    public String add(User user) {
        String result = assemble(user);
        if(result!=null){
            return result;
        }
        userMapper.insert(user);
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
        if(!StringUtils.lengthCheck(user.getPassword(),1,20)){
            return "手机号长度不能小于0或大于100";
        }
        if(StringUtils.isEmpty(user.getId())){
            if(isRepeat(user)){
                return "账号重复";
            }
            user.setId(IdWorker.getInstance().nextId());
            user.setStatus(1);
            user.setLoginCount(0);
            user.setModifyTime(new Date());
        }else{
            if(isRepeat(user)){
                return "账号重复";
            }
        }
        return null;
    }



    @Override
    public int deleteById(String id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User getById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageData<List<User>> getByAttribute(UserRequest request) {
        return new PageData(userMapper.getByAttribute(request),userMapper.countByAttribute(request));
    }

    @Override
    public UserResponse login(HttpServletRequest httpServletRequest,UserRequest request) {
        try {
            UserResponse response = new UserResponse();
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
                redisUtils.set(Constant.ACCESS_TOKEN_WITH_PREFIX+token,gson.toJson(user1), Constant.TIMEOUT);

                //修改最后登录时间与登录ip
                user.setLastLoginTime(new Date());
                user.setLastLoginIp(IPUtils.getIpAddress(httpServletRequest));
                userMapper.updateLoginInfo(user);
                return response;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public String logout(HttpServletRequest httpServletRequest) {
        try {
            if(StringUtils.isEmpty(httpServletRequest.getHeader(Constant.ACCESS_TOKEN))){
                return "令牌不能为空";
            }
            redisUtils.delete(Constant.ACCESS_TOKEN_WITH_PREFIX+httpServletRequest.getHeader(Constant.ACCESS_TOKEN));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
