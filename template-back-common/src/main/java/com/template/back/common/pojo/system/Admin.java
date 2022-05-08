package com.template.back.common.pojo.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.template.back.common.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后台管理员数据模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends BasePojo {
    @TableId   //注解为主键
    private Long adminId;  //主键id
    private String name;   //用户姓名
    private String userName;  //用户登录名
    private String password;  //用户密码
    private Long state;  //用户状态
    private String deptId;  //部门id

    //关联的部门类型
    @TableField(exist = false)  //该字段在数据库中不存在
    private Dept dept;

    //乐观锁字段
    @Version
    private Integer version;
}
