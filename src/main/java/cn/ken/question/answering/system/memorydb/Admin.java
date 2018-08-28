package cn.ken.question.answering.system.memorydb;

import cn.ken.question.answering.system.model.admin.Menu;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.model.admin.Role;
import cn.ken.question.answering.system.model.admin.RoleMenu;
import cn.ken.question.answering.system.model.admin.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author: shangkun <br/>
 * date: 2018/7/26 <br/>
 * what: 管理信息 <br/>
 */
public class Admin extends Base {

    public static Map<String,User> userMap = new ConcurrentHashMap<>();

    public static Map<String,Role> roleMap = new ConcurrentHashMap<>();

    public static Map<String,RoleMenu> roleMenuMap = new ConcurrentHashMap<>();

    public static Map<String,Menu> menuMap = new ConcurrentHashMap<>();

    public static void putAllUserMap(List<User> list){
        for(User user:list){
            userMap.put(user.getId(),user);
        }
    }

    public static void removeUserMapByList(List<String> list){
        for(String id:list){
            userMap.remove(id);
        }
    }

    public static void putAllRoleMap(List<Role> list){
        for(Role role:list){
            roleMap.put(role.getId(),role);
        }
    }

    public static void removeRoleMapByList(List<String> list){
        for(String id:list){
            roleMap.remove(id);
        }
    }

    public static void putAllRoleMenuMap(List<RoleMenu> list){
        for(RoleMenu roleMenu:list){
            roleMenuMap.put(roleMenu.getId(),roleMenu);
        }
    }

    public static void putAllMenuMap(List<Menu> list){
        for(Menu menu:list){
            menuMap.put(menu.getId(),menu);
        }
    }

    public static void removeRoleMenuMapByList(List<String> list){
        for(String id:list){
            roleMenuMap.remove(id);
        }
    }
}
