package cn.ken.question.answering.system.mapper.admin;

import cn.ken.question.answering.system.model.admin.Role;
import cn.ken.question.answering.system.model.admin.RoleRequest;
import cn.ken.question.answering.system.mapper.BaseMapper;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role,String> {

    List<Role> getByAttribute(RoleRequest request);

    int countByAttribute(RoleRequest request);

    int countByName(String name);

    int countByIdAndName(String id,String name);

    int deleteByIdList(List<String> list);
}