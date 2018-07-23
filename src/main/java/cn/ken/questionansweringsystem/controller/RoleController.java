package cn.ken.questionansweringsystem.controller;

import cn.ken.questionansweringsystem.model.PageData;
import cn.ken.questionansweringsystem.model.Response;
import cn.ken.questionansweringsystem.model.RoleMenu;
import cn.ken.questionansweringsystem.model.request.RoleRequest;
import cn.ken.questionansweringsystem.service.RoleService;
import cn.ken.questionansweringsystem.utils.Base;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 角色接口类 <br/>
 */
@RestController
@RequestMapping("/api/admin/role")
@Api(tags="角色管理")
public class RoleController extends Base{
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "按条件查询角色", notes = "按条件查询角色")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response get(@RequestBody RoleRequest request) throws Exception{
        PageData pageData = roleService.get(request);
        return Response.SUCCESS(pageData);
    }

    @ApiOperation(value = "角色名校验", notes = "角色名校验")
    @RequestMapping(value = "/repeat", method = RequestMethod.POST)
    @ResponseBody
    public Response repeat(RoleRequest request) throws Exception{
        if (roleService.isRepeat(request)) {
            return Response.FAIL("角色名重复");
        } else {
            return Response.SUCCESS("角色名不重复");
        }
    }

    @ApiOperation(value = "单个添加角色", notes = "添加一个角色")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(RoleRequest request) throws Exception{
        String result = roleService.add(request);
        if(result!=null){
            return Response.SUCCESS(result);
        }else{
            return Response.SUCCESS();
        }
    }

    @ApiOperation(value = "单个删除角色", notes = "删除一个角色")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@PathVariable("id") String id) throws Exception{
        roleService.delete(id);
        return Response.SUCCESS();
    }

    @ApiOperation(value = "更新角色", notes = "更新角色")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(RoleRequest request) throws Exception{
        String result = roleService.update(request);
        if(result!=null){
            return Response.SUCCESS(result);
        }else{
            return Response.SUCCESS();
        }
    }

    @ApiOperation(value = "单个获取角色", notes = "获取一个角色")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response update(@PathVariable("id") String id) throws Exception{
        List<RoleMenu> roleMenuList = roleService.getMenuByRoleId(id);
        if(!CollectionUtils.isEmpty(roleMenuList)){
            return Response.SUCCESS(roleMenuList);
        }else{
            return Response.FAIL("数据为空");
        }
    }
}
