package com.template.back.server.service.school;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.pojo.school.Campus;
/**
 * @author 张骞
 * @version 1.0.1
 * 校区管理业务层约束接口
 */
public interface CampusService {

    /**
     * 分页查询的方法
     * @param page  当前页码，默认1
     * @param pageSize  当前页码，默认12
     * @return 返回自定义的分页VO数据
     */
    Page findPage(Integer page, Integer pageSize);

    /**
     * 新增数据的方法
     * @param campus
     * @return
     */
    Boolean insert(Campus campus);

    /**
     * 根据id删除数据的方法
     * @param id
     * @return
     */
    Boolean deleteById(String id);

    /**
     * 根据id查询单个数据的方法
     * @param id
     * @return
     */
    Campus findOne(String id);

    /**
     * 保存校区修改
     * @param campus
     * @return
     */
    Boolean update(Campus campus);

}
