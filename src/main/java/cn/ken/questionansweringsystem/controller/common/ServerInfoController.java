package cn.ken.questionansweringsystem.controller.common;

import cn.ken.questionansweringsystem.model.response.Response;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.SigarUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * author: shangkun <br/>
 * date: 2018/7/24 <br/>
 * what: 服务器信息 <br/>
 */
@RestController
@RequestMapping(value = "/api/common/server")
@Api(tags="服务器信息")
public class ServerInfoController extends Base{

    @ApiOperation(value = "获取服务器信息", notes = "获取服务器信息")
    @RequestMapping(value = "/info/get", method = RequestMethod.POST)
    @ResponseBody
    public Response get() throws Exception{
        return Response.SUCCESS(SigarUtils.property());
    }

}
