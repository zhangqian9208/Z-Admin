package com.template.back.common.utils;

import java.lang.annotation.*;

/**
 * @author 张骞
 * @version 1.0.0
 * @Description 被标记为Cache的Controller进行缓存，其他情况不进行缓存
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented //标记注解
public @interface CaChe {
    /**
     * 缓存时间，可以在注解中修改
     * @return
     */
    String time() default "60";
}
