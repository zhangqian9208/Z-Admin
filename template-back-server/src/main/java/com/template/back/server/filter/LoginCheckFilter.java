package com.template.back.server.filter;

import com.alibaba.fastjson.JSON;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.utils.AdminThreadLocal;
import com.template.back.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张骞
 * @version 1.0.0
 * 用户登录过滤器，拦截器使用有些问题，所以使用过滤器
*/
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //创建路径匹配器对象
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //01.获取本次请求的URI
        String url = request.getRequestURI();
        //log.info("拦截到请求：{}", url);

        //02.判断本次请求, 是否需要登录, 才可以访问
        //2.1定义不需要过滤的数组
        String[] urls = new String[]{
                "/system/user/login",
                "/system/user//logout",
                "/backend/**",
                "/common/**",
                "/upload/**",
                "/backend/pages/login/login.html"
        };
        //2.2使用私有方法进行请求匹配
        Boolean check = this.check(urls, url);

        //03.如果不需要，则直接放行
        if (check) {
            //log.info("本次请求{}不需要进行登录拦截", url);
            filterChain.doFilter(request,response);
            return;
        }

        //04-1.判断后台用户登录状态，如果已登录，则直接放行
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (null != admin) {
            log.info("用户已登录，用户id为：{}", request.getSession().getAttribute("admin"));
            //将登录的用户id放入ThreadLocal
            AdminThreadLocal.set(admin);
            //log.info("过滤器层返回数据：后台用户校验成功！");
            //放行操作
            filterChain.doFilter(request,response);
            return;
        }

        //05.如果未登录, 则返回未登录结果
        log.info("当前用户未登录！");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        //返回false，如果返回true的话，write方法会报错
        return;
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
