package com.template.back.common.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张骞
 * @version 1.0.1
 * 通用返回结果集合对象
 *   A. 如果业务执行结果为成功, 构建R对象时, 只需要调用 success 方法; 如果需要返回数据传递 object 参数, 如果无需返回, 可以直接传递null。
 *   B. 如果业务执行结果为失败, 构建R对象时, 只需要调用error 方法, 传递错误提示信息即可。
 */
@Data  //注解生成get/set方法
public class R<T> {
    //返回结果集的成员变量
    private Integer code;  //相应编码，1-成功，0或者其他数字-失败
    private String msg;  //错误信息
    private T data;  //数据
    private Map map = new HashMap();  //相应的动态数据

    /**
     * 返回成功数据的结果集
     * @param object
     * @param <T>
     * @return
     */
    public static <T> R<T> success(T object){
        R<T> r = new R<T>();
        r.code=1;
        r.data=object;
        return r;
    }

    /**
     * 返回失败的结果集对象
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> R<T> error(String msg){
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    /**
     * 返回动态map数据的方法
     * @param key
     * @param value
     * @return
     */
    public R<T> add(String key,Object value){
        this.map.put(key,value);
        return this;
    }
}
