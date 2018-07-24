package cn.ken.questionansweringsystem.model.admin;

import cn.ken.questionansweringsystem.model.admin.Role;
import cn.ken.questionansweringsystem.model.admin.User;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: UserResponse <br/>
 */
public class UserResponse{

    private User user;

    private String message;

    private Role role;

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
