package com.template.back.server.service.school.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.template.back.common.mapper.school.CampusMapper;
import com.template.back.common.pojo.school.Campus;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.utils.AdminThreadLocal;
import com.template.back.server.service.school.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author 张骞
 * @version 1.0.1
 * 校区管理业务层
 */
@Service  //注解为spring管理的业务层
public class CampusServiceImpl extends ServiceImpl<CampusMapper,Campus> implements CampusService {
    //注入mapper接口
    @Autowired
    private CampusMapper campusMapper;

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
    @Override
    public Page findPage(Integer page, Integer pageSize,String campusName,String address, String startData, String endData) {
        //01.配置MP的查询条件，根据时间排序
        LambdaQueryWrapper<Campus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Campus::getCreated);

        //1.1根据校区名称查询
        if(StrUtil.isNotEmpty(campusName)){
            queryWrapper.like(Campus::getName,campusName);
        }

        //2.4构造根据起止日期开始查询的条件，日期需要转换格式
        if (StrUtil.isAllNotEmpty(startData, endData)) {
            LocalDate startDate = LocalDate.parse(startData, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate = LocalDate.parse(endData, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            queryWrapper.ge(Campus::getCoopTime,startDate);
            queryWrapper.le(Campus::getCoopTime,endDate);
        }

        //02.配置分页数据
        Page<Campus> pageCampus = new Page<>(page,pageSize);

        //03.查询数据
        IPage<Campus> campusIPage = this.campusMapper.selectPage(pageCampus, queryWrapper);

        //04.获取数据
        List<Campus> campusList = campusIPage.getRecords();


        //06.封装分页数据返回
        pageCampus.setRecords(campusList);
        return pageCampus;
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
