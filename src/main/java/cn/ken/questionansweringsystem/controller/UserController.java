package cn.ken.questionansweringsystem.controller;

import cn.ken.questionansweringsystem.model.PageData;
import cn.ken.questionansweringsystem.model.Response;
import cn.ken.questionansweringsystem.model.User;
import cn.ken.questionansweringsystem.model.request.UserRequest;
import cn.ken.questionansweringsystem.service.UserService;
import cn.ken.questionansweringsystem.utils.Base;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 用户接口类 <br/>
 */
@RestController
@RequestMapping("/api/user")
@Api(tags="用户管理")
public class UserController extends Base{
    @Autowired
    private UserService userService;

    @ApiOperation(value = "按条件查询用户", notes = "按条件查询用户")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response get(@RequestBody UserRequest request) {
        PageData pageData = userService.getByAttribute(request);
        return Response.SUCCESS(pageData);
    }

    @ApiOperation(value = "单个添加用户", notes = "添加一个用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(User user) {
        userService.add(user);
        return Response.SUCCESS();
    }

    @ApiOperation(value = "单个删除用户", notes = "删除一个用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@PathVariable("id") String id) {
        userService.deleteById(id);
        return Response.SUCCESS();
    }

    @ApiOperation(value = "更新用户", notes = "更新用户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(User user) {
        userService.update(user);
        return Response.SUCCESS();
    }

    @ApiOperation(value = "单个获取用户", notes = "获取一个用户")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response update(@PathVariable("id") String id) {
        User user = userService.getById(id);
        if(null!=user){
            return Response.SUCCESS(user);
        }
        return Response.FAIL();
    }
}
