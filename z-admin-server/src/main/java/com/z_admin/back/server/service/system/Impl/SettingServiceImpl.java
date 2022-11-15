package com.z_admin.back.server.service.system.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z_admin.back.common.mapper.system.SettingMapper;
import com.z_admin.back.common.dao.system.Admin;
import com.z_admin.back.common.dao.system.Setting;
import com.z_admin.back.common.utils.myself.AdminThreadLocal;
import com.z_admin.back.server.service.system.SettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0
 * 设置管理业务层
 */
@Service  //标记为SpringBoot管理的业务层
@Slf4j  //日志注解
public class SettingServiceImpl extends ServiceImpl<SettingMapper,Setting> implements SettingService {
    //注入mapper对象
    @Autowired
    private SettingMapper settingMapper;

    /**
     * 分页查询的方法
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page findPage(Integer page, Integer pageSize,String name) {
        //01.配置MP的查询条件，根据时间排序
        QueryWrapper<Setting> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if(StrUtil.isNotEmpty(name)){
            queryWrapper.like("name",name);
        }
        //02.配置分页数据
        Page<Setting> pageSetting = new Page<>(page,pageSize);
        //03.查询数据
        this.settingMapper.selectPage(pageSetting, queryWrapper);
        //04.封装分页数据
        return pageSetting;
    }

    /**
     * 插入新数据的方法
     * @param setting
     * @return
     */
    @Override
    public Boolean insert(Setting setting) {
        //01.获取当前操作的用户信息
        Admin admin = AdminThreadLocal.get();
        //02.保存数据
        int insert = this.settingMapper.insert(setting);
        return insert == 1;
    }

    /**
     * 根据id删除的方法
     * @param ids
     * @return
     */
    @Override
    public Boolean deleteByIds(List<Long> ids) {
        //构造根据id删除条件
        QueryWrapper<Setting> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",ids);
        //执行删除
        int i = this.settingMapper.delete(queryWrapper);
        //返回受影响值
        return i > 0;
    }

    /**
     * 根据id查询单个数据的方法
     * @param id
     * @return
     */
    @Override
    public Setting findById(Long id) {
        return this.settingMapper.selectById(id);
    }

    /**
     * 更新数据的方法
     * @param setting
     * @return
     */
    @Override
    public Boolean update(Setting setting) {
        //乐观锁
        log.info("当前数据的版本号为："+setting.getVersion());
        setting.setVersion(setting.getVersion());
        //提交并返回，根据受影响的行数，进行布尔值判断
        return this.settingMapper.updateById(setting) == 1;
    }

}
