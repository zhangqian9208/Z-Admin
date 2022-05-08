package com.template.back.common.vo;

import com.template.back.common.pojo.DataTree;
import lombok.Data;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0
 * 树形控件返回封装类
 */
@Data //注解生成get/set方法
public class TreeVo implements DataTree<TreeVo> {
    //主键id
    private Long id;
    //树节点名称
    private String name;
    //父节点id
    private Long parentId;
    //子节点数据集合
    private List<TreeVo> children;
    //当前数据是否被选中
    private Boolean checked;
}
