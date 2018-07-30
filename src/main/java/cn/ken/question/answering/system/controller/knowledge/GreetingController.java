package cn.ken.question.answering.system.controller.knowledge;

import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.service.knowledge.GreetingService;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.model.knowledge.Greeting;
import cn.ken.question.answering.system.model.knowledge.GreetingRequest;
import cn.ken.question.answering.system.model.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 寒暄知识 <br/>
 */
@RestController
@RequestMapping(value = "/api/knowledge/greeting")
@Api(tags="寒暄知识管理")
public class GreetingController extends Base {
    @Autowired
    private GreetingService greetingService;

    @ApiOperation(value = "寒暄知识添加", notes = "寒暄知识添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(GreetingRequest request) throws Exception{
        String result = greetingService.add(request);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "寒暄知识单个获取", notes = "寒暄知识单个获取")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@PathVariable("id") String id) throws Exception{
        Greeting result = greetingService.getGreeting(id);
        if(result==null){
            return Response.FAIL("查询为空");
        }
        return Response.SUCCESS(result);
    }

    @ApiOperation(value = "寒暄知识标题重复判断", notes = "寒暄知识标题重复判断")
    @RequestMapping(value = "/repeat", method = RequestMethod.POST)
    @ResponseBody
    public Response isRepeat(@RequestBody GreetingRequest request) throws Exception{
        if(greetingService.isRepeat(request)){
            return Response.FAIL("重复");
        }else{
            return Response.SUCCESS("可以添加");
        }
    }

    @ApiOperation(value = "寒暄知识修改", notes = "寒暄知识修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(GreetingRequest request) throws Exception{
        String result = greetingService.update(request);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "寒暄知识单个删除", notes = "寒暄知识单个删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@PathVariable("id") String id) throws Exception{
        String result = greetingService.delete(id);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "寒暄知识批量删除", notes = "寒暄知识批量删除")
    @RequestMapping(value = "/batch/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response batchDelete(@RequestBody List<String> list) throws Exception{
        String result = greetingService.deleteByIdList(list);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "寒暄知识列表查询", notes = "寒暄知识列表查询")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(@RequestBody GreetingRequest request) throws Exception{
        PageData result = greetingService.get(request);
        if(result.getTotal()==0){
            return Response.SUCCESS(result, "数据为空");
        }
        return Response.SUCCESS(result);
    }
}
