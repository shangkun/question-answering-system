package cn.ken.questionansweringsystem.model;

public class RoleMenu {
    private String id;

    private String roleId;

    private String menuId;

    public RoleMenu(String id, String roleId, String menuId) {
        this.id = id;
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public RoleMenu() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}