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
 * what: 请求切面 <br/>
 */
@Aspect    //该标签把RequestAspect类声明为一个切面
@Order(1)  //设置切面的优先级：如果有多个切面，可通过设置优先级控制切面的执行顺序（数值越小，优先级越高）
@Component //该标签把RequestAspect类放到IOC容器中
public class RequestAspect extends Base{
    //线程局部变量 用于解决大并发情况下开始时间错乱问题
    ThreadLocal<Long> start = new ThreadLocal();
    private static long count = 0;

    @Pointcut("execution(* cn.ken.questionansweringsystem.controller.*.*(..))")
    public void customAspect() {}

    @Before(value = "customAspect()")
    public void before(JoinPoint joinPoint) {
        start.set(System.currentTimeMillis());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //打印请求内容
        logger.info("Request IP:{}",IPUtils.getIpAddress(request));
        logger.info("Request URL:{}",request.getRequestURL().toString());
        logger.info("Request Method:{}",joinPoint.getSignature());
        logger.info("Request Param:{}",getJoinPointStr(joinPoint));
        logger.info("Request Count:{}",count++);
    }


    @AfterReturning(returning = "o", pointcut = "customAspect()")
    public void afterReturning(Object o) {
        logger.info("Response Content:{}",gson.toJson(o));
        logger.info("Response Waste Time:{}",(System.currentTimeMillis() - start.get()) + "ms");
    }

    @AfterThrowing(pointcut = "customAspect()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        logger.info("Excetion Request Method:{}",joinPoint.getSignature());
        logger.info("Excetion Request Param:{}",getJoinPointStr(joinPoint));
        logger.error("Error Info:",e);
    }

    /**
     * 获取请求参数
     * @param joinPoint
     * @return
     */
    public String getJoinPointStr(JoinPoint joinPoint) {
        String params = "";
        try {
            Object[] args = joinPoint.getArgs();
            if (args == null || args.length == 0) {
                return params;
            }
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
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return params;
    }
}

