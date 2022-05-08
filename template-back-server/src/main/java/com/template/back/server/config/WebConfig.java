package com.template.back.server.config;

import com.template.back.common.mapper.JacksonObjectMapper;
import com.template.back.server.interceptor.AuthorInterceptor;
import com.template.back.server.interceptor.RedisCacheInterceptor;
import com.template.back.server.interceptor.AdminLoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0.1
 * 拦截器配置类，可自定拦截器顺序
 */
@Configuration
@Slf4j
public class WebConfig extends WebMvcConfigurationSupport {
    //注入登录拦截器
    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;
    //注入缓存拦截器
    @Autowired
    private RedisCacheInterceptor redisCacheInterceptor;
    //注入权限拦截器
    @Autowired
    private AuthorInterceptor authorInterceptor;

    /**
     * 将自定义方法加入拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //考虑拦截器的顺序
        //登录使用了过滤器，不再使用拦截器
        //registry.addInterceptor(this.adminLoginInterceptor).addPathPatterns("/**");
        //权限拦截器
        registry.addInterceptor(this.authorInterceptor).addPathPatterns("/**");
        registry.addInterceptor(this.redisCacheInterceptor).addPathPatterns("/**");
    }
    /**
     * 进行静态资源映射的方法
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射……");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
        //配置本地磁盘的静态资源路径
        registry.addResourceHandler("/common/download/**").addResourceLocations("file:E:/001/");
    }

    /**
     * 配置自定义对象转换器的方法
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //01.创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //02.设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //03.将上面的消息转换器对象追加到mvc框架的转换器集合中
        converters.add(0,messageConverter);
    }
}
