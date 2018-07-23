package cn.ken.questionansweringsystem.service;

import cn.ken.questionansweringsystem.model.Menu;
import cn.ken.questionansweringsystem.model.PageData;
import cn.ken.questionansweringsystem.model.RoleMenu;
import cn.ken.questionansweringsystem.model.request.RoleRequest;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/23 <br/>
 * what: RoleService <br/>
 */
public interface RoleService {

    public List<Menu> getMenu() throws Exception;

    public List<RoleMenu> getMenuByRoleId(String roleId) throws Exception;

    public String add(RoleRequest request) throws Exception;

    public boolean isRepeat(RoleRequest request) throws Exception;

    public String delete(String id) throws Exception;

    public String update(RoleRequest request) throws Exception;

    public PageData get(RoleRequest request) throws Exception;

    public boolean isRoleExists(String roleId);
}
