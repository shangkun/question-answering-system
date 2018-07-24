package cn.ken.questionansweringsystem.mapper.admin;

import cn.ken.questionansweringsystem.model.admin.RoleMenu;

import java.util.List;

public interface RoleMenuMapper {

    List<RoleMenu> getByRoleId(String id);

    List<String> getMenuByRoleId(String id);

    int deleteByRoleId(String id);

    int batchInsert(List<RoleMenu> list);

    int deleteByRoleIdList(List<String> list);
}