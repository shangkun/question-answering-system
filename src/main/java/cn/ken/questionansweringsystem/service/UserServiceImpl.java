package cn.ken.questionansweringsystem.service;

import cn.ken.questionansweringsystem.mapper.UserMapper;
import cn.ken.questionansweringsystem.model.User;
import cn.ken.questionansweringsystem.utils.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 用户服务实现类 <br/>
 */
@Service
public class UserServiceImpl extends Base implements UserService{
    @Autowired
    private UserMapper userMapper;

    public int add(User user) {
        return userMapper.insert(user);
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
}
