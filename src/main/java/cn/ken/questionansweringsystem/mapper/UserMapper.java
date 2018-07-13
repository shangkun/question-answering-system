package cn.ken.questionansweringsystem.mapper;

import cn.ken.questionansweringsystem.model.User;
import cn.ken.questionansweringsystem.model.request.UserRequest;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 用户Mapper <br/>
 */
public interface UserMapper extends BaseMapper<User,String> {

    int countByAttribute(UserRequest request);

    List<User> getByAttribute(UserRequest request);
}
