package cn.ken.question.answering.system.service.admin;

import cn.ken.question.answering.system.model.admin.Menu;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.admin.RoleMenu;
import cn.ken.question.answering.system.model.admin.Role;
import cn.ken.question.answering.system.model.admin.RoleRequest;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/23 <br/>
 * what: 角色服务层接口 <br/>
 */
public interface RoleService {
    //获取所有菜单数据
    public List<Menu> getMenu() throws Exception;
    //获取所有角色菜单关联数据
    public List<RoleMenu> getRoleMenu() throws Exception;
    //通过角色id查询菜单数据
    public List<String> getMenuByRoleId(String roleId) throws Exception;
    //单个获取角色数据
    public Role getRole(String roleId);
    //单个添加角色
    public String add(RoleRequest request) throws Exception;
    //判断角色名称是否重复
    public boolean isRepeat(RoleRequest request) throws Exception;
    //通过id删除角色
    public String delete(String id) throws Exception;
    //通过多个id删除角色
    public String deleteByIdList(List<String> list) throws Exception;
    //修改角色数据
    public String update(RoleRequest request) throws Exception;
    //分页查询角色数据
    public PageData get(RoleRequest request) throws Exception;
    //判断角色是否存在
    public boolean isRoleExists(String roleId);
}
