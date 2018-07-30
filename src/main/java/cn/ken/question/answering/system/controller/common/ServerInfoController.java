package cn.ken.question.answering.system.controller.common;

import cn.ken.question.answering.system.model.response.Response;
import cn.ken.question.answering.system.model.statistics.InfoStatistics;
import cn.ken.question.answering.system.service.statistics.InfoStatisticsService;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.utils.ServerInfoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/24 <br/>
 * what: 服务器信息 <br/>
 */
@RestController
@RequestMapping(value = "/api/common/server")
@Api(tags="服务器信息")
public class ServerInfoController extends Base {
    @Autowired
    private InfoStatisticsService infoStatisticsService;

    @ApiOperation(value = "获取服务器信息", notes = "获取服务器信息")
    @RequestMapping(value = "/info/get", method = RequestMethod.POST)
    @ResponseBody
    public Response get() throws Exception{
        return Response.SUCCESS(ServerInfoUtils.property());
    }

    @ApiOperation(value = "获取信息统计", notes = "获取信息统计")
    @RequestMapping(value = "/info/statistics", method = RequestMethod.POST)
    @ResponseBody
    public Response statistics() throws Exception{
        List<InfoStatistics> infoStatisticsList = infoStatisticsService.get();
        return Response.SUCCESS(infoStatisticsList);
    }

}
