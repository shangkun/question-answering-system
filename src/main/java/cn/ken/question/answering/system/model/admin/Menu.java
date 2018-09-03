package cn.ken.question.answering.system.model.admin;
/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 菜单 <br/>
 */
public class Menu {
    //主键
    private String id;
    //菜单名称
    private String name;
    //菜单URL
    private String url;
    //菜单描述
    private String description;
    //菜单状态
    private Integer status;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}