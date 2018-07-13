package cn.ken.questionansweringsystem.config;

import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: ControllerAspect <br/>
 */
@Aspect    //该标签把ControllerAspect类声明为一个切面
@Order(1)  //设置切面的优先级：如果有多个切面，可通过设置优先级控制切面的执行顺序（数值越小，优先级越高）
@Component //该标签把ControllerAspect类放到IOC容器中
public class ControllerAspect extends Base{
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    /**
     * 定义拦截规则：拦截controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* cn.ken.questionansweringsystem.controller.*.*(..))")
    public void controllerAspect() {
    }

    //请求method前打印内容
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //打印请求内容
        logger.info("===============Request Begin===============");
        logger.info("Request From:" + IPUtils.getIpAddress(request));
        logger.info("Request Address:" + request.getRequestURL().toString());
        logger.info("Request Method:" + joinPoint.getSignature());
        logger.info("Request Param:" + getJoinPointStr(joinPoint));
    }


    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturning(Object o) {
        logger.info("Response Content:" + gson.toJson(o));
        logger.info("Response Waste Time:" + (System.currentTimeMillis() - startTime.get()) + "ms");
        logger.info("===============Request End===============");
    }

    @AfterThrowing(pointcut = "controllerAspect()", throwing = "exception")
    public void methodAfterThrowing(JoinPoint joinPoint, Exception exception) {
        //打印请求内容
        logger.error("===============Excetion Coming===============");
        logger.info("Request Method:" + joinPoint.getSignature());
        logger.info("Request Param:" + getJoinPointStr(joinPoint));
        logger.error("Error Info:", exception);
    }


    /**
     * 获取用户请求方法的参数并序列化为JSON格式字符串
     * @param joinPoint
     * @return
     */
    public String getJoinPointStr(JoinPoint joinPoint) {
        String params = "";
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if(args[i]==null){
                    continue;
                }
                Object obj = args[i];
                String s = obj.getClass().toGenericString();
                if(!s.contains("org.apache.catalina") && !s.contains("HttpServlet")){
                    params += gson.toJson(args[i]) + ";";
                }
            }
        }
        return params;
    }
}

