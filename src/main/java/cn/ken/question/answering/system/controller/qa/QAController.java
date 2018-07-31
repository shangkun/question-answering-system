package cn.ken.question.answering.system.controller.qa;

import cn.ken.question.answering.system.model.configuration.Configuration;
import cn.ken.question.answering.system.model.qa.QuestionAnswer;
import cn.ken.question.answering.system.model.statistics.KnowledgeRanking;
import cn.ken.question.answering.system.service.statistics.KnowledgeRankingService;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.utils.StringUtils;
import cn.ken.question.answering.system.model.response.Response;
import cn.ken.question.answering.system.service.qa.QAService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/25 <br/>
 * what: 问答 <br/>
 */
@RestController
@RequestMapping(value = "/api/qa")
@Api(tags="问答")
public class QAController extends Base {
    @Autowired
    private QAService qaService;
    @Autowired
    private KnowledgeRankingService knowledgeRankingService;

    @ApiOperation(value = "获取问答配置", notes = "获取问答配置")
    @RequestMapping(value = "/config/get", method = RequestMethod.GET)
    @ResponseBody
    public Response config() throws Exception{
        Configuration result= qaService.getConfig();
        if(result==null){
            return Response.SUCCESS("查询为空");
        }
        return Response.SUCCESS(result);
    }

    @ApiOperation(value = "问答请求", notes = "问答请求")
    @RequestMapping(value = "/request", method = RequestMethod.POST)
    @ResponseBody
    public Response qa(@RequestBody QuestionAnswer questionAnswer) throws Exception{
        QuestionAnswer result = qaService.qa(questionAnswer);
        if(!StringUtils.isEmpty(result.getMessage())){
            return Response.SUCCESS(result.getMessage());
        }
        return Response.SUCCESS(result);
    }

    @ApiOperation(value = "问题推荐", notes = "问题推荐")
    @RequestMapping(value = "/suggest", method = RequestMethod.POST)
    @ResponseBody
    public Response suggest(@RequestBody QuestionAnswer questionAnswer) throws Exception{
        List<String> result= qaService.suggest(questionAnswer);
        if(CollectionUtils.isEmpty(result)){
            return Response.SUCCESS("查询为空");
        }
        return Response.SUCCESS(result);
    }

    @ApiOperation(value = "获取当前热点问题", notes = "获取当前热点问题")
    @RequestMapping(value = "/hot/question/get", method = RequestMethod.POST)
    @ResponseBody
    public Response getHotQuestion(@RequestBody QuestionAnswer questionAnswer) throws Exception{
        List<KnowledgeRanking> knowledgeRankingList = knowledgeRankingService.getHotKnowledge(questionAnswer.getChannelId());
        if(CollectionUtils.isEmpty(knowledgeRankingList)){
            return Response.SUCCESS("查询为空");
        }
        return Response.SUCCESS(knowledgeRankingList);
    }
}
