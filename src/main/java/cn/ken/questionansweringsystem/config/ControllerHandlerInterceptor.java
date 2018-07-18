package cn.ken.questionansweringsystem.config;

import cn.ken.questionansweringsystem.common.Constant;
import cn.ken.questionansweringsystem.utils.Base;
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
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        //直接放开白名单
        if(isWhiteList(url)){
            return true;
        }
        logger.info(Constant.printPattern+"preHandle"+Constant.printPattern);
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
        logger.info(Constant.printPattern+"postHandle"+Constant.printPattern);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info(Constant.printPattern+"afterCompletion"+Constant.printPattern);
    }
}
