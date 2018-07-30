package cn.ken.question.answering.system.controller.knowledge;

import cn.ken.question.answering.system.model.knowledge.ClassificationRequest;
import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.service.knowledge.ClassificationService;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.model.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 分类接口 <br/>
 */
@RestController
@RequestMapping("/api/knowledge/classification")
@Api(tags="分类管理")
public class ClassificationController extends Base {
    @Autowired
    private ClassificationService classificationService;

    @ApiOperation(value = "按条件查询分类", notes = "按条件查询分类")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response get(@RequestBody ClassificationRequest request) throws Exception{
        PageData pageData = classificationService.get(request);
        return Response.SUCCESS(pageData);
    }

    @ApiOperation(value = "分类名校验", notes = "分类名校验")
    @RequestMapping(value = "/repeat", method = RequestMethod.POST)
    @ResponseBody
    public Response repeat(@RequestBody ClassificationRequest request) throws Exception{
        if (classificationService.isRepeat(request)) {
            return Response.FAIL("分类名重复");
        } else {
            return Response.SUCCESS("分类名不重复");
        }
    }

    @ApiOperation(value = "单个添加分类", notes = "添加一个分类")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(ClassificationRequest request) throws Exception{
        String result = classificationService.add(request);
        if(result!=null){
            return Response.SUCCESS(result);
        }else{
            return Response.SUCCESS();
        }
    }

    @ApiOperation(value = "单个删除分类", notes = "删除一个分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@PathVariable("id") String id) throws Exception{
        String result = classificationService.delete(id);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }

    @ApiOperation(value = "更新分类", notes = "更新分类")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(ClassificationRequest request) throws Exception{
        String result = classificationService.update(request);
        if(result!=null){
            return Response.SUCCESS(result);
        }else{
            return Response.SUCCESS();
        }
    }
}
