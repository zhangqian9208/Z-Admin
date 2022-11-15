package com.z_admin.back.common.utils.myself;

import com.z_admin.back.common.dao.system.Admin;

/**
 * @author 张骞
 * @version 1.0.0
 * @Description 存放当前登录用户信息的线程数据，在其他功能处验证用户登录使用
 *              前提条件：需要在登录代码中将用户数据写入
*/
public class AdminThreadLocal {
    //创建ThreadLocal
    private static final ThreadLocal<Admin> LOCAL = new ThreadLocal<>();
    //构造方法私有化
    private AdminThreadLocal(){

    }

    /**
     * 将对象放入ThreadLocal中
     *
     * @param admin  传入admin对象
    */
    public static void set(Admin admin){
        LOCAL.set(admin);
    }

    /**
     * 返回当前线程中的Admin对象
     *
     * @return
    */
        public static Admin get(){
        return LOCAL.get();
    }

    /**
     * 删除当前线程中的Admin对象
    */
    public static void remove(){
        LOCAL.remove();
    }

}
