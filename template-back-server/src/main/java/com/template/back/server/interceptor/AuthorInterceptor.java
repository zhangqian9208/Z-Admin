package com.template.back.server.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.template.back.common.pojo.system.Module;
import com.template.back.common.utils.AdminThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张骞
 * @version 1.2
 * 后台权限拦截器
 */
@Component   //注解加载到spring容器中
@Slf4j  //日志注解
public class AuthorInterceptor implements HandlerInterceptor {

    //权限过滤器开关
    @Value("${author-interceptor-state}")
    private Boolean state;

    /**
     * 请求的前置处理，将User对象写入到ThreadLocal中
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.判断权限过滤器开关状态
        if(ObjectUtil.isNotEmpty(state)){
            if(!state){
                return true;
            }
        }

        //2.校验当前的Handler
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //3.获取用户当前的操作
        //3.1获取用户请求的URI
        String uri = request.getRequestURI();

        //中间加入：需要对一部分操作放行，否则登录/进入主页等操作会报空指针异常
        //.css .js .png .jpg .index
        if(uri.endsWith(".css")
                ||uri.endsWith(".js")
                ||uri.endsWith(".png")
                ||uri.endsWith(".jpg")
                ||uri.endsWith("index.jsp")
                ||uri.endsWith("login.jsp")
                ||uri.endsWith("unauthorized.jsp")
                ||uri.equals(null)){
            return true;
        }

        //3.2对用户当前的请求进行处理
        String url = uri.substring(1);
        log.info("当前请求的地址为："+url);

        //对部分请求的全部路径放行
        if(StrUtil.isNotEmpty(url)){
            if(url.equals("")||url.equals("")){
                return true;
            }
        }

        //注意：对部分用户的请求参数值进行放行
        // error错误页面请求
        // author为角色授权按钮（主页刷新）
        // findById为修改回显的请求
        if(StrUtil.isNotEmpty(url)){
            if(url.endsWith("login")
                    || url.endsWith("home")
                    || url.endsWith("logout")
                    || url.endsWith("error")
                    || url.endsWith("author")
                    || url.endsWith("findById")
            ){
                return true;
            }
        }

        //4.获取到当前登录人允许的操作
        String authorStr = request.getSession().getAttribute("authorStr").toString();
        log.info("当前的权限集合为："+authorStr);

        //5.比对本次操作是否在当前登录人员允许的操作范围内
        if(authorStr.contains(url)){
            //如果在，则放行
            return true;
        }else {
            //如果不在，则阻止访问
            //03用户状态验证失败，返回到登录页面
            response.sendRedirect(request.getContextPath() + "pages/error/error.jsp");
            return false;
        }
    }

    /**
     * 请求结束的后置处理，不需要做处理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //不需要做处理
    }
}


