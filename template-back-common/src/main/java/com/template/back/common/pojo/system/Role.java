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
 * @Date 2022年8月14日18:25:30
 * 后台用户角色数据模型
 */
@ApiModel("后台用户角色")  //knife4j框架注解，对类的解释说明
@Data    //注解生成get/set方法
@AllArgsConstructor   //注解生成全参构造
@NoArgsConstructor    //注解生成无参构造
public class Role extends BasePojo implements Serializable {

    //定义序列化id
    private static final Long serialVersionUID = 1L;

    //创建成员变量
    @ApiModelProperty("主键id")
    @TableId   //注解为主键
    private Long roleId;

    @ApiModelProperty("角色名称")
    private String name;  //名称

    @ApiModelProperty("描述信息")
    private String remark;  //描述信息

    @ApiModelProperty("用户与角色关联回显数据")
    @TableField(exist = false)  //该字段在数据库中不存在
    private String checked; //用于用户与角色关联回显数据，标记当前用户和角色的关联关系

}
