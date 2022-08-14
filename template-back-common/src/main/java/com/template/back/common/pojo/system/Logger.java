package com.template.back.common.pojo.system;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.template.back.common.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 张骞
 * @version 2.0.0
 * @Date 2022年8月14日17:44:48
 * 自定义错误日志数据模型
 */
@ApiModel("自定义错误日志")  //knife4j框架注解，对类的解释说明
@Data  //注解get/set方法
@AllArgsConstructor //注解全参数构造方法
@NoArgsConstructor  //注解无参构造方法
public class Logger implements Serializable {
    //定义序列化id
    private static final Long serialVersionUID = 1L;

    //成员变量
    @ApiModelProperty("主键id")
    @TableId   //注解为主键
    private Long id;

    //记录异常出现的类
    @ApiModelProperty("记录异常出现的类")
    private String classes;

    //记录异常出现的方法
    @ApiModelProperty("记录异常出现的方法")
    private String method;

    //记录操作时出现异常的用户id
    @ApiModelProperty("记录操作时出现异常的用户id")
    private String userId;

    //出现异常的用户姓名
    @ApiModelProperty("出现异常的用户姓名")
    private String nickName;

    //具体的异常信息
    @ApiModelProperty("具体的异常信息")
    private String exception;

    //开发者备注
    @ApiModelProperty("开发者备注")
    private String remark;

    //本表没有乐观锁字段与逻辑删除字段，这里不继承basePojo，所以添加如下字段

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
    private String operator_name;

    //记录当前操作用户id
    @ApiModelProperty("操作用户Id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long operator_id;
}
