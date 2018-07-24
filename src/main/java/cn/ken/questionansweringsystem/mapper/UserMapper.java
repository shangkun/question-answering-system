package cn.ken.questionansweringsystem.mapper;

import cn.ken.questionansweringsystem.model.User;
import cn.ken.questionansweringsystem.model.request.UserRequest;

import java.util.List;

public interface UserMapper extends BaseMapper<User,String>{

    int countByAttribute(UserRequest request);

    List<User> getByAttribute(UserRequest request);

    int updateLoginInfo(User user);

    User login(UserRequest request);

    int countByAccount(String account);

    int countByIdAndAccount(String id,String account);

    int deleteByIdList(List<String> list);

    int updatePwd(User user);
}