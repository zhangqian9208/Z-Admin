package com.template.back.server.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.pojo.system.Setting;

import java.util.List;

/**
 * @author 张骞
 * @version
 * 设置类业务层
 */
public interface SettingService {
    /**
     * 分页查询的方法
     * @param page
     * @param pageSize
     * @return
     */
    Page findPage(Integer page, Integer pageSize, String name);

    /**
     * 插入新数据的方法
     * @param setting
     * @return
     */
    Boolean insert(Setting setting);

    /**
     * 根据id删除的方法
     * @param ids
     * @return
     */
    Boolean deleteByIds(List<Long> ids);

    /**
     * 根据id查询单个数据的方法
     * @param id
     * @return
     */
    Setting findById(Long id);

    /**
     * 更新数据的方法
     * @param setting
     * @return
     */
    Boolean update(Setting setting);

}
