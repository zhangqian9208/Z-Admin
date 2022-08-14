package com.template.back.server.controller.system;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.pojo.system.Module;
import com.template.back.common.pojo.system.Role;
import com.template.back.common.service.LoggerService;
import com.template.back.common.utils.AdminThreadLocal;
import com.template.back.common.vo.R;
import com.template.back.server.service.system.ModuleService;
import com.template.back.server.service.system.RoleService;
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
 * 模块管理控制层程序
 * 更新日志：前端更换为html页面，使用json格式数据进行交互，后端进行对应更改
 */
@Api(tags = "功能模块相关接口")  //knife4j注解，用于自动生成api文档
@RestController   //标记为控制层
@RequestMapping("/system/module")   //定义请求映射路径
@Slf4j   //注解日志
public class ModuleController {
    //注入自定义日志业务层
    @Autowired
    private LoggerService loggerService;

    //注入模块业务层
    @Autowired
    private ModuleService moduleService;

    //注入角色业务层
    @Autowired
    private RoleService roleService;

    /**
     * 查看模块列表的方法
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @ApiOperation(value = "查询模块列表接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页码",required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数据长度",required = true,defaultValue = "10"),
            @ApiImplicitParam(name = "name",value = "模块名称",required = false)
    })  //knife4j注解，用于对接口参数进行说明
    @GetMapping("page")
    public R<Page> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                        @RequestParam(value = "name",required = false)String name){
        try{
            //调用业务层的方法
            Page all = this.moduleService.queryPage(page,pageSize,name);
            //向前端存入数据
            return R.success(all);
        }catch (Exception exception){
            //出现异常，记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "查看模块列表");
            log.error("后台工程:controller.ModuleController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("查询数据列表！");
    }

    /**
     * 新增保存数据的方法
     * @param module
     * @return
     */
    @ApiOperation(value = "新增模块接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "module",value = "新增的模块",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PostMapping("save")
    public R<String> addSave(@RequestBody Module module){
        try {
            //调用业务层的方法进行数据插入
            Boolean insert = this.moduleService.insert(module);
            if(!insert){
                //出现异常，返回错误信息
                return R.error("数据保存失败！");
            }else {
                //返回页面，请求重定向到列表页
                return R.success("数据保存成功！");
            }
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLogAndUser(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    this.getAdmin().getName(),    //获取当前登录的用户信息
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
    @ApiOperation(value = "删除模块接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "ids",value = "选中的模块id集合",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PostMapping("delete")
    public R<String> delete(@RequestParam("ids") List<Long> ids) {
        try {
            //当前模块下有子模块，不允许删除
            for (Long id : ids) {
                List<Module> moduleList = this.moduleService.findChildrenById(id);
                if(CollUtil.isNotEmpty(moduleList)){
                    return R.error("当前模块含有子模块，不允许删除！");
                }
            }

            //当前模块被角色关联，不允许删除
            for (Long id : ids) {
                List<Role> roleList = this.roleService.findRoleByModuleId(id);
                if(CollUtil.isNotEmpty(roleList)){
                    return R.error("当前模块关联了角色，不允许删除！");
                }
            }

            //调用业务层的删除方法
            Boolean delete = this.moduleService.deleteByIds(ids);
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
            log.error("后台工程:controller.ModuleController出现异常！异常信息为：" + exception);
        }
        //写入错误数据
        return R.error("数据删除失败！");
    }

    /**
     * 根据id查询单个的方法
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询单条数据接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "选中的模块id",required = true)})  //knife4j注解，用于对接口参数进行说明
    @GetMapping("/findById")
    public R<Module> findById(@RequestParam("id") Long id) {
        //01.数据回显
        if(ObjectUtil.isNotEmpty(id)){
            Module module = this.moduleService.queryById(id);
            if(ObjectUtil.isNotEmpty(module)){
                //将数据写入
                return R.success(module);
            }
        }
        //02.返回错误信息
        return R.error("未查询到要修改的数据！");
    }

    /**
     * 保存修改后数据的方法
     * @return
     */
    @ApiOperation(value = "修改后台用户接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "module",value = "修改的模块",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PutMapping("edit")
    public R<String> update(@RequestBody Module module){
        try {
            //调用业务层的方法进行数据插入
            Boolean update = this.moduleService.update(module);
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
     * 查询列表的方法
     * @return
     */
    @ApiOperation(value = "查询全部模块接口")  //knife4j注解，用于对接口方法进行说明
    @GetMapping("/list")
    public R<List<Module>> list(){
        //01.调用业务层方法
        List<Module> moduleList = this.moduleService.findAll();
        //03.返回数据
        return R.success(moduleList);
    }

    /**
     * 获取当前登录用户信息的方法
     * @return 返回当前登录的用户对象
     */
    public Admin getAdmin(){
        return AdminThreadLocal.get();
    }

}
