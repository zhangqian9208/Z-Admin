package com.z_admin.back.common.dao.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.z_admin.back.common.dao.DataTree;
import com.z_admin.back.common.dao.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 张骞
 * @version 2.0.0
 * @Date 2022年8月14日18:21:34
 * 功能模块数据模型
 */
@ApiModel("功能模块")  //knife4j框架注解，对类的解释说明
@Data    //注解生成get/set方法
@AllArgsConstructor   //注解生成全参构造
@NoArgsConstructor    //注解生成无参构造
public class Module extends BasePojo implements DataTree<Module> {
    //成员变量
    @ApiModelProperty("主键id")
    @TableId(value = "module_id")  //注解为主键,指定主键在数据库中的名字
    private Long id;  //模块id

    @ApiModelProperty("排序字段")
    private String sort;   //排序字段

    @ApiModelProperty("父模块id")
    private Long parentId;  //父模块id

    @ApiModelProperty("模块名称")
    private String name;  //模块名称

    @ApiModelProperty("类型0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态")
    private Long ctype;  //'0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态'

    @ApiModelProperty("状态1启用0停用")
    private Long state;  //'1启用0停用'

    @ApiModelProperty("静态页链接地址")
    private String curl;  //'模块前端静态页链接地址'

    @ApiModelProperty("页面请求地址")
    private String req;  //'模块前端静态页点击按钮或者刷新页面后的请求地址'

    @ApiModelProperty("备注")
    private String remark;  //'备注'

    @ApiModelProperty("图标")
    private String icon;  //'图标 如果是按钮，这里则是按钮的显示状态'

    //本类的自关联对象
    @ApiModelProperty("本类的自关联对象")
    @TableField(exist = false)  //该字段在数据库中不存在
    private Module module;

    //数据表中不存在，为子模块字段，在树形工具类中使用
    @ApiModelProperty("子模块")
    @TableField(exist= false)
    private List<Module> children;

    @ApiModelProperty("逻辑删除字段")
    @TableLogic
    private Integer deleted;
}
