package cn.ken.questionansweringsystem.service;

import cn.ken.questionansweringsystem.model.PageData;
import cn.ken.questionansweringsystem.model.User;
import cn.ken.questionansweringsystem.model.request.UserRequest;
import cn.ken.questionansweringsystem.model.response.UserResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 用户服务接口类 <br/>
 */
public interface UserService {
    /**
     * 添加一个用户
     * @param user
     * @return
     */
    public String add(User user) throws Exception;

    /**
     * 重复校验
     * @param user
     * @return
     */
    public boolean isRepeat(User user) throws Exception;

    /**
     * 删除一个用户
     * @param id
     * @return
     */
    public String deleteById(String id) throws Exception;

    /**
     * 批量删除
     * @param idList
     * @return
     * @throws Exception
     */
    public String deleteByIdList(List<String> idList) throws Exception;

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    public String update(User user) throws Exception;

    /**
     * 更新用户状态
     * @param user
     * @return
     * @throws Exception
     */
    public String updatePwd(User user) throws Exception;

    /**
     * 获取一个用户数据
     * @param id
     * @return
     */
    public User getById(String id) throws Exception;

    /**
     * 根据条件查询一组数据
     * @param request
     * @return
     */
    public PageData<List<User>> getByAttribute(UserRequest request) throws Exception;

    /**
     * 管理员登陆
     * @param httpServletRequest
     * @param request
     * @return
     */
    public UserResponse login(HttpServletRequest httpServletRequest,UserRequest request) throws Exception;

    /**
     * 管理员登出
     * @param httpServletRequest
     * @return
     */
    public String logout(HttpServletRequest httpServletRequest) throws Exception;
}
