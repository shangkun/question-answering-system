package cn.ken.question.answering.system.controller.statistics;

import cn.ken.question.answering.system.model.response.Response;
import cn.ken.question.answering.system.model.statistics.ResponseTypeStatistics;
import cn.ken.question.answering.system.model.statistics.ResponseTypeStatisticsRequest;
import cn.ken.question.answering.system.service.statistics.ResponseTypeStatisticsService;
import cn.ken.question.answering.system.utils.Base;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/31 <br/>
 * what: 回复类型统计 <br/>
 */
@RestController
@RequestMapping(value = "/api/statistics/response/type")
@Api(tags="回复类型统计")
public class ResponseTypeStatisticsController extends Base {
    @Autowired
    private ResponseTypeStatisticsService responseTypeStatisticsService;

    @ApiOperation(value = "获取回复类型统计数据", notes = "获取回复类型统计数据")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response get(@RequestBody ResponseTypeStatisticsRequest request) throws Exception{
        List<ResponseTypeStatistics> responseTypeStatisticsList = responseTypeStatisticsService.get(request);
        return Response.SUCCESS(responseTypeStatisticsList);
    }
}
