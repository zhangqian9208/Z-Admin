package com.template.back.server.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.back.common.utils.CaChe;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张骞
 * @version 1.0
 * 缓存过滤功能
 */
@Component  //注解为Spring的bean进行管理
@Slf4j
public class RedisCacheInterceptor implements HandlerInterceptor {
    @Value("${tanhua.cache.enable}")  //注入配置文件中的全局缓存开关数据
    private Boolean enable;
    @Autowired  //注入redis模板类，对缓存进行操作
    private RedisTemplate<String, String> redisTemplate;
    //对数据进行序列化
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 请求的前置处理，起到拦截并且写入缓存的作用
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置缓存全局开关校验
        if (!enable) {
            return true;  //如果开关没有开启,则直接返回true,进行放行
        }

        //校验当前的Handler
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //判断当前请求是否为get
        if (!((HandlerMethod) handler).hasMethodAnnotation(GetMapping.class)) {
            return true;
        }

        //判断当前请求是否添加了Cache注解
        if (!((HandlerMethod) handler).hasMethodAnnotation(CaChe.class)) {
            return true;
        }

        //缓存命中
        //根据方法生成redisKey
        String redisKey = createRedisKey(request);
        //根据redisKey进行数据查询
        String cacheData = this.redisTemplate.opsForValue().get(redisKey);
        //如果查询的数据为空，表示缓存没有命中
        if(StringUtils.isEmpty(cacheData)){
            log.info("当前请求没有命中缓存！");
            return true;
        }

        //如果缓存命中，进行输出
        //设置字符集及数据信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //输出查询的数据
        response.getWriter().write(cacheData);
        log.info("当前请求已经命中缓存！");
        return false;   //默认返回false，意味着进行拦截
    }

    /**
     * 生成redis中的key，规则：SERVER_CACHE_DATA_MD5(url + param + token)
     *
     * @param request
     * @return
     */
    public static String createRedisKey(HttpServletRequest request) throws Exception {
        String url = request.getRequestURI();   //请求的url
        //Map<String, String[]> parameterMap = request.getParameterMap();
        String param = MAPPER.writeValueAsString(request.getParameterMap());  //请求的param
        String token = request.getHeader("Authorization");  //从请求头中拿到token数据

        String data = url + "_" + param + "_" + token;
        return "SERVER_CACHE_DATA_" + DigestUtils.md5Hex(data);
    }
}
