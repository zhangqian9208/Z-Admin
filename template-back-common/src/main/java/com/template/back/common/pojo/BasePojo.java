package com.template.back.common.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 张骞
 * @version 1.0.0
 * @Description 自动填充新增日期和修改日期，需要配合handler
 */
@Data
public abstract class BasePojo {
    //新增时的保存时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime created;
    //新增和修改时的保存时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updated;

    //记录当前操作人信息,不进行自动填充，根据需求在业务层自行填充
    private String operator;
}
