package cn.ken.question.answering.system.controller.configuration;

import cn.ken.question.answering.system.model.configuration.Configuration;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.model.response.Response;
import cn.ken.question.answering.system.service.configuration.ConfigurationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author: shangkun <br/>
 * date: 2018/7/24 <br/>
 * what: 配置接口 <br/>
 */
@RestController
@RequestMapping(value = "/api/config")
@Api(tags="配置接口")
public class ConfigurationController extends Base {
    @Autowired
    private ConfigurationService configurationService;

    @ApiOperation(value = "获取问答配置", notes = "获取问答配置")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Response get() throws Exception{
        return Response.SUCCESS(configurationService.get());
    }

    @ApiOperation(value = "修改问答配置", notes = "修改问答配置")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(Configuration configuration) throws Exception{
        String result = configurationService.update(configuration);
        if(result!=null){
            return Response.FAIL(result);
        }
        return Response.SUCCESS();
    }
}
