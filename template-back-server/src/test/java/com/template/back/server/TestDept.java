package com.template.back.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.template.back.common.mapper.system.AdminMapper;
import com.template.back.common.mapper.system.DeptMapper;
import com.template.back.common.pojo.system.Admin;
import com.template.back.common.pojo.system.Dept;
import com.template.back.common.service.LoggerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDept {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private LoggerService loggerService;

    @Test
    public void testSaveDept(){
        Dept dept = new Dept();
        //dept.setDeptId(10L);
        dept.setDeptName("测试部门");
        dept.setRemark("测试数据");
        this.deptMapper.insert(dept);
    }

    @Test
    public void testSaveAdmin(){
        Admin admin = new Admin();
        admin.setName("吴会茹");
        admin.setUserName("chenwei");
        admin.setPassword("123456");
        admin.setVersion(1);
        this.adminMapper.insert(admin);
    }

    @Test
    public void testUpdateAdmin(){
        Admin admin = new Admin();
        admin.setAdminId(2L);
        admin.setName("付彦君");
        admin.setUserName("makelove");
        admin.setPassword("123456");
        admin.setVersion(2);

        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_id",admin.getAdminId());

        this.adminMapper.update(admin,queryWrapper);
    }

    @Test
    public void testERROR(){
        //:service.Impl.AdminServiceImpl
        //this.loggerService.saveLogAndUser("后台工程:service.Impl.AdminServiceImpl", "1123", new Exception("测试") , "此处保存的用户名为用户提交数据");
    }
}
