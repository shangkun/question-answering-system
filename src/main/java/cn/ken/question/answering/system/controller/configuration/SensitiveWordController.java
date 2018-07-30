package cn.ken.question.answering.system.controller.configuration;

import cn.ken.question.answering.system.model.configuration.SensitiveWordRequest;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.response.Response;
import cn.ken.question.answering.system.service.configuration.SensitiveWordService;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.model.configuration.SensitiveWord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/24 <br/>
 * what: 敏感词配置 <br/>
 */
@RestController
@RequestMapping(value = "/api/config/sensitive/word")
@Api(tags="敏感词配置")
public class SensitiveWordController extends Base {
    @Autowired
    private SensitiveWordService sensitiveWordService;

    @ApiOperation(value = "敏感词添加", notes = "敏感词添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(SensitiveWordRequest request) throws Exception{
        String result = sensitiveWordService.add(request);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "敏感词单个获取", notes = "敏感词单个获取")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@PathVariable("id") String id) throws Exception{
        SensitiveWord result = sensitiveWordService.get(id);
        if(result==null){
            return Response.FAIL("查询为空");
        }
        return Response.SUCCESS(result);
    }

    @ApiOperation(value = "敏感主题重复判断", notes = "敏感主题重复判断")
    @RequestMapping(value = "/repeat", method = RequestMethod.POST)
    @ResponseBody
    public Response isRepeat(@RequestBody SensitiveWord request) throws Exception{
        if(sensitiveWordService.isRepeat(request)){
            return Response.FAIL("重复");
        }else{
            return Response.SUCCESS("可以添加");
        }
    }

    @ApiOperation(value = "敏感词修改", notes = "敏感词修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(SensitiveWordRequest request) throws Exception{
        String result = sensitiveWordService.update(request);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "敏感词单个删除", notes = "敏感词单个删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@PathVariable("id") String id) throws Exception{
        String result = sensitiveWordService.deleteById(id);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "敏感词批量删除", notes = "敏感词批量删除")
    @RequestMapping(value = "/batch/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response batchDelete(@RequestBody List<String> list) throws Exception{
        String result = sensitiveWordService.batchDelete(list);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "敏感词列表查询", notes = "敏感词列表查询")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(@RequestBody SensitiveWordRequest request) throws Exception{
        PageData result = sensitiveWordService.getByAttribute(request);
        if(result.getTotal()==0){
            return Response.SUCCESS(result, "数据为空");
        }
        return Response.SUCCESS(result);
    }
}
