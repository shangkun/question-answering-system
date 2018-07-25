package cn.ken.questionansweringsystem.model.admin;

import java.util.Date;
import java.util.List;
/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 角色 <br/>
 */
public class Role {

    private String id;

    private String name;

    private String description;

    private Date modifyTime;

    private Integer status;

    private List<String> menuList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<String> menuList) {
        this.menuList = menuList;
    }
}