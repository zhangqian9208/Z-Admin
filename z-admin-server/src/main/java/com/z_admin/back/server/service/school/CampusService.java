package com.z_admin.back.server.service.school;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.z_admin.back.common.dao.school.Campus;

/**
 * @author 张骞
 * @version 1.0.1
 * 校区管理业务层约束接口
 */
public interface CampusService extends IService<Campus> {

    /**
     * 分页查询的方法
     * @param page  当前页码，默认1
     * @param pageSize  当前页码，默认12
     * @param campusName 根据校区名称查询条件
     * @param address  根据校区地址查询条件
     * @param startData  根据开始日期查询条件
     * @param endData  根据结束日期查询条件
     * @return  返回自定义的分页VO数据
     */
    Page findPage(Integer page, Integer pageSize,String campusName,String address, String startData, String endData);

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
