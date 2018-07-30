package cn.ken.question.answering.system.service;

import cn.ken.question.answering.system.JUnit4BaseConfig;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.service.admin.RoleService;
import cn.ken.question.answering.system.model.admin.Role;
import cn.ken.question.answering.system.model.admin.RoleRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 用户服务测试类 <br/>
 */
public class TestRoleService extends JUnit4BaseConfig {
    @Autowired
    private RoleService roleService;
    @Test
    public void add() throws Exception{
        List<String> menuList = new ArrayList<>();
        for(int i=0;i<5;i++){
            menuList.add(i+"");
        }
        RoleRequest request = new RoleRequest();
        request.setMenuList(menuList);
        request.setName("超级管理员");
        request.setDescription("超级管理员");
        request.setStatus(1);
        roleService.add(request);

        RoleRequest request1 = new RoleRequest();
        request1.setIndex(0);
        request1.setPageSize(10);

        PageData<List<Role>> users = roleService.get(request1);

        users.getTotal();
    }
}
