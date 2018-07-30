package cn.ken.question.answering.system.mapper.admin;

import cn.ken.question.answering.system.model.admin.UserRequest;
import cn.ken.question.answering.system.mapper.BaseMapper;
import cn.ken.question.answering.system.model.admin.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User,String> {

    int countByAttribute(UserRequest request);

    List<User> getByAttribute(UserRequest request);

    int updateLoginInfo(User user);

    User login(UserRequest request);

    int countByAccount(String account);

    int countByIdAndAccount(String id,String account);

    int deleteByIdList(List<String> list);

    int updatePwd(User user);

    int checkDeleteRole(String roleId);
}