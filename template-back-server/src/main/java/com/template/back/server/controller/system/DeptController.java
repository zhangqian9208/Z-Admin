package com.template.back.server.controller.system;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.pojo.system.Dept;
import com.template.back.common.service.LoggerService;
import com.template.back.common.vo.R;
import com.template.back.server.service.system.AdminService;
import com.template.back.server.service.system.DeptService;
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
@RestController   //标记为控制层
@RequestMapping("/system/dept")   //定义请求映射路径
@Slf4j   //注解日志
public class DeptController {
    //注入自定义日志业务层
    @Autowired
    private LoggerService loggerService;

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
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "查询部门列表");
            log.error("后台工程:controller.DeptController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("查询数据列表！");
    }

    /**
     * 新增页面提交保存的方法
     *
     * @return
     */
    @PostMapping("save")
    public R<String> addSave(@RequestBody Dept dept) {
        try {
            //调用业务层的方法进行数据插入
            System.out.println(dept);
            Boolean insert = this.deptService.insert(dept);
            //返回页面，请求重定向到列表页
            return R.success("数据保存成功！");
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "");
            log.error("后台工程:controller.DeptController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据保存失败！");
    }

    /**
     * 根据id删除数据的方法
     * @param ids
     * @return
     */
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
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "");
            log.error("后台工程:controller.DeptController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据删除失败！");
    }

    /**
     * 根据id查询单个的方法
     * @param id
     * @return
     */
    @PostMapping("/findById")
    public R<Dept> findById(@RequestParam("id") Long id) {
        //01.数据回显
        if(ObjectUtil.isNotEmpty(id)){
            Dept dept = this.deptService.findById(id);
            if(ObjectUtil.isNotEmpty(dept)){
                //将数据写入
                System.out.println(dept);
                return R.success(dept);
            }
        }
        //02.返回错误信息
        return R.error("未查询到要修改的数据！");
    }

    /**
     * 保存修改后数据的方法
     * @return
     */
    @PutMapping("edit")
    public R<String> edit(@RequestBody Dept dept){
        try {
            //调用业务层的方法进行数据插入
            Boolean update = this.deptService.update(dept);
            return R.success("数据修改成功！");
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "");
            log.error("后台工程:controller.DeptController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据修改失败！");
    }


    /**
     * 查询列表的方法
     * @return
     */
    @GetMapping("/list")
    public R<List<Dept>> list(){
        //01.调用业务层方法
        List<Dept> depts = this.deptService.list();
        //03.返回数据
        return R.success(depts);
    }
}
