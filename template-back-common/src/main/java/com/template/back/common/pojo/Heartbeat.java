package com.template.back.common.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张骞
 * @version 1.0.0
 * 心跳连接表，用于查询
 */
@ApiModel("心跳连接模拟数据")  //knife4j框架注解，对类的解释说明
@Data  //注解get/set方法
public class Heartbeat {
    //成员变量
    @ApiModelProperty("主键Id")
    @TableId   //注解为主键
    private Long id;  //id

    @ApiModelProperty("名称")
    private String name;   //名称
}
