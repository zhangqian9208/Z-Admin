package com.z_admin.back.common.utils.myself;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.z_admin.back.common.mapper.system.SettingMapper;
import com.z_admin.back.common.dao.system.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 张骞
 * @version 1.0.0
 * 自定义数据库参数工具类
 */
@Component
public final class SettingUtils {

    //注入
    @Autowired
    private SettingMapper settingMapper;

    private static SettingUtils settingUtils;

    @PostConstruct
    public void init() {
        settingUtils = this;
        settingUtils.settingMapper = this.settingMapper;
    }

    //私有化构造方法
    private SettingUtils(){}

    /**
     * 获取一个参数的方法
     * @param settingName  传入参数名
     * @return  返回参数值
     */
    public static String getOneParam(String settingName){
        //构造查询方法
        LambdaQueryWrapper<Setting> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Setting::getName,settingName);
        //查询数据
        Setting setting = settingUtils.settingMapper.selectOne(queryWrapper);
        //返回数据
        return setting.getParam();
    }

}
