package cn.ken.questionansweringsystem.config;

import cn.ken.questionansweringsystem.common.Constant;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.IPUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author: shangkun <br/>
 * date: 2018/7/17 <br/>
 * what: MineHandlerInterceptor <br/>
 */
public class MineHandlerInterceptor extends Base implements HandlerInterceptor {
    ThreadLocal<Long> start = new ThreadLocal();
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        start.set(System.currentTimeMillis());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        //直接放开白名单
        if(isWhiteList(url)){
            return true;
        }
        logger.info("=============preHandle=============");

        logger.info("Request IP:{}", IPUtils.getIpAddress(request));
        logger.info("Request URL:{}",url);
        return false;
    }

    /**
     * 判断是否白名单
     * @param url
     * @return
     */
    public boolean isWhiteList(String url){
        for(String white:Constant.whiteList){
            if(url.contains(white)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("=============postHandle=============");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("=============afterCompletion=============");
        logger.info("Response Waste Time:{}",(System.currentTimeMillis() - start.get()) + "ms");
    }
}
