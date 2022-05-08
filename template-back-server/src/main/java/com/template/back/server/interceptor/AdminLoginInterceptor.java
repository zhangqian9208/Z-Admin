package com.template.back.server.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.utils.AdminThreadLocal;
import com.template.back.common.utils.NoAuthorization;
import com.template.back.server.service.system.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张骞
 * @version 1.2
 * 验证后台用户登录的拦截器，并且将用户信息写入AdminThreadLocal
 */
@Component   //注解加载到spring容器中
@Slf4j  //日志注解
public class AdminLoginInterceptor implements HandlerInterceptor {
    //注入业务层对象，用来验证用户登录信息
    @Autowired
    private AdminService adminService;

    //注入redis模板类，用于每次验证写入用户登录信息
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

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
        //将Admin对象写入到ThreadLocal中
        //校验当前的Handler
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //判断当前请求是否添加了NoAuthorization注解,如果添加了就跳过验证
        if (((HandlerMethod) handler).hasMethodAnnotation(NoAuthorization.class)) {
            return true;
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NoAuthorization noAnnotation = handlerMethod.getMethod().getAnnotation(NoAuthorization.class);
            if (noAnnotation != null) {
                // 如果该方法被标记为无需验证登录，直接返回即可
                return true;
            }
        }

        //01.从session中拿到当前登录的用户数据
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        //02.验证数据是否为空，如果不为空，则在数据库中查询用户信息
        if(ObjectUtil.isNotEmpty(admin)){
            //调用业务层验证的方法
            if(null != admin.getAdminId() || null != admin.getUserName() || null!= admin.getPassword()){
                Admin admin1 = this.adminService.findAdminById(admin.getAdminId(),admin.getUserName(),admin.getPassword());
                if(null != admin1){
                    //用户验证成功，写入数据
                    AdminThreadLocal.set(admin1);
                    //将用户登录的状态数据写入redis
                    log.info("过滤器层返回数据：后台用户校验成功！");
                    return true;
                }
            }
        }

        //03用户状态验证失败，返回到登录页面
        response.sendRedirect(request.getContextPath() + "/backend/pages/login/login.html");
        log.error("过滤器层返回数据：用户登录验证失败！");
        return false;
    }

    /**
     * 请求结束的后置处理，将User对象从ThreadLocal中删除
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //将User对象从ThreadLocal中删除
        AdminThreadLocal.remove();
        //把登录状态信息从redis中删除
    }
}


