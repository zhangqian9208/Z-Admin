package com.z_admin.back.common.utils.annotation;

import java.lang.annotation.*;

/**
 * @author 张骞
 * @version 1.0.0
 * @Description 跳过用户登录验证的注解,前提需要在登录工程中进行验证
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented //标记注解
public @interface NoAuthorization {

}