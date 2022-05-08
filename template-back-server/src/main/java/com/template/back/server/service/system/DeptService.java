package com.template.back.server.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.pojo.system.Dept;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0
 * 部门管理业务层接口
 */
public interface DeptService {

    /**
     * 分页查询的方法
     * @param page  页码
     * @param pageSize  每页的数据条数
     * @return  返回分页数据
     */
    Page findPage(Integer page, Integer pageSize,String deptName);

    /**
     * 插入单条数据的方法
     * @param dept
     * @return
     */
    Boolean insert(Dept dept);

    /**
     * 根据id删除数据的方法
     * @param ids  前端请求传入的id集合
     */
    Boolean deleteByIds(List<Long> ids);


    /**
     * 根据Id查询单条数据
     * @param id  前端请求传入的id
     * @return
     */
    Dept findById(Long id);

    /**
     * 更新数据的方法
     * @param dept  前端请求传入的封装类
     * @return  返回布尔值
     */
    Boolean update(Dept dept);


    /**
     * 查询所有数据的方法
     * @return
     */
    List<Dept> findAll();

    /**
     * 查询部门列表的方法
     * @return
     */
    List<Dept> list();
}
