package cn.ken.question.answering.system.mapper.admin;

import cn.ken.question.answering.system.model.admin.RoleMenu;

import java.util.List;

public interface RoleMenuMapper {

    List<String> getByRoleId(List<String> list);

    List<RoleMenu> get();

    List<String> getMenuByRoleId(String id);

    int deleteByRoleId(String id);

    int batchInsert(List<RoleMenu> list);

    int deleteByRoleIdList(List<String> list);
}