package com.template.back.common.pojo.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.template.back.common.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色模块数据模型
 */
@Data    //注解生成get/set方法
@AllArgsConstructor   //注解生成全参构造
@NoArgsConstructor    //注解生成无参构造
public class Role extends BasePojo {
    //创建成员变量
    @TableId   //注解为主键
    private Long roleId;
    private String name;  //名称
    private String remark;  //描述信息
    @TableField(exist = false)  //该字段在数据库中不存在
    private String checked; //用于用户与角色关联回显数据，标记当前用户和角色的关联关系

    @Version  //乐观锁字段
    private Integer version;
}
