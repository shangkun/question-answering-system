package cn.ken.questionansweringsystem.service.admin;

import cn.ken.questionansweringsystem.model.admin.Menu;
import cn.ken.questionansweringsystem.model.admin.RoleMenu;
import cn.ken.questionansweringsystem.model.response.PageData;
import cn.ken.questionansweringsystem.model.admin.Role;
import cn.ken.questionansweringsystem.model.admin.RoleRequest;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/23 <br/>
 * what: RoleService <br/>
 */
public interface RoleService {

    public List<Menu> getMenu() throws Exception;

    public List<RoleMenu> getRoleMenu() throws Exception;

    public List<String> getMenuByRoleId(String roleId) throws Exception;

    public Role getRole(String roleId);

    public String add(RoleRequest request) throws Exception;

    public boolean isRepeat(RoleRequest request) throws Exception;

    public String delete(String id) throws Exception;

    public String deleteByIdList(List<String> list) throws Exception;

    public String update(RoleRequest request) throws Exception;

    public PageData get(RoleRequest request) throws Exception;

    public boolean isRoleExists(String roleId);
}
