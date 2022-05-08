package com.template.back.server.controller.system;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.pojo.system.Module;
import com.template.back.common.pojo.system.Role;
import com.template.back.common.service.LoggerService;
import com.template.back.common.utils.AdminThreadLocal;
import com.template.back.common.utils.NoAuthorization;
import com.template.back.common.utils.TreeUtils;
import com.template.back.common.vo.R;
import com.template.back.server.service.system.AdminService;
import com.template.back.server.service.system.DeptService;
import com.template.back.server.service.system.ModuleService;
import com.template.back.server.service.system.RoleService;
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
@RestController  //注解为restful风格controller
@RequestMapping("system/user")
@Slf4j
public class AdminController {
    //注入自定义日志业务层
    @Autowired
    private LoggerService loggerService;

    //注入管理员功能对应的Service对象
    @Autowired
    private AdminService adminService;

    //注入部门管理的业务层
    @Autowired
    private DeptService deptService;

    //注入角色业务层
    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;

    /**
     * 后台用户登录的方法
     *
     * @return 返回页面重定向地址
     */
    @NoAuthorization  //不需要验证登录
    @PostMapping("/login")
    public R<Admin> login(@RequestBody Map<String, String> map, HttpServletRequest req) {
        String userName = map.get("userName");
        String password = map.get("password");
        try {
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
            //记录日志
            this.loggerService.saveLogAndUser(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    userName, exception, "此处保存的用户名为用户提交数据");
            log.error("后台工程:controller.AdminController出现异常！异常信息为：" + exception);
        }
        //登录失败，返回到登录页，给出错误提示
        return R.error("登录失败，服务器错误");
    }

    /**
     * 加载用户权限菜单的方法
     *
     * @return
     */
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
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "查询部门列表");
            log.error("后台工程:controller.AdminController出现异常！异常信息为：" + exception);
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
    @PostMapping("save")
    public R<String> insert(@RequestBody Admin admin) {
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
            //记录日志
            this.loggerService.saveLogAndUser(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    this.getAdmin().getName(),    //获取当前登录的用户信息
                    exception, "");
            log.error("后台工程:controller.DeptController出现异常！异常信息为：" + exception);
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
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "");
            log.error("后台工程:controller.AdminController出现异常！异常信息为：" + exception);
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

    /**
     * 根据id查询单个的方法
     *
     * @param id
     * @return
     */
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
    @PutMapping("edit")
    public R<String> edit(@RequestBody Admin admin) {
        try {
            //调用业务层的方法进行数据插入
            Boolean update = this.adminService.update(admin);
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
     * 查询所有角色的方法
     *
     * @return
     */
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
    @PostMapping("/updateRole")
    public R<String> updateRole(@RequestBody Map map) {
        //调取业务层的方法
        roleService.updateRole(map);
        //跳转页面
        return R.success("用户授权成功！");
    }

}
