package cn.ken.question.answering.system.config;

import cn.ken.question.answering.system.common.Constant;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.utils.RedisUtils;
import cn.ken.question.answering.system.utils.StringUtils;
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
        //直接放开白名单
        if(isWhiteList(url)){
            return true;
        }
        if(StringUtils.isEmpty(accessToken)){
            logger.error("this request:{} without access_token",url);
        }
        String userString = redisUtils.get(Constant.ACCESS_TOKEN_WITH_PREFIX+accessToken);
        if(userString==null){
            logger.error("illegal access url:{}",url);
            return false;
        }
        //访问令牌续期
        redisUtils.set(Constant.ACCESS_TOKEN_WITH_PREFIX+accessToken,userString,Constant.TIMEOUT);
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
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
