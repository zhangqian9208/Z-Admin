package com.z_admin.back.server.controller.system;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z_admin.back.common.dao.system.Admin;
import com.z_admin.back.common.dao.system.Module;
import com.z_admin.back.common.dao.system.Role;
import com.z_admin.back.common.dto.system.AdminDto;
import com.z_admin.back.common.utils.myself.AdminThreadLocal;
import com.z_admin.back.common.utils.annotation.NoAuthorization;
import com.z_admin.back.common.utils.myself.LoggerUtils;
import com.z_admin.back.common.utils.myself.TreeUtils;
import com.z_admin.back.common.vo.R;
import com.z_admin.back.server.service.system.AdminService;
import com.z_admin.back.server.service.system.ModuleService;
import com.z_admin.back.server.service.system.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 张骞
 * @version 1.0.0
 * 管理员控制层代码
 */
@Api(tags = "后台用户相关接口")  //knife4j注解，用于自动生成api文档
@RestController  //注解为restful风格controller
@RequestMapping("system/user")
@Slf4j
public class AdminController {

    //注入管理员功能对应的Service对象
    @Autowired
    private AdminService adminService;

    //注入角色业务层
    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;

    //导入验证码校验的业务层
    @Autowired
    private CaptchaService captchaService;

    /**
     * 后台用户登录的方法
     *
     * @return 返回页面重定向地址
     */
    @ApiOperation(value = "后台用户登录接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminDto",value = "用户信息DTO",required = true)
    })  //knife4j注解，用于对接口参数进行说明
    @NoAuthorization  //不需要验证登录
    @PostMapping("/login")
    public R<Admin> login(@RequestBody AdminDto adminDto, HttpServletRequest req) {
        String userName = adminDto.getUserName();
        String password = adminDto.getPassword();
        String captchaVerification = adminDto.getCaptchaVerification();
        try {
            //对图形验证码进行二次验签
            CaptchaVO captchaVO = new CaptchaVO();
            captchaVO.setCaptchaVerification(captchaVerification);
            ResponseModel response = captchaService.verification(captchaVO);

            //打印验证码情况
            log.info("----------------------------------------------------");
            log.info("接收到验证码信息："+captchaVerification + ",后台处理结果："+response.getRepCode());
            log.info("----------------------------------------------------");

            if(!response.isSuccess()){
                String code = response.getRepCode();
                if(StrUtil.equals(code,"9999")){
                    return R.error("验证码服务器内部异常！");
                }else if(StrUtil.equals(code,"0011")){
                    return R.error("验证码参数不能为空！");
                }else if(StrUtil.equals(code,"6110")){
                    return R.error("验证码已失效，请重新获取");
                }else if(StrUtil.equals(code,"6111")){
                    return R.error("验证码失败，不允许登录！");
                }else if(StrUtil.equals(code,"6112")){
                    return R.error("获取验证码失败,请联系管理员");
                }
            }

            //调用业务层的登录方法
            Admin admin = this.adminService.login(userName);
            //01.校验用户名，如果根据用户名查询的数据为空，则用户名不存在
            if (ObjectUtil.isEmpty(admin)) {
                return R.error("输入的用户名不存在！");
            }
            //02.校验用户状态，如果被禁用将无法登录
            if (admin.getState() != 1) {
                return R.error("当前用户被禁用！");
            }
            //02.校验密码，如果密码不对，则返回提示
            if (!StrUtil.equals(password, admin.getPassword())) {
                return R.error("输入的密码错误！");
            }
            //03.如果业务层返回成功
            if (ObjectUtil.isNotEmpty(admin)) {
                //向session添加数据
                req.getSession().setAttribute("admin", admin);

                //登录成功，返回数据
                return R.success(admin);
            }
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-后台用户登录异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //登录失败，返回到登录页，给出错误提示
        return R.error("登录失败，服务器错误");
    }

    /**
     * 加载用户权限菜单的方法
     *
     * @return
     */
    @ApiOperation(value = "加载用户权限菜单接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({
    })  //knife4j注解，用于对接口参数进行说明
    @PostMapping("author")
    public R<List<Module>> queryAuthor(HttpServletRequest request) {
        //01.获取当前登录的用户信息
        Admin admin = AdminThreadLocal.get();

        //02.进行鉴权操作
        //2.1加载该用户对应的角色下的模块信息集合
        List<Module> modules = this.moduleService.findModuleByUserId(admin.getAdminId());
        //2.2讲模块集合按照父子关系重新整理（使用了Tree工具类）
        List<Module> treeList = TreeUtils.getTreeList("0", modules);

        //03.在登录时，将查询到的用户module数据当做字符串传递给Session，给权限过滤器使用
        StringBuffer author = new StringBuffer();
        //3.1将数据放入SringBuffer中，组合成字符串
        for (Module module : modules) {
            //重新定义字段，放入请求地址
            author.append(module.getReq());
            author.append(",");
        }
        //3.2将数据放入请求域，供权限拦截器使用
        request.getSession().setAttribute("authorStr", author.toString());

        //04.返回树形权限集合数据
        return R.success(treeList);
    }

    /**
     * 用户退出的方法
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "后台用户退出接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({
    })  //knife4j注解，用于对接口参数进行说明
    @PostMapping("logout")
    public R<String> logout(HttpServletRequest req) {
        //删除请求域的数据
        req.getSession().removeAttribute("admin");
        //返回数据
        log.info("退出的方法被请求！");
        return R.success("用户退出成功！");
    }

    /**
     * 查询管理员用户列表
     *
     * @param page     当前页码
     * @param pageSize 每页的数据
     * @param name     查询的条件
     * @return
     */
    @ApiOperation(value = "查询后台用户列表接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页码",required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数据长度",required = true,defaultValue = "10"),
            @ApiImplicitParam(name = "name",value = "根据用户姓名查询",required = false)
    })  //knife4j注解，用于对接口参数进行说明
    @GetMapping("page")
    public R<Page> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "name", required = false) String name) {
        try {
            //调用业务层方法
            Page pageInfo = this.adminService.findPage(page, pageSize, name);
            //向前端存入数据
            return R.success(pageInfo);
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-查询后台用户列表异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("查询列表信息失败！");
    }


    /**
     * 新增保存数据的方法
     *
     * @param admin
     * @return
     */
    @ApiOperation(value = "新增后台用户接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "admin",value = "新增的后台用户",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PostMapping("save")
    public R<String> addSave(@RequestBody Admin admin) {
        try {
            //1.这里需要验证用户名唯一,根据用户名查询数据
            Admin login = this.adminService.login(admin.getUserName());
            // 1.1如果可以查询到数据，则用户名重复
            if (ObjectUtil.isNotEmpty(login)) {
                return R.error("当前用户名重复！");
            }
            //2.调用业务层的方法进行数据插入
            Boolean insert = this.adminService.insert(admin);
            // 2.1判断业务层返回的情况
            if (!insert) {
                return R.error("数据保存失败！");
            } else {
                //返回页面，请求重定向到列表页
                return R.success("数据保存成功！");
            }
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-新增后台用户异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        //写入错误信息
        return R.error("数据保存失败！");
    }

    /**
     * 根据id删除数据的方法
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除后台用户接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "ids",value = "选中的课程id集合",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PostMapping("delete")
    public R<String> delete(@RequestParam("ids") List<Long> ids) {
        try {
            //调用业务层的删除方法
            Boolean delete = this.adminService.deleteByIds(ids);
            //判断业务层返回的状态
            if (!delete) {
                //写入错误数据
                return R.error("数据删除失败！");
            }
            return R.success("数据删除成功！");
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-删除后台用户异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据删除失败！");
    }



    /**
     * 根据id查询单个的方法
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询单条数据接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "选中的用户id",required = true)})  //knife4j注解，用于对接口参数进行说明
    @GetMapping("/findById")
    public R<Admin> findById(@RequestParam("id") Long id) {
        //01.数据回显
        if (ObjectUtil.isNotEmpty(id)) {
            Admin admin = this.adminService.findOne(id);
            if (ObjectUtil.isNotEmpty(admin)) {
                //将数据写入
                return R.success(admin);
            }
        }
        //02.返回错误信息
        return R.error("未查询到要修改的数据！");
    }

    /**
     * 保存修改后数据的方法
     *
     * @return
     */
    @ApiOperation(value = "修改后台用户接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "admin",value = "修改的后台用户",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PutMapping("edit")
    public R<String> update(@RequestBody Admin admin) {
        try {
            //调用业务层的方法进行数据插入
            Boolean update = this.adminService.update(admin);
            return R.success("数据修改成功！");
        } catch (Exception exception) {
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-修改后台用户异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据修改失败！");
    }

    /**
     * 查询所有角色的方法
     *
     * @return
     */
    @ApiOperation(value = "根据用户查询角色接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "后台用户id",required = true)})  //knife4j注解，用于对接口参数进行说明
    @GetMapping("userRoleList")
    public R<List<Role>> userRoleList(@RequestParam("id") Long id) {
        //数据回显
        //2.当前用户已经关联的角色回显
        //2.1根据当前的id查询关联的角色数据
        List<Role> all = roleService.findRoleByUserId(id);

        //3.转发页面
        return R.success(all);
    }

    /**
     * 保存角色授权的方法
     *
     * @return
     */
    @ApiOperation(value = "根据用户查询角色接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({@ApiImplicitParam(name = "map",value = "adminId-后台用户id roles-选中的角色集合",required = true)})  //knife4j注解，用于对接口参数进行说明
    @PostMapping("/updateRole")
    public R<String> updateRole(@RequestBody Map map) {
        //调取业务层的方法
        roleService.updateRole(map);
        //跳转页面
        return R.success("用户授权成功！");
    }


}
