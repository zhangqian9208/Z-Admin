package com.template.back.server.controller.system;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.pojo.system.Setting;
import com.template.back.common.service.LoggerService;
import com.template.back.common.vo.R;
import com.template.back.server.service.system.SettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0
 * 自定义设置管理控制层程序，设置内容以键值对的形式存在
 */
@RestController   //标记为控制层
@RequestMapping("/system/setting")   //定义请求映射路径
@Slf4j   //注解日志
public class SettingController {
    //注入自定义日志业务层
    @Autowired
    private LoggerService loggerService;

    //注入业务层对象
    @Autowired
    private SettingService settingService;

    /**
     * 查询设置列表的方法
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("page")
    public R<Page> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                        @RequestParam(value = "name",required = false)String name){
        try {
            //调用业务层方法
            Page all = this.settingService.findPage(page, pageSize,name);
            //向前端存入数据
            return R.success(all);
        }catch (Exception exception){
            //记录异常日志
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "查询设置列表");
            log.error("后台工程:controller.SettingController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("查询数据列表！");
    }


    /**
     * 新增页面提交保存的方法
     * @param setting
     * @return
     */
    @PostMapping("save")
    public R<String> addSave(@RequestBody Setting setting) {
        try {
            //调用业务层的方法进行数据插入
            Boolean insert = this.settingService.insert(setting);
            //返回页面，请求重定向到列表页
            return R.success("数据保存成功！");
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "");
            log.error("后台工程:controller.SettingController出现异常！异常信息为：" + exception);
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
            //调用业务层的删除方法
            Boolean delete = this.settingService.deleteByIds(ids);
            //判断业务层返回的状态
            if(!delete){
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
            log.error("后台工程:controller.SettingController出现异常！异常信息为：" + exception);
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
    public R<Setting> findById(@RequestParam("id") Long id) {
        //01.数据回显
        if(ObjectUtil.isNotEmpty(id)){
            Setting setting = this.settingService.findById(id);
            if(ObjectUtil.isNotEmpty(setting)){
                return R.success(setting);
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
    public R<String> edit(@RequestBody Setting setting){
        try {
            //调用业务层的方法进行数据插入
            Boolean update = this.settingService.update(setting);
            //返回页面，请求重定向到列表页
            return R.success("数据修改成功！");
        } catch (Exception exception) {
            //记录日志
            this.loggerService.saveLog(this.getClass().getName(),  //获取当前类名及类数据
                    Thread.currentThread().getStackTrace()[1].getMethodName(),  //获取当前方法名
                    exception, "");
            log.error("后台工程:controller.SettingController出现异常！异常信息为：" + exception);
        }
        //出现异常，返回错误信息
        return R.error("数据修改失败！");
    }

}
