package cn.ken.questionansweringsystem.model.request;

import cn.ken.questionansweringsystem.model.Role;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/23 <br/>
 * what: RoleRequest <br/>
 */
public class RoleRequest extends Role{

    private int index;

    private int pageSize;

    private List<String> menuList;

    public List<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<String> menuList) {
        this.menuList = menuList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
