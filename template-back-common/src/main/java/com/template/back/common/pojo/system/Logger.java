package com.template.back.common.pojo.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.template.back.common.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张骞
 * @version 1.0.0
 * 记录日志数据模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logger extends BasePojo {
    //日志记录的成员变量信息

    //用户id，在配置文件中设置为自增长
    @TableId   //注解为主键
    private Long id;
    //记录异常出现的类
    private String classes;
    //记录异常出现的方法
    private String method;
    //记录操作时出现异常的用户id
    private String userId;
    //出现异常的用户姓名
    private String nickName;
    //具体的异常信息
    private String exception;
    //开发者备注
    private String remark;
}
