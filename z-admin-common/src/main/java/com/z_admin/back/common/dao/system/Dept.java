package com.z_admin.back.common.dao.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.z_admin.back.common.dao.BasePojo;
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
 * 后台部门数据模型
 */
@ApiModel("部门")  //knife4j框架注解，对类的解释说明
@Data  //注解get/set方法
@AllArgsConstructor //注解全参数构造方法
@NoArgsConstructor  //注解无参构造方法
public class Dept extends BasePojo implements Serializable {
    //定义序列化id
    private static final Long serialVersionUID = 1L;

    //成员变量
    @ApiModelProperty("主键id")
    @TableId   //注解为主键
    private Long deptId;  //部门id

    @ApiModelProperty("部门名称")
    private String deptName;   //部门名称

    @ApiModelProperty("备注")
    private String remark;   //备注

}
