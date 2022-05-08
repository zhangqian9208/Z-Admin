package com.template.back.common.pojo.school;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.template.back.common.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 张骞
 * @version 1.0.0
 * 校区数据实体模型
 */
@Data  //注解get/set方法
@AllArgsConstructor //注解全参数构造方法
@NoArgsConstructor  //注解无参构造方法
public class Campus extends BasePojo {
    //成员变量
    @TableId(type = IdType.UUID )   //注解为主键，字段开启驼峰命名法
    private String campusId;    //主键id,使用UUID形式
    private String name;  //学校名称
    private Date coopTime;  //合作时间
    private String sale;    //所属销售
    private String remark;  //校区备注

    //乐观锁字段
    @Version
    private Integer version;
}
