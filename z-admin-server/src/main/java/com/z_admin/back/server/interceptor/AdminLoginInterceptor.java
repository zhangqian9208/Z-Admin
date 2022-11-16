package com.z_admin.back.server.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.z_admin.back.common.dao.system.Admin;
import com.z_admin.back.common.utils.myself.AdminThreadLocal;
import com.z_admin.back.common.utils.annotation.NoAuthorization;
import com.z_admin.back.common.vo.R;
import com.z_admin.back.server.service.system.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
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

    //knife4j框架的doc文档地址开关
    @Value("${doc-interceptor-state}")
    private Boolean docState;

    //注入业务层对象，用来验证用户登录信息
    @Autowired
    private AdminService adminService;

    //创建路径匹配器对象
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    //注入redis模板类，用于每次验证写入用户登录信息
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private static String sessionId = null;

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
        //00.获取sessionId
        sessionId = request.getSession().getId();

        //01.获取本次请求的URI
        String url = request.getRequestURI();
        log.info("拦截到请求：{}", url);

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

        //2-3.对knife4j框架
        String[] docUrls = new String[]{
                "/doc.html",  //从这里开始，是swagger框架过滤使用
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"
        };

        //2-3-2使用私有方法进行请求匹配
        Boolean docCheck = this.check(docUrls, url);

        //2-3-3如果不需要，则直接放行
        if (docCheck) {
            //判断配置文件的状态，如果为true，则放行，否则不放行
            if(docState){
                log.info("放行knife4j请求{}", url);
                return true;
            }else {
                //如果配置文件为false，则始终返回false
                return false;
            }
        }


        //2-4.判断本次请求, 是否需要登录, 才可以访问
        //2-4-1定义需要过滤的数组
        String[] urls = new String[]{
                "/system/user/login",
                "/system/user/logout",
                "/backend/**",
                "/common/**",
                "/upload/**",
                "/backend/pages/login/login.html",
                "/captcha/**",  // 验证码请求地址
        };
        //2-4-2使用私有方法进行请求匹配
        Boolean check = this.check(urls, url);

        //2-4-3如果不需要，则直接放行
        if (check) {
            log.info("本次请求{}不需要进行登录拦截", url);
            return true;
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
                    //04-3-2重新写入session,为session延时
                    request.getSession().setAttribute("admin", admin);
                    log.info("过滤器层返回数据：后台用户校验成功！");
                    return true;
                }
            }
        }

        //03用户状态验证失败，返回到登录页面
//        response.sendRedirect(request.getContextPath() + "/backend/pages/login/login.html");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
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

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param urls
     * @param uri
     * @return
     */
    private Boolean check(String[] urls, String uri) {
        //01.遍历要放行的请求集合
        for (String url : urls) {
            //02.调用请求匹配器的方法
            boolean match = PATH_MATCHER.match(url, uri);
            //03.如果匹配，返回true
            if (match) {
                return true;
            }
        }
        //04.如果不匹配，返回false
        return false;
    }
}


