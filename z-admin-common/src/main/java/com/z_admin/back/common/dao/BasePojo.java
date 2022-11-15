package com.z_admin.back.common.dao;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 张骞
 * @version 1.0.0
 * @Date 2022年8月14日18:39:33
 * @Description 自动填充新增日期和修改日期，需要配合handler
 */
@ApiModel("公共类")  //knife4j框架注解，对类的解释说明
@Data  //注解get/set方法
public abstract class BasePojo {

    //新增时的保存时间
    @ApiModelProperty("新增时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime created;

    //新增和修改时的保存时间
    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updated;

    //记录当前操作用户姓名
    @ApiModelProperty("操作用户名称")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String operatorName;

    //记录当前操作用户id
    @ApiModelProperty("操作用户Id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long operatorId;

    //乐观锁字段
    @ApiModelProperty("乐观锁字段")
    @Version
    private Integer version;


}
