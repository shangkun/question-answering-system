package cn.ken.question.answering.system.config;

import cn.ken.question.answering.system.common.Constant;
import cn.ken.question.answering.system.memorydb.Admin;
import cn.ken.question.answering.system.model.admin.RoleMenu;
import cn.ken.question.answering.system.model.admin.User;
import cn.ken.question.answering.system.utils.Base;
import cn.ken.question.answering.system.utils.RedisUtils;
import cn.ken.question.answering.system.utils.StringUtils;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
        //权限验证
        if(!validate(url,userString)){
            setResponse(httpServletResponse,"请求非法",401);
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

    /**
     * 请求验证
     * @param url
     * @param userString
     * @return
     */
    public boolean validate(String url,String userString){
        if(StringUtils.isEmpty(userString)){
            return false;
        }
        try {
            User user = gson.fromJson(userString,User.class);
            if(Admin.userMap.get(user.getId())==null  || Admin.roleMap.get(user.getRoleId())==null
                    || Admin.roleMenuMap.size()==0){
                return false;
            }
            if(url.contains("qa/")){
                return true;
            }
            for(RoleMenu roleMenu:Admin.roleMenuMap.values()){
                if(roleMenu.getRoleId().equals(user.getRoleId()) &&
                        Admin.menuMap.get(roleMenu.getMenuId())!=null &&
                        url.contains(Admin.menuMap.get(roleMenu.getMenuId()).getUrl())){
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 设置返回
     * @param response
     * @param message
     * @param code
     */
    public void setResponse(HttpServletResponse response,String message,int code){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()){
            out.append("{\"info\":\""+message+"\",\"status\":"+code+"}");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
