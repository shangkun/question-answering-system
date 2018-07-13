package cn.ken.questionansweringsystem.service;

import cn.ken.questionansweringsystem.model.PageData;
import cn.ken.questionansweringsystem.model.User;
import cn.ken.questionansweringsystem.model.request.UserRequest;

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
    public int add(User user);

    /**
     * 删除一个用户
     * @param id
     * @return
     */
    public int deleteById(String id);

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    public int update(User user);

    /**
     * 获取一个用户数据
     * @param id
     * @return
     */
    public User getById(String id);

    /**
     * 根据条件查询一组数据
     * @param request
     * @return
     */
    public PageData<List<User>> getByAttribute(UserRequest request);
}
