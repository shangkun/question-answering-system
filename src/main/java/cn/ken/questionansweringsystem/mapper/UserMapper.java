package cn.ken.questionansweringsystem.mapper;

import cn.ken.questionansweringsystem.model.User;
import cn.ken.questionansweringsystem.model.request.UserRequest;

import java.util.List;

public interface UserMapper extends BaseMapper<User,String>{

    int countByAttribute(UserRequest request);

    List<User> getByAttribute(UserRequest request);
}