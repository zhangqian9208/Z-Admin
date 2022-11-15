package com.z_admin.back.server.controller.system;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z_admin.back.common.dao.system.Admin;
import com.z_admin.back.common.dao.system.Dept;
import com.z_admin.back.common.utils.myself.LoggerUtils;
import com.z_admin.back.common.vo.R;
import com.z_admin.back.server.service.system.AdminService;
import com.z_admin.back.server.service.system.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张骞
 * @version 2.0
 * 部门管理控制层程序
 * 更新日志：前端更换为html页面，使用json格式数据进行交互，后端进行对应更改
 */
@Api(tags = "后台部门相关接口")  //knife4j注解，用于自动生成api文档
@RestController   //标记为控制层
@RequestMapping("/system/dept")   //定义请求映射路径
@Slf4j   //注解日志
public class DeptController {

    //注入部门业务层
    @Autowired
    private DeptService deptService;

    //注入管理员管理业务层
    @Autowired
    private AdminService adminService;

    /**
     * 查询部门列表
     * @param page         当前页码
     * @param pageSize     每页的数据长度
     * @return
     */
    @ApiOperation(value = "查询部门列表接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页码",required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数据长度",required = true,defaultValue = "10"),
            @ApiImplicitParam(name = "deptName",value = "部门名称",required = false)
    })  //knife4j注解，用于对接口参数进行说明
    @GetMapping("page")
    public R<Page> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                        @RequestParam(value = "deptName",required = false)String deptName) {
        try {
            //调用业务层方法
            Page all = this.deptService.findPage(page, pageSize,deptName);
            //向前端存入数据
            return R.success(all);
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-查询部门列表异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("查询数据列表！");
    }

    /**
     * 新增页面提交保存的方法
     *
     * @return
     */
    @ApiOperation(value = "新增部门接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "dept",value = "新增的部门",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PostMapping("save")
    public R<String> addSave(@RequestBody Dept dept) {
        try {
            //调用业务层的方法进行数据插入
            System.out.println(dept);
            Boolean insert = this.deptService.insert(dept);
            //返回页面，请求重定向到列表页
            return R.success("数据保存成功！");
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-新增部门数据异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据保存失败！");
    }

    /**
     * 根据id删除数据的方法
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除部门接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "ids",value = "选中的部门id集合",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PostMapping("delete")
    public R<String> delete(@RequestParam("ids") List<Long> ids) {
        try {
            //如果当前部门关联了员工，则不允许删除
            List<Admin> byDeptId = this.adminService.findByDeptId(ids);
            //如果集合不为空，则不允许删除部门
            if(CollUtil.isNotEmpty(byDeptId)){
                //写入错误数据
                return R.error("当前部门关联了用户，不允许删除！");
            }

            //调用业务层的删除方法
            Boolean delete = this.deptService.deleteByIds(ids);
            //判断业务层返回的状态
            if(!delete){
                //写入错误数据
                return R.error("数据删除失败！");
            }
            return R.success("数据删除成功！");
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-删除部门数据异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据删除失败！");
    }

    /**
     * 根据id查询单个的方法
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询单条数据接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "选中的部门id",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PostMapping("/findById")
    public R<Dept> findById(@RequestParam("id") Long id) {
        try{
            //01.数据回显
            if(ObjectUtil.isNotEmpty(id)){
                Dept dept = this.deptService.findById(id);
                if(ObjectUtil.isNotEmpty(dept)){
                    //将数据写入
                    System.out.println(dept);
                    return R.success(dept);
                }
            }
        }catch (Exception exception){
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-查询单条部门数据异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }

        //02.返回错误信息
        return R.error("未查询到要修改的数据！");
    }

    /**
     * 保存修改后数据的方法
     * @return
     */
    @ApiOperation(value = "修改部门接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "dept",value = "修改的部门",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PutMapping("edit")
    public R<String> update(@RequestBody Dept dept){
        try {
            //调用业务层的方法进行数据插入
            Boolean update = this.deptService.update(dept);
            return R.success("数据修改成功！");
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-修改部门数据异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据修改失败！");
    }


    /**
     * 查询列表的方法
     * @return
     */
    @ApiOperation(value = "查询全部部门接口")  //knife4j注解，用于对接口方法进行说明
    @GetMapping("/list")
    public R<List<Dept>> list(){
        try{
            //01.调用业务层方法
            List<Dept> depts = this.deptService.list();
            //03.返回数据
            return R.success(depts);
        }catch (Exception exception){
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-查询全部部门异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        return R.error("查询部门数据失败！");
    }
}
