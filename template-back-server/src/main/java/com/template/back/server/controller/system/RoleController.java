package com.template.back.server.controller.system;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.pojo.system.Role;
import com.template.back.common.service.LoggerService;
import com.template.back.common.vo.R;
import com.template.back.common.vo.TreeVo;
import com.template.back.server.service.system.AdminService;
import com.template.back.server.service.system.ModuleService;
import com.template.back.server.service.system.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 张骞
 * @version 2.0
 * 角色管理控制层程序
 * 更新日志：前端更换为html页面，使用json格式数据进行交互，后端进行对应更改
 */
@RestController   //标记为控制层
@RequestMapping("/system/role")   //定义请求映射路径
@Slf4j   //注解日志
public class RoleController {
    //注入自定义日志业务层
    @Autowired
    private LoggerService loggerService;

    //注入管理员功能对应的Service对象
    @Autowired
    private RoleService roleService;

    //注入模块功能对应的Service对象
    @Autowired
    private ModuleService moduleService;

    //注入管理员管理业务层
    @Autowired
    private AdminService adminService;

    /**
     * 查询管理员用户列表
     * @param page  页码数
     * @param pageSize  每页的条数
     * @param name  //前端传入的查询条件
     * @return
     */
    @GetMapping("page")
    public R<Page> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                        @RequestParam(value = "name",required = false)String name) {
        try {
            //调用业务层方法
            Page all = this.roleService.findPage(page, pageSize,name);
            //向前端存入数据
            return R.success(all);
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "查询部门列表");
            log.error("后台工程:controller.RoleController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("查询列表信息出现错误！");
    }


    /**
     * 新增页面提交保存的方法
     * @param role
     * @return
     */
    @PostMapping("save")
    public R<String> addSave(@RequestBody Role role) {
        try {
            //调用业务层的方法进行数据插入
            Boolean insert = this.roleService.insert(role);
            //返回页面
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
     * 根据id集合删除数据的方法
     * @param ids
     * @return
     */
    @PostMapping("delete")
    public R<String> delete(@RequestParam("ids") List<Long> ids) {
        try {
            //如果当前角色关联了用户，则不允许删除
            for (Long roleId : ids) {
                List<Admin> adminList = this.adminService.findAdminByRoleId(roleId);
                if(CollUtil.isNotEmpty(adminList)){
                    //写入错误数据
                    return R.error("当前角色关联了用户，不允许删除！");
                }
            }

            //如果当前角色没有关联用户，则调用业务层的删除方法
            Boolean delete = this.roleService.deleteByIds(ids);
            //判断业务层返回的状态
            if (!delete) {
                //写入错误数据
                return R.error("数据删除失败！");
            }
            //返回页面，请求重定向到列表页
            return R.success("数据删除成功！");
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "");
            log.error("后台工程:controller.RoleController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据删除失败！");
    }

    /**
     * 根据id查询单个的方法
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public R<Role> findById(@RequestParam("id") Long id) {
        //01.数据回显
        if(ObjectUtil.isNotEmpty(id)){
            Role role = this.roleService.findOne(id);
            if(ObjectUtil.isNotEmpty(role)){
                //将数据写入
                return R.success(role);
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
    public R<String> edit(@RequestBody Role role) {
        try {
            //调用业务层的方法进行数据插入
            Boolean update = this.roleService.update(role);
            //返回页面，请求重定向到列表页
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
     * 跳转授权页面的方法
     *
     * @return
     */
    @GetMapping("author")
    public R<Map> author(@RequestParam("id") Long roleId) {
        try {
            //01.查询后台数据，将数据返回为集合，子模块与父模块不进行区分
            List<TreeVo> treeVos = moduleService.findByRole(roleId);

            //02.调用业务层数据进行封装
            Map<String,Object> map = roleService.convertTree(treeVos);

            return R.success(map);
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "");
            log.error("后台工程:controller.RoleController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("查询模块数据失败！");
    }

    /**
     * 保存授权数据
     * @return
     */
    @PutMapping("updateRoleModule")
    public R<String> updateRoleModule(@RequestBody Map map) {
        //解析前台传入的数据
        Long roleId = Convert.toLong(map.get("st"));
        String moduleIds = String.valueOf(map.get("checkedArray"));
        //调用业务层方法
        roleService.updateRoleModule(roleId, moduleIds);
        //返回修改成功数据
        return R.success("角色授权成功！");
    }

}
