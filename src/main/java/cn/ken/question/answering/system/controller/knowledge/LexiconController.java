package cn.ken.question.answering.system.controller.knowledge;

import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.model.knowledge.Lexicon;
import cn.ken.question.answering.system.model.knowledge.LexiconRequest;
import cn.ken.question.answering.system.model.response.Response;
import cn.ken.question.answering.system.service.knowledge.LexiconService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 词库 <br/>
 */
@RestController
@RequestMapping(value = "/api/knowledge/lexicon")
@Api(tags="词库配置")
public class LexiconController extends Base {
    @Autowired
    private LexiconService lexiconService;

    @ApiOperation(value = "词库添加", notes = "词库添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(LexiconRequest request) throws Exception{
        String result = lexiconService.add(request);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "词库单个获取", notes = "词库单个获取")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@PathVariable("id") String id) throws Exception{
        Lexicon result = lexiconService.get(id);
        if(result==null){
            return Response.FAIL("查询为空");
        }
        return Response.SUCCESS(result);
    }

    @ApiOperation(value = "敏感主题重复判断", notes = "敏感主题重复判断")
    @RequestMapping(value = "/repeat", method = RequestMethod.POST)
    @ResponseBody
    public Response isRepeat(@RequestBody Lexicon request) throws Exception{
        if(lexiconService.isRepeat(request)){
            return Response.FAIL("重复");
        }else{
            return Response.SUCCESS("可以添加");
        }
    }

    @ApiOperation(value = "词库修改", notes = "词库修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(LexiconRequest request) throws Exception{
        String result = lexiconService.update(request);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "词库单个删除", notes = "词库单个删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@PathVariable("id") String id) throws Exception{
        String result = lexiconService.deleteById(id);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "词库批量删除", notes = "词库批量删除")
    @RequestMapping(value = "/batch/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response batchDelete(@RequestBody List<String> list) throws Exception{
        String result = lexiconService.batchDelete(list);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "词库列表查询", notes = "词库列表查询")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(@RequestBody LexiconRequest request) throws Exception{
        PageData result = lexiconService.getByAttribute(request);
        if(result.getTotal()==0){
            return Response.SUCCESS(result, "数据为空");
        }
        return Response.SUCCESS(result);
    }
}
