package com.template.back.server.controller.school;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.pojo.school.Campus;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.service.LoggerService;
import com.template.back.common.utils.AdminThreadLocal;
import com.template.back.server.service.school.CampusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author 张骞
 * @version 1.0.0
 * 加盟校控制层代码，本框架单表增删改查标准代码
 */
@Controller  //标记为控制层
@RequestMapping("/school/campus")  //注解请求映射路径
@Slf4j  //注解日志
public class CampusController {
    //注入自定义日志业务层
    @Autowired
    private LoggerService loggerService;

    //注入业务层接口
    @Autowired
    private CampusService campusService;

    /**
     * 查询列表
     *
     * @param page         当前页码
     * @param pageSize     每页的数据长度
     * @param modelAndView 视图对象
     * @return
     */
    @RequestMapping("list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                             @RequestParam(value = "size", defaultValue = "12", required = false) Integer pageSize,
                             ModelAndView modelAndView) {
        try {
            //调用业务层方法
            Page all = this.campusService.findPage(page, pageSize);
            //向前端存入数据
            modelAndView.addObject("page", all);
            //请求转发
            modelAndView.setViewName("WEB-INF/pages/school/campus/list");
            return modelAndView;
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLogAndUser(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    this.getAdmin().getName(),  //获取当前登录的用户名
                    exception, "查询校区列表");  //功能中文描述
            log.error("后台工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }

    /**
     * 跳转到新增页面的方法
     *
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(ModelAndView modelAndView) {
        //转发页面,如果自关联、一对多、多对多，可以模仿系统管理部分的方法
        modelAndView.setViewName("WEB-INF/pages/school/campus/add");
        return modelAndView;
    }

    /**
     * 新增保存数据的方法
     * @param modelAndView  视图对象
     * @param param 前端FROM表单提交数据
     * @return 返回视图对象
     */
    @RequestMapping("save")
    public ModelAndView insert(ModelAndView modelAndView, @RequestParam Map<String, String> param){
        try {
            //使用HuTool下的插件，进行map属性注入
            Campus campus = BeanUtil.fillBeanWithMap(param, new Campus(), false);
            //调用业务层的方法进行数据插入
            Boolean insert = this.campusService.insert(campus);
            if(!insert){
                //写入错误信息
                modelAndView.addObject("error","业务层返回数据保存失败！");
                //转发页面
                modelAndView.setViewName("WEB-INF/pages/school/campus/add");
            }else {
                //返回页面，请求重定向到列表页
                modelAndView.setViewName("redirect:/school/campus/list");
            }
            return modelAndView;
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLogAndUser(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    this.getAdmin().getName(),  //获取当前登录的用户名
                    exception, "新增校区数据");  //功能中文描述
            log.error("后台工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        //写入错误信息
        modelAndView.addObject("error","控制层返回数据保存失败！");
        //转发页面继续回到新增页
        modelAndView.setViewName("WEB-INF/pages/school/campus/add");
        return modelAndView;
    }

    /**
     * 根据id删除数据的方法
     *
     * @param modelAndView
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public ModelAndView delete(ModelAndView modelAndView, @RequestParam("id") String id) {
        try {
            //调用业务层的删除方法
            Boolean delete = this.campusService.deleteById(id);
            //判断业务层返回的状态
            if(!delete){
                //写入错误数据
                modelAndView.addObject("error","数据删除失败！");
            }
            //返回页面，请求重定向到列表页
            modelAndView.setViewName("redirect:/school/campus/list");
            return modelAndView;
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLogAndUser(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    this.getAdmin().getName(),  //获取当前登录的用户名
                    exception, "删除校区数据");  //功能中文描述
            log.error("后台工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }

    /**
     * 跳转到修改数据页面的方法
     *
     * @param modelAndView
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(ModelAndView modelAndView, @RequestParam("id") String id) {
        //01.数据回显
        if (ObjectUtil.isNotEmpty(id)) {
            Campus campus = this.campusService.findOne(id);
            if (ObjectUtil.isNotEmpty(campus)) {
                //将数据写入
                modelAndView.addObject("campus", campus);
            }
        }
        //02.请求重定向到修改页面
        modelAndView.setViewName("WEB-INF/pages/school/campus/update");
        return modelAndView;
    }

    /**
     * 保存修改后数据的方法
     *
     * @return
     */
    @RequestMapping("edit")
    public ModelAndView edit(ModelAndView modelAndView, @RequestParam Map<String, Object> param) {
        try {
            //使用hutool下的插件，进行map属性注入
            Campus campus = BeanUtil.fillBeanWithMap(param, new Campus(), false);
            //调用业务层的方法进行数据插入
            Boolean update = this.campusService.update(campus);
            //返回页面，请求重定向到列表页
            modelAndView.setViewName("redirect:/school/campus/list");
            return modelAndView;
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLogAndUser(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    this.getAdmin().getName(),  //获取当前登录的用户名
                    exception, "保存校区修改");  //功能中文描述
            log.error("后台工程出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }


    /**
     * 获取当前登录用户信息的方法
     * @return 返回当前登录的用户对象
     */
    public Admin getAdmin(){
        return AdminThreadLocal.get();
    }
}
