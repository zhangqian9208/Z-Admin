package com.template.back.common.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张骞
 * @version 1.0.0
 * 心跳连接表，用于查询
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Heartbeat {
    //成员变量
    @TableId   //注解为主键
    private Long id;  //id
    private String name;   //名称
}
