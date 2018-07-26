package cn.ken.questionansweringsystem.service.admin;

import cn.ken.questionansweringsystem.common.Constant;
import cn.ken.questionansweringsystem.mapper.admin.MenuMapper;
import cn.ken.questionansweringsystem.mapper.admin.RoleMapper;
import cn.ken.questionansweringsystem.mapper.admin.RoleMenuMapper;
import cn.ken.questionansweringsystem.mapper.admin.UserMapper;
import cn.ken.questionansweringsystem.memorydb.Admin;
import cn.ken.questionansweringsystem.model.admin.Menu;
import cn.ken.questionansweringsystem.model.response.PageData;
import cn.ken.questionansweringsystem.model.admin.Role;
import cn.ken.questionansweringsystem.model.admin.RoleMenu;
import cn.ken.questionansweringsystem.model.admin.RoleRequest;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.IdWorker;
import cn.ken.questionansweringsystem.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/23 <br/>
 * what: RoleServiceImpl <br/>
 */
@Service
public class RoleServiceImpl extends Base implements RoleService{
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Menu> getMenu() throws Exception {
        return menuMapper.get();
    }

    @Override
    public List<RoleMenu> getRoleMenu() throws Exception {
        return roleMenuMapper.get();
    }

    @Override
    public List<String> getMenuByRoleId(String roleId) throws Exception {
        if(!isRoleExists(roleId)){
            return Collections.emptyList();
        }
        return roleMenuMapper.getMenuByRoleId(roleId);
    }

    @Override
    public Role getRole(String roleId){
        if(!isRoleExists(roleId)){
            return null;
        }
        Role role = roleMapper.selectById(roleId);
        List<String> menuList = roleMenuMapper.getMenuByRoleId(roleId);
        role.setMenuList(menuList);
        return role;
    }

    @Override
    public String add(RoleRequest request) throws Exception {
        Role role = new Role();
        List<RoleMenu> roleMenuList = new ArrayList<>();
        String result = assemble(request,role,roleMenuList);
        if(result!=null){
            return result;
        }
        if(roleMenuMapper.batchInsert(roleMenuList)==0){
            return "角色菜单关联数据插入失败";
        }
        roleMapper.insert(role);
        Admin.putAllRoleMenuMap(roleMenuList);
        Admin.roleMap.put(role.getId(),role);
        return null;
    }

    /**
     * 数据组装
     * @param request
     * @return
     */
    public String assemble(RoleRequest request,Role role,List<RoleMenu> roleMenuList) throws Exception{
        if(!StringUtils.lengthCheck(request.getName(),2,50)){
            return "角色名称不能小于2或大于50";
        }
        role.setName(request.getName());
        if(!StringUtils.lengthCheck(request.getDescription(),1,200)){
            return "角色描述不能小于1或大于200";
        }
        role.setDescription(request.getDescription());
        if(isRepeat(request)){
            return "角色名称重复";
        }
        if(StringUtils.isEmpty(request.getId())){
            role.setId(IdWorker.getInstance().nextId());
            role.setStatus(1);
            role.setModifyTime(new Date());
        }else{
            if(!isRoleExists(request.getId())){
                return "当前角色不存在";
            }
            role.setId(request.getId());
        }
        if(CollectionUtils.isEmpty(request.getMenuList())){
            return "至少要选择一个菜单";
        }
        for(String menuId:request.getMenuList()){
            RoleMenu roleMenu = new RoleMenu(IdWorker.getInstance().nextId(),role.getId(),menuId);
            roleMenuList.add(roleMenu);
        }
        return null;
    }

    @Override
    public boolean isRepeat(RoleRequest request) throws Exception {
        if(StringUtils.isEmpty(request.getId()) && !StringUtils.isEmpty(request.getName()) && roleMapper.countByName(request.getName())>0){
            return true;
        }
        if(!StringUtils.isEmpty(request.getId()) && !StringUtils.isEmpty(request.getName()) && roleMapper.countByIdAndName(request.getId(), request.getName())>0){
            return true;
        }
        return false;
    }

    @Override
    public String delete(String id) throws Exception {
        if(id.equals(Constant.DEFAULT_ROLE)){
            return "不能删除系统默认角色";
        }
        if(userMapper.checkDeleteRole(id)>0){
            return "该角色正在被使用,请确认该角色没有被用户使用";
        }
        List<String> list = new ArrayList<>();
        list.add(id);
        List<String> roleMenuList = roleMenuMapper.getByRoleId(list);
        if(roleMenuMapper.deleteByRoleId(id)==0){
            return "角色菜单关联数据删除失败";
        }
        roleMapper.deleteById(id);
        Admin.removeRoleMenuMapByList(roleMenuList);
        Admin.roleMap.remove(id);
        return null;
    }

    @Override
    public String deleteByIdList(List<String> list) throws Exception {
        if(list.contains(Constant.DEFAULT_ROLE)){
            return "不能删除系统默认角色";
        }
        for(String id:list){
            if(userMapper.checkDeleteRole(id)>0){
                return "该角色正在被使用,请确认该角色没有被用户使用";
            }
        }
        if(roleMenuMapper.deleteByRoleIdList(list)==0){
            return "角色菜单关联数据删除失败";
        }
        roleMapper.deleteByIdList(list);
        Admin.removeRoleMenuMapByList(list);
        Admin.removeRoleMapByList(list);
        return null;
    }

    @Override
    public String update(RoleRequest request) throws Exception {
        if(request.getId().equals(Constant.DEFAULT_ROLE)){
            return "不能修改系统默认角色";
        }
        Role role = new Role();
        List<RoleMenu> roleMenuList = new ArrayList<>();
        String result = assemble(request,role,roleMenuList);
        if(result!=null){
            return result;
        }
        List<String> list = new ArrayList<>();
        list.add(request.getId());
        List<String> roleMenuIdList = roleMenuMapper.getByRoleId(list);
        if(roleMenuMapper.deleteByRoleId(request.getId())==0){
            return "角色菜单关联数据删除失败";
        }
        if(roleMenuMapper.batchInsert(roleMenuList)==0){
            return "角色菜单关联数据插入失败";
        }
        roleMapper.update(role);
        Admin.removeRoleMenuMapByList(roleMenuIdList);
        Admin.putAllRoleMenuMap(roleMenuList);
        Admin.roleMap.put(request.getId(),role);
        return null;
    }

    @Override
    public PageData get(RoleRequest request) throws Exception {
        PageData pageData = new PageData(0);
        int total = roleMapper.countByAttribute(request);
        if(total==0){
            pageData.setData(Collections.emptyList());
            return pageData;
        }
        pageData.setTotal(total);
        List<Role> roleList = roleMapper.getByAttribute(request);
        pageData.setData(roleList);
        return pageData;
    }

    @Override
    public boolean isRoleExists(String roleId){
        if(StringUtils.isEmpty(roleId)){
            return false;
        }
        Role role = roleMapper.selectById(roleId);
        if(role==null){
            return false;
        }
        return true;
    }
}
