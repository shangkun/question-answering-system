package cn.ken.question.answering.system.controller.statistics;

import cn.ken.question.answering.system.model.response.PageData;
import cn.ken.question.answering.system.model.response.Response;
import cn.ken.question.answering.system.model.statistics.KnowledgeRankingRequest;
import cn.ken.question.answering.system.service.statistics.KnowledgeRankingService;
import cn.ken.question.answering.system.utils.Base;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author: shangkun <br/>
 * date: 2018/7/31 <br/>
 * what: 知识点排名 <br/>
 */
@RestController
@RequestMapping(value = "/api/statistics/knowledge/ranking")
@Api(tags="知识点排名")
public class KnowledgeRankingController extends Base {
    @Autowired
    private KnowledgeRankingService knowledgeRankingService;

    @ApiOperation(value = "获取知识点排名数据", notes = "获取知识点排名数据")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response get(@RequestBody KnowledgeRankingRequest knowledgeRankingRequest) throws Exception{
        PageData pageData = knowledgeRankingService.get(knowledgeRankingRequest);
        return Response.SUCCESS(pageData);
    }
}
