package com.template.back.server.controller.school;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.pojo.school.Campus;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.service.LoggerService;
import com.template.back.common.utils.AdminThreadLocal;
import com.template.back.common.vo.R;
import com.template.back.server.service.school.CampusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author 张骞
 * @version 1.0.0
 * 加盟校控制层代码，本框架单表增删改查标准代码
 */
@Api(tags = "校区管理相关接口")
@RestController  //标记为restful风格的控制层
@RequestMapping("/school/campus/")  //注解请求映射路径
@Slf4j  //注解日志
public class CampusController {
    //注入自定义日志业务层
    @Autowired
    private LoggerService loggerService;

    //注入业务层接口
    @Autowired
    private CampusService campusService;


    /**
     * 查询部门列表
     *
     * @param page     当前页码
     * @param pageSize 每页的数据长度
     * @return
     */
    @ApiOperation(value = "查看加盟校列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页码",required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数据长度",required = true,defaultValue = "10"),
            @ApiImplicitParam(name = "campusName",value = "根据校区名称查询",required = false),
            @ApiImplicitParam(name = "address",value = "根据校区地址查询",required = false),
            @ApiImplicitParam(name = "startData",value = "根据开始时间查询，和结束时间共用",required = false),
            @ApiImplicitParam(name = "endData",value = "根据结束时间查询，和开始时间共用",required = false),
    })  //knife4j注解，用于对接口参数进行说明
    @Cacheable(value = "campusCache",key = "#page + '_' + #pageSize+ '_' +#campusName + '_' + #address+ '_' + #startData + '_' + #endData")  //spring cache写入缓存
    @GetMapping("page")
    public R<Page> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "campusName", required = false) String campusName,
                        @RequestParam(value = "address", required = false) String address,
                        @RequestParam(value = "startData", required = false)String startData,
                        @RequestParam(value = "endData", required = false)String endData) {
        try {
            //调用业务层方法
            Page all = this.campusService.findPage(page, pageSize, campusName, address,startData,endData);
            //向前端存入数据
            return R.success(all);
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "查询加盟校列表");
            log.error("后台工程:controller.CampusController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("查询数据列表异常！");
    }

    /**
     * 新增数据的方法
     * @param campus
     * @return
     */
    @ApiOperation(value = "新增加盟校数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "campus",value = "新增的加盟校数据",required = true)})  //knife4j注解，用于对接口参数进行说明
    @CacheEvict(value = "campusCache",allEntries = true)  //新增数据后，删除对应的缓存数据
    @PostMapping("save")
    public R<String> addSave(@RequestBody Campus campus){
        //调用业务层的方法进行保存
        Boolean insert = this.campusService.save(campus);
        if(insert){
            return R.success("数据保存成功！");
        }else {
            return R.error("数据保存失败！");
        }

    }

    /**
     * 根据id查询单条数据，用于数据回显
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询单条数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "选中的校区id",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PostMapping("findById")
    public R<Campus> findById(@RequestParam(value = "id") String id) {
        //如果传入的数据不为空，则开始查询
        if (ObjectUtil.isNotEmpty(id)) {
            Campus campus = this.campusService.findOne(id);
            if (ObjectUtil.isNotEmpty(campus)) {
                return R.success(campus);
            }
        }
        return R.error("未查询到要修改的数据！");
    }

    /**
     * 修数据的方法
     * @return
     */
    @ApiOperation(value = "修改加盟校数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "campus",value = "修改的加盟校数据",required = true)})  //knife4j注解，用于对接口参数进行说明
    @CacheEvict(value = "campusCache",allEntries = true)
    @PutMapping("edit")
    public R<String> edit(@RequestBody Campus campus){
        try {
            //调用业务层的方法进行数据插入
            Boolean update = this.campusService.update(campus);
            return R.success("数据修改成功！");
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "");
            log.error("后台工程:controller.CampusController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据修改失败！");
    }

    /**
     * 根据id删除数据的方法
     * @param ids
     * @return
     */
    @ApiOperation(value = "根据id删除数据的方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "ids",value = "选中的加盟校id集合",required = true)})  //knife4j注解，用于对接口参数进行说明
    @CacheEvict(value = "campusCache",allEntries = true)
    @PostMapping("delete")
    public R<String> delete(@RequestParam("ids") List<String> ids) {
        try {

            //调用业务层的删除方法
            Boolean delete = this.campusService.removeByIds(ids);

            //判断业务层返回的状态
            if(!delete){
                //写入错误数据
                return R.error("数据删除失败！");
            }
            return R.success("数据删除成功！");
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "");
            log.error("后台工程:controller.CampusController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据删除失败！");
    }

    /**
     * 获取当前登录用户信息的方法
     *
     * @return 返回当前登录的用户对象
     */
    public Admin getAdmin() {
        return AdminThreadLocal.get();
    }
}

