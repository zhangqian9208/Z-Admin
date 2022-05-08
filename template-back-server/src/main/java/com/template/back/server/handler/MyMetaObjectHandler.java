package com.template.back.server.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 张骞
 * @version 1.0.1
 * 自动填充新增和更改的日期
 */
//注解为spring的bean
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //新增数据时的方法
    @Override
    public void insertFill(MetaObject metaObject) {
        //处理pojo的created字段
        Object created = getFieldValByName("created", metaObject);
        if(null == created){
            //如果字段为空，则填充新增字段
            setFieldValByName("created",LocalDateTime.now(),metaObject);
        }
        //处理pojo的update字段
        Object updated = getFieldValByName("updated", metaObject);
        if(null == updated){
            //如果字段为空，则填充更新字段
            setFieldValByName("updated",LocalDateTime.now(),metaObject);
        }
    }

    //更新数据时的方法
    @Override
    public void updateFill(MetaObject metaObject) {
        //无论数据是否为空，每次更新，均更新该字段
        setFieldValByName("updated",LocalDateTime.now(),metaObject);
    }
}
