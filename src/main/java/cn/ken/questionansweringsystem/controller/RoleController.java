package cn.ken.questionansweringsystem.controller;

import cn.ken.questionansweringsystem.model.Menu;
import cn.ken.questionansweringsystem.model.PageData;
import cn.ken.questionansweringsystem.model.Response;
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
        String result = roleService.delete(id);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "批量删除角色", notes = "批量删除角色")
    @RequestMapping(value = "batch/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response batchDelete(@RequestBody List<String> idList) throws Exception{
        String result = roleService.deleteByIdList(idList);
        if(result!=null){
            return Response.FAIL(result);
        }
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

    @ApiOperation(value = "单个获取角色的菜单id列表", notes = "单个获取角色的菜单id列表")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response getRole(@PathVariable("id") String id) throws Exception{
        List<String> menuList = roleService.getMenuByRoleId(id);
        if(!CollectionUtils.isEmpty(menuList)){
            return Response.SUCCESS(menuList);
        }else{
            return Response.FAIL("数据为空");
        }
    }

    @ApiOperation(value = "获取所有菜单", notes = "获取所有菜单")
    @RequestMapping(value = "/menu/get", method = RequestMethod.POST)
    @ResponseBody
    public Response getMenu() throws Exception{
        List<Menu> menuList = roleService.getMenu();
        if(!CollectionUtils.isEmpty(menuList)){
            return Response.SUCCESS(menuList);
        }else{
            return Response.FAIL("数据为空");
        }
    }
}
