package cn.ken.questionansweringsystem.controller.admin;

import cn.ken.questionansweringsystem.model.PageData;
import cn.ken.questionansweringsystem.model.response.Response;
import cn.ken.questionansweringsystem.model.admin.User;
import cn.ken.questionansweringsystem.model.admin.UserRequest;
import cn.ken.questionansweringsystem.model.admin.UserResponse;
import cn.ken.questionansweringsystem.service.admin.UserService;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 用户接口类 <br/>
 */
@RestController
@RequestMapping("/api/admin/user")
@Api(tags="用户管理")
public class UserController extends Base{
    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(HttpServletRequest httpServletRequest,UserRequest request) throws Exception{
        UserResponse response = userService.login(httpServletRequest,request);
        if(StringUtils.isEmpty(response.getMessage())){
            return Response.SUCCESS(response);
        }else {
            return Response.FAIL(response.getMessage());
        }
    }

    @ApiOperation(value = "登出", notes = "登出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Response logout(HttpServletRequest httpServletRequest) throws Exception{
        String result = userService.logout(httpServletRequest);
        if(StringUtils.isEmpty(result)){
            return Response.SUCCESS();
        }else {
            return Response.FAIL(result);
        }
    }

    @ApiOperation(value = "按条件查询用户", notes = "按条件查询用户")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response get(@RequestBody UserRequest request) throws Exception{
        PageData pageData = userService.getByAttribute(request);
        return Response.SUCCESS(pageData);
    }

    @ApiOperation(value = "登录名校验", notes = "登录名校验")
    @RequestMapping(value = "/repeat", method = RequestMethod.POST)
    @ResponseBody
    public Response repeat(@RequestBody User user) throws Exception{
        if (userService.isRepeat(user)) {
            return Response.FAIL("登录名重复");
        } else {
            return Response.SUCCESS("登录名不重复");
        }
    }

    @ApiOperation(value = "单个添加用户", notes = "添加一个用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(User user) throws Exception{
        String result = userService.add(user);
        if(result!=null){
            return Response.SUCCESS(result);
        }else{
            return Response.SUCCESS();
        }
    }

    @ApiOperation(value = "单个删除用户", notes = "删除一个用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@PathVariable("id") String id) throws Exception{
        String result = userService.deleteById(id);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "批量删除用户", notes = "批量删除用户")
    @RequestMapping(value = "/batch/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response batchDelete(@RequestBody List<String> idList) throws Exception{
        String result = userService.deleteByIdList(idList);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @RequestMapping(value = "/pwd/update", method = RequestMethod.POST)
    @ResponseBody
    public Response updatePwd(User user) throws Exception{
        String result = userService.updatePwd(user);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "更新用户", notes = "更新用户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(User user) throws Exception{
        String result = userService.update(user);
        if(result!=null){
            return Response.SUCCESS(result);
        }else{
            return Response.SUCCESS();
        }
    }

    @ApiOperation(value = "单个获取用户", notes = "获取一个用户")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response update(@PathVariable("id") String id) throws Exception{
        User user = userService.getById(id);
        if(null!=user){
            return Response.SUCCESS(user);
        }
        return Response.FAIL();
    }
}
