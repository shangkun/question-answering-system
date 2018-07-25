package cn.ken.questionansweringsystem.controller.configuration;

import cn.ken.questionansweringsystem.model.configuration.Configuration;
import cn.ken.questionansweringsystem.model.response.Response;
import cn.ken.questionansweringsystem.service.configuration.ConfigurationService;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.SigarUtils;
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
public class ConfigurationController extends Base{
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
