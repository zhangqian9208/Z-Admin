package com.z_admin.back.server.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.z_admin.back.server.interceptor.AdminLoginInterceptor;
import com.z_admin.back.server.interceptor.AuthorInterceptor;
import com.z_admin.back.common.mapper.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0.1
 * 拦截器配置类，可自定拦截器顺序
 */
@Configuration
@Slf4j
//使用swagger配置
@EnableSwagger2
//使用Knife4j配置
@EnableKnife4j
public class WebConfig extends WebMvcConfigurationSupport {

    //注入登录拦截器
    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;


    //注入权限拦截器
    @Autowired
    private AuthorInterceptor authorInterceptor;

    /**
     * 定义前端资源需要放行的部分
     */
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/backend/", "classpath:/public/" };

    /**
     * 将自定义方法加入拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //考虑拦截器的顺序
        //登录使用了过滤器，不再使用拦截器
        registry.addInterceptor(this.adminLoginInterceptor).addPathPatterns("/**");
        //权限拦截器
        registry.addInterceptor(this.authorInterceptor).addPathPatterns("/**");
    }
    /**
     * 进行静态资源映射的方法
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射……");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        //注册前端资源，避免前端js和css无法被加载
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        //配置本地磁盘的静态资源路径
        registry.addResourceHandler("/common/download/**").addResourceLocations("file:E:/001/");
        //配置swagger资源（下面两个都是）
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 自定义首页地址
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //登录的路径
        registry.addViewController("/").setViewName("forward:/backend/pages/login/login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
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

    /**
     * knife4j框架配置使用
     * @return
     */
    @Bean
    public Docket createRestApi() {
        // 文档类型
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.z_admin.back.server.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * knife4j框架配置使用
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Z-Admin")
                .version("2.1")
                .description("Z-Admin后台管理接口文档")
                .build();
    }

}
