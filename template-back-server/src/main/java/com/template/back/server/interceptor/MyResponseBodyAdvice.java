package com.template.back.server.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.back.common.utils.CaChe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.util.concurrent.TimeUnit;

/**
 * @author 张骞
 * @version 1.0
 * 缓存响应及写入功能
 */
@ControllerAdvice
@Slf4j
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    @Value("${tanhua.cache.enable}")  //注入全局变量的设置值
    private Boolean enable;
    @Autowired  //注入redis模板类
    private RedisTemplate<String,String> redisTemplate;
    //序列化对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 判断当前全局设置是否支持本操作
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //需要判断三个条件：配置文件中全局开关处于开启状态；是get请求；包含了@Cache注解
        //三个条件同时满足，flag为true
        boolean flag = enable && methodParameter.hasMethodAnnotation(GetMapping.class) && methodParameter.hasMethodAnnotation(CaChe.class);
        return flag;
    }

    /**
     * 输出之前的操作，缓存未命中的话，在这里重新写入缓存
     * @param o  返回的数据
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //如果传入的数据为空,则不需要处理
        if(null == o){
            return null;
        }

        try{
            //将数据写入缓存
            String redisValue = null;
            //判断o的类型是不是String类型
            if(o instanceof String){
                redisValue = (String) o;
            }else {
                String string = MAPPER.writeValueAsString(o);
                redisValue = string;
            }

            //调用拦截器的方法，生成redisKey
            String redisKey = RedisCacheInterceptor.createRedisKey(((ServletServerHttpRequest)serverHttpRequest).getServletRequest());

            //获取超时时间
            CaChe caChe = methodParameter.getMethodAnnotation(CaChe.class);

            //缓存的时间单位是秒
            this.redisTemplate.opsForValue().set(redisKey,redisValue,Long.valueOf(caChe.time()), TimeUnit.SECONDS);

            //输出日志
            log.info("数据已经写入缓存！");

        }catch (Exception exception){
            log.error("将数据写入缓存失败！错误详情:"+exception);
        }



        return o;
    }
}
