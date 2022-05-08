package com.template.back.server.service.school.Impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.mapper.school.CampusMapper;
import com.template.back.common.pojo.school.Campus;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.utils.AdminThreadLocal;
import com.template.back.server.service.school.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张骞
 * @version 1.0.1
 * 校区管理业务层
 */
@Service  //注解为spring管理的业务层
public class CampusServiceImpl implements CampusService {
    //注入mapper接口
    @Autowired
    private CampusMapper campusMapper;

    /**
     * 分页查询的方法
     * @param page  当前页码，默认1
     * @param pageSize  当前页码，默认12
     * @return 返回自定义的分页VO数据
     */
    @Override
    public Page findPage(Integer page, Integer pageSize) {
        //01.配置MP的查询条件，根据时间排序
        QueryWrapper<Campus> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created");
        //02.配置分页数据
        Page<Campus> campusPage = new Page<>(page, pageSize);
        //03.查询数据
        IPage<Campus> campusIPage = this.campusMapper.selectPage(campusPage, queryWrapper);
        //04.封装分页数据并返回
        return campusPage;
    }

    /**
     * 新增数据的方法
     * @param campus
     * @return
     */
    @Override
    public Boolean insert(Campus campus) {
        //对数据进行非空验证的方法
        Boolean aBoolean = this.dataValidation(campus);
        if(aBoolean){
            //01.生成UUID(注解自动生成)
            //campus.setCampusId(UUID.randomUUID().toString());
            //02.获取当前操作的用户信息
            Admin admin = AdminThreadLocal.get();
            //03.更改当前数据的修改人信息
            campus.setOperator(admin.getName());
            //04.保存数据
            int insert = this.campusMapper.insert(campus);
            return insert == 1;
        }
        return false;
    }

    /**
     * 根据id删除数据的方法
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(String id) {
        //构造根据id删除条件
        QueryWrapper<Campus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("campus_id",id);
        //执行删除
        int i = this.campusMapper.delete(queryWrapper);
        //返回受影响值
        return i == 1;
    }

    /**
     * 根据id查询单个数据的方法
     * @param id
     * @return
     */
    @Override
    public Campus findOne(String id) {
        return this.campusMapper.selectById(id);
    }

    /**
     * 保存校区修改
     * @param campus
     * @return
     */
    @Override
    public Boolean update(Campus campus) {
        //对数据进行非空验证的方法
        Boolean aBoolean = this.dataValidation(campus);
        if(aBoolean) {
            //乐观锁
            campus.setVersion(campus.getVersion());
            //提交并返回，根据受影响的行数，进行布尔值判断
            return this.campusMapper.updateById(campus) == 1;
        }
        return false;
    }

    /**
     * 对关键字段进行数据验证的方法
     * @param campus
     * @return
     */
    private Boolean dataValidation(Campus campus){
        if(ObjectUtil.isAllNotEmpty(campus.getName(),campus.getCoopTime(),campus.getSale())){
            return true;
        }
        return false;
    }
}
