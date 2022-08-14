package com.template.back.common.pojo.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.template.back.common.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 张骞
 * @version 2.0.0
 * @Date 2022年8月14日17:41:04
 * 后台用户数据模型
 */
@ApiModel("后台用户")  //knife4j框架注解，对类的解释说明
@Data  //注解get/set方法
@AllArgsConstructor //注解全参数构造方法
@NoArgsConstructor  //注解无参构造方法
public class Admin extends BasePojo implements Serializable {
    //定义序列化id
    private static final Long serialVersionUID = 1L;

    //成员变量
    @ApiModelProperty("主键id")
    @TableId   //注解为主键
    private Long adminId;  //主键id

    @ApiModelProperty("用户姓名")
    private String name;   //用户姓名

    @ApiModelProperty("用户登录名")
    private String userName;  //用户登录名

    @ApiModelProperty("用户密码")
    private String password;  //用户密码

    @ApiModelProperty("用户状态")
    private Long state;  //用户状态

    @ApiModelProperty("部门id")
    private String deptId;  //部门id

    //关联的部门类型
    @ApiModelProperty("关联的部门类型")
    @TableField(exist = false)  //该字段在数据库中不存在
    private Dept dept;
}
