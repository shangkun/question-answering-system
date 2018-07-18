package cn.ken.questionansweringsystem.model;

import java.util.Date;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 用户 <br/>
 */
public class User {

    private String id;

    private String account;

    private String name;

    private String password;

    private Date modifyTime;

    private int status;

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
