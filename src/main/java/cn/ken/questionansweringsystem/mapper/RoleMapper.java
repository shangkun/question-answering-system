package cn.ken.questionansweringsystem.mapper;

import cn.ken.questionansweringsystem.model.Role;
import cn.ken.questionansweringsystem.model.request.RoleRequest;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role,String>{

    List<Role> getByAttribute(RoleRequest request);

    int countByAttribute(RoleRequest request);

    int countByName(String name);

    int countByIdAndName(String id,String name);

    int deleteByIdList(List<String> list);
}