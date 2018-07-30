package cn.ken.question.answering.system.controller.knowledge;

import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.response.Response;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.model.knowledge.Knowledge;
import cn.ken.question.answering.system.model.knowledge.KnowledgeRequest;
import cn.ken.question.answering.system.service.knowledge.KnowledgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 知识 <br/>
 */
@RestController
@RequestMapping(value = "/api/knowledge")
@Api(tags="知识管理")
public class KnowledgeController extends Base {
    @Autowired
    private KnowledgeService knowledgeService;

    @ApiOperation(value = "知识添加", notes = "知识添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(KnowledgeRequest request) throws Exception{
        String result = knowledgeService.add(request);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "知识单个获取", notes = "知识单个获取")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@PathVariable("id") String id) throws Exception{
        Knowledge result = knowledgeService.getKnowledge(id);
        if(result==null){
            return Response.FAIL("查询为空");
        }
        return Response.SUCCESS(result);
    }

    @ApiOperation(value = "知识标题重复判断", notes = "知识标题重复判断")
    @RequestMapping(value = "/repeat", method = RequestMethod.POST)
    @ResponseBody
    public Response isRepeat(@RequestBody KnowledgeRequest request) throws Exception{
        if(knowledgeService.isRepeat(request)){
            return Response.FAIL("重复");
        }else{
            return Response.SUCCESS("可以添加");
        }
    }

    @ApiOperation(value = "知识修改", notes = "知识修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(KnowledgeRequest request) throws Exception{
        String result = knowledgeService.update(request);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "知识单个删除", notes = "知识单个删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@PathVariable("id") String id) throws Exception{
        String result = knowledgeService.delete(id);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "知识批量删除", notes = "知识批量删除")
    @RequestMapping(value = "/batch/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response batchDelete(@RequestBody List<String> list) throws Exception{
        String result = knowledgeService.deleteByIdList(list);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "知识列表查询", notes = "知识列表查询")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(@RequestBody KnowledgeRequest request) throws Exception{
        PageData result = knowledgeService.get(request);
        if(result.getTotal()==0){
            return Response.SUCCESS(result, "数据为空");
        }
        return Response.SUCCESS(result);
    }
}
