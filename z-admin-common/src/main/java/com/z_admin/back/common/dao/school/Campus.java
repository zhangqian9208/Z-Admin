package com.z_admin.back.common.dao.school;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.z_admin.back.common.dao.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author 张骞
 * @version 2.0.0
 * @Date 2022年8月14日17:41:04
 * 校区数据实体模型，其他模型可以参考此模型
 */
@ApiModel("校区")  //knife4j框架注解，对类的解释说明
@Data  //注解get/set方法
@AllArgsConstructor //注解全参数构造方法
@NoArgsConstructor  //注解无参构造方法
public class Campus extends BasePojo  implements Serializable {

    //定义序列化id
    private static final Long serialVersionUID = 1L;

    //成员变量
    @ApiModelProperty("主键id")
    @TableId(type = IdType.UUID )   //注解为主键，字段开启驼峰命名法
    private String campusId;    //主键id,使用UUID形式

    @ApiModelProperty("学校名称")
    private String name;  //学校名称

    @ApiModelProperty("合作时间")
    private LocalDate coopTime;  //合作时间


    @ApiModelProperty("所属销售")
    private String sale;    //所属销售

    @ApiModelProperty("校区备注")
    private String remark;  //校区备注

}
