package cn.ken.question.answering.system.config;

import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.common.Constant;
import cn.ken.question.answering.system.model.response.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 全局异常处理 <br/>
 */
@ControllerAdvice
public class GlobalExceptionHandler extends Base {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("request url:{"+req.getRequestURL()+"}  error info:{"+e.getMessage()+"}",e);
        return Response.FAIL(Constant.ERROR_INFO);
    }
}

