package cn.ken.questionansweringsystem.config;

import cn.ken.questionansweringsystem.common.Constant;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author: shangkun <br/>
 * date: 2018/7/17 <br/>
 * what: ControllerHandlerInterceptor <br/>
 */
public class ControllerHandlerInterceptor extends Base implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        logger.info("accessToken:{}",accessToken);
        //直接放开白名单
        if(isWhiteList(url)){
            return true;
        }
        String userString = redisUtils.get(Constant.ACCESS_TOKEN_WITH_PREFIX+accessToken);
        if(userString==null){
            return false;
        }
        redisUtils.set(Constant.ACCESS_TOKEN_WITH_PREFIX+userString,userString, Constant.TIMEOUT);
        logger.info(Constant.printPattern+"preHandle"+Constant.printPattern);
        return true;
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
        logger.info(Constant.printPattern+"postHandle"+Constant.printPattern);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info(Constant.printPattern+"afterCompletion"+Constant.printPattern);
    }
}
