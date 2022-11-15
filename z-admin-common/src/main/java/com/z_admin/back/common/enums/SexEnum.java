package com.z_admin.back.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author 张骞
 * @version 1.0.0
 * @Description 用户性别枚举模板类，主要作为框架枚举模板，实现了mybatisplus中的枚举类型
 */
public enum SexEnum implements IEnum<Integer> {
    MAN(1,"男"),
    WOMAN(2,"女"),
    UNKNOWN(3,"未知");

    private int value;
    private String desc;

    SexEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
