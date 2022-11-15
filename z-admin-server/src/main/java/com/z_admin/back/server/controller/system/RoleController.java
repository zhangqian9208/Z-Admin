package com.z_admin.back.server.controller.system;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.z_admin.back.common.dao.system.Admin;
import com.z_admin.back.common.dao.system.Role;
import com.z_admin.back.common.utils.myself.LoggerUtils;
import com.z_admin.back.common.vo.R;
import com.z_admin.back.common.vo.TreeVo;
import com.z_admin.back.server.service.system.AdminService;
import com.z_admin.back.server.service.system.ModuleService;
import com.z_admin.back.server.service.system.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "角色相关接口")  //knife4j注解，用于自动生成api文档
@RestController   //标记为控制层
@RequestMapping("/system/role")   //定义请求映射路径
@Slf4j   //注解日志
public class RoleController {

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
    @ApiOperation(value = "查询角色列表接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页码",required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数据长度",required = true,defaultValue = "10"),
            @ApiImplicitParam(name = "name",value = "角色名称",required = false)
    })  //knife4j注解，用于对接口参数进行说明
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
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-查看角色列表异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("查询列表信息出现错误！");
    }


    /**
     * 新增页面提交保存的方法
     * @param role
     * @return
     */
    @ApiOperation(value = "新增角色接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "role",value = "新增的角色",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PostMapping("save")
    public R<String> addSave(@RequestBody Role role) {
        try {
            //调用业务层的方法进行数据插入
            Boolean insert = this.roleService.insert(role);
            //返回页面
            return R.success("数据保存成功！");
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-新增角色数据异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据保存失败！");
    }

    /**
     * 根据id集合删除数据的方法
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除角色接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "ids",value = "选中的角色id集合",required = true)})  //knife4j注解，用于对接口参数进行说明
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
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-删除角色数据异常");
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
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "选中的角色id",required = true)})  //knife4j注解，用于对接口参数进行说明
    @GetMapping("/findById")
    public R<Role> findById(@RequestParam("id") Long id) {
        try {
            //01.数据回显
            if(ObjectUtil.isNotEmpty(id)){
                Role role = this.roleService.findOne(id);
                if(ObjectUtil.isNotEmpty(role)){
                    //将数据写入
                    return R.success(role);
                }
            }
        }catch (Exception exception){
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-查询单条角色数据异常");
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
    @ApiOperation(value = "修改角色接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "role",value = "修改的角色",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PutMapping("edit")
    public R<String> edit(@RequestBody Role role) {
        try {
            //调用业务层的方法进行数据插入
            Boolean update = this.roleService.update(role);
            //返回页面，请求重定向到列表页
            return R.success("数据修改成功！");
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-修改角色数据异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据修改失败！");
    }


    /**
     * 跳转授权页面的方法
     *
     * @return
     */
    @ApiOperation(value = "查询角色授权接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "选中的角色id",required = true)})  //knife4j注解，用于对接口参数进行说明
    @GetMapping("author")
    public R<Map> author(@RequestParam("id") Long roleId) {
        try {
            //01.查询后台数据，将数据返回为集合，子模块与父模块不进行区分
            List<TreeVo> treeVos = moduleService.findByRole(roleId);

            //02.调用业务层数据进行封装
            Map<String,Object> map = roleService.convertTree(treeVos);

            return R.success(map);
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-查询角色授权异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("查询模块数据失败！");
    }

    /**
     * 保存授权数据
     * @return
     */
    @ApiOperation(value = "保存角色授权接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "map",value = "st-选中的角色id checkedArray-选中的模块集合",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PutMapping("updateRoleModule")
    public R<String> updateRoleModule(@RequestBody Map map) {
        try{
            //解析前台传入的数据
            Long roleId = Convert.toLong(map.get("st"));
            String moduleIds = String.valueOf(map.get("checkedArray"));
            //调用业务层方法
            roleService.updateRoleModule(roleId, moduleIds);
            //返回修改成功数据
            return R.success("角色授权成功！");
        }catch (Exception exception){
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-查询角色授权异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        return R.success("角色授权失败！");
    }

}
