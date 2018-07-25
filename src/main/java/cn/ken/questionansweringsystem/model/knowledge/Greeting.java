package cn.ken.questionansweringsystem.model.knowledge;

import java.util.Date;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 寒暄知识 <br/>
 */
public class Greeting {

    private String id;

    private String title;

    private Date modifyTime;

    private String modifierId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }
}