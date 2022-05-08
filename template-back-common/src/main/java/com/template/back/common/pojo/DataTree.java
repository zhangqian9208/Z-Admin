package com.template.back.common.pojo;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0
 * @param <T>
 *     element ui 树形控件使用
 */
public interface DataTree<T> {
    public Object getId();
    public Object getParentId();
    public void setChildren(List<T> children);
    public List<T> getChildren();
}
