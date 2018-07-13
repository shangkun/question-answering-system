package cn.ken.questionansweringsystem.service;

import cn.ken.questionansweringsystem.JUnit4BaseConfig;
import cn.ken.questionansweringsystem.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 用户服务测试类 <br/>
 */
public class TestUserService extends JUnit4BaseConfig {
    @Autowired
    private UserService userService;
    @Test
    public void add(){
        User user = new User();
        user.setId("1");
        user.setAccount("1");
        user.setName("1");
        user.setPassword("1");
        user.setStatus(1);
        userService.add(user);

        User user1 = userService.getById("1");

        user1.setName("2");
        userService.update(user1);

        userService.deleteById("1");

    }
}
