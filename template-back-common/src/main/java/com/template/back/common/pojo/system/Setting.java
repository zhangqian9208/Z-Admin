package com.template.back.common.pojo.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.template.back.common.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张骞
 * @version 1.0.0
 * 自定义可读取参数数据模型
 * 键值对模式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Setting extends BasePojo {
    //成员变量
    @TableId   //注解为主键
    private Long id;
    private String sort;   //排序字段
    private String name;  //参数名称
    private String param; //具体参数
    private String remark;  //备注

    @Version  //乐观锁字段
    private Integer version;
}
