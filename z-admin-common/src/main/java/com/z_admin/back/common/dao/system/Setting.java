package com.z_admin.back.common.dao.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.z_admin.back.common.dao.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 张骞
 * @version 1.0.0
 * @Date 2022年8月14日18:27:11
 * 自定义可读取参数数据模型
 * 键值对模式
 */
@ApiModel("后台自定义设置")  //knife4j框架注解，对类的解释说明
@Data  //注解get/set方法
@AllArgsConstructor //注解全参数构造方法
@NoArgsConstructor  //注解无参构造方法
public class Setting extends BasePojo implements Serializable {
    //定义序列化id
    private static final Long serialVersionUID = 1L;

    //成员变量
    @ApiModelProperty("主键id")
    @TableId   //注解为主键
    private Long id;

    @ApiModelProperty("排序字段")
    private String sort;   //排序字段

    @ApiModelProperty("参数名称")
    private String name;  //参数名称

    @ApiModelProperty("具体参数")
    private String param; //具体参数

    @ApiModelProperty("备注")
    private String remark;  //备注

    @ApiModelProperty("逻辑删除字段")
    @TableLogic
    private Integer deleted;
}
