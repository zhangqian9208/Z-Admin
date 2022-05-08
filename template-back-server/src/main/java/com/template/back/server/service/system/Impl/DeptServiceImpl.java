package com.template.back.server.service.system.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.mapper.system.DeptMapper;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.pojo.system.Dept;
import com.template.back.common.utils.AdminThreadLocal;
import com.template.back.server.service.system.DeptService;
import com.template.back.server.service.system.LoggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0
 * 部门管理业务层实现类
 */
@Service   //注解标注为Spring的业务层
@Slf4j     //日志注解
public class DeptServiceImpl implements DeptService {
    //注入自定义日志业务层
    @Autowired
    private LoggerService loggerService;

    @Autowired
    private DeptMapper deptMapper;



    /**
     * 分页查询的方法
     * @param page  当前页码
     * @param pageSize   每页展示的条数
     * @return  返回分页数据
     */
    @Override
    public Page findPage(Integer page, Integer pageSize,String deptName) {
        //01.配置MP的查询条件，根据时间排序
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created");
        if(StrUtil.isNotEmpty(deptName)){
            queryWrapper.like("dept_name",deptName);
        }
        //02.配置分页数据
        Page<Dept> pageDept = new Page<>(page,pageSize);
        //03.查询数据
        this.deptMapper.selectPage(pageDept, queryWrapper);
        //04.封装分页数据
        return pageDept;
    }

    /**
     * 插入数据的方法
     * @param dept
     * @return
     */
    @Override
    public Boolean insert(Dept dept){
        //01.获取当前操作的用户信息
        Admin admin = AdminThreadLocal.get();
        //02.更改当前数据的修改人信息
        dept.setOperator(admin.getName());
        //03.保存数据
        int insert = this.deptMapper.insert(dept);
        return insert == 1;
    }

    /**
     * 根据id删除数据的方法
     * @param ids
     */
    @Override
    public Boolean deleteByIds(List<Long> ids) {


        //构造根据id删除条件
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dept::getDeptId,ids);
        int delete = this.deptMapper.delete(queryWrapper);
        return delete>0;
    }

    /**
     * 根据id查询单条数据
     * @param id
     * @return
     */
    @Override
    public Dept findById(Long id) {
        return this.deptMapper.selectById(id);
    }

    /**
     * 更新数据的方法
     * @param dept
     * @return
     */
    @Override
    public Boolean update(Dept dept) {
        //乐观锁
        log.info("当前数据的版本号为："+dept.getVersion());
        dept.setVersion(dept.getVersion());
        //提交并返回，根据受影响的行数，进行布尔值判断
        return this.deptMapper.updateById(dept) == 1;
    }


    /**
     * 查询所有数据的方法
     * @return
     */
    @Override
    public List<Dept> findAll() {
        //构造查询条件
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created");
        //查询数据并返回
        return this.deptMapper.selectList(queryWrapper);
    }

    /**
     * 查询部门列表的方法
     * @return
     */
    @Override
    public List<Dept> list() {
        //构造查询条件
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        //设置排序条件
        queryWrapper.orderByDesc(Dept::getUpdated);
        //查询数据
        final List<Dept> depts = this.deptMapper.selectList(queryWrapper);
        return depts;
    }
}
