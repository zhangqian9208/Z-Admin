package com.template.back.common.pojo.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.template.back.common.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后台管理员关联部门用户模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept extends BasePojo {
    //成员变量
    @TableId   //注解为主键
    private Long deptId;  //部门id
    private String deptName;   //部门名称
    private String remark;   //备注

    @Version  //乐观锁字段
    private Integer version;
}
