package com.z_admin.back.server.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张骞
 * @version 1.0.0
 * MP配置类
 */
@EnableTransactionManagement   //开启事务
@MapperScan("com.z_admin.back.common.mapper")   //设置mapper接口的扫描包
@Configuration  //注解为配置类
public class MybatisPlusConfig {
    @Bean //配置分页插件
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    //oracle数据库配置
    @Bean
    public OracleKeyGenerator oracleKeyGenerator(){
        return new OracleKeyGenerator();
    }

    /**
     * 自定义拦截器

    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }
     */

    //SQL分析插件
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor(){
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        //设置
        List<ISqlParser> list = new ArrayList<>();
        list.add(new BlockAttackSqlParser());//全表更新或者删除阻断器
        sqlExplainInterceptor.setSqlParserList(list);
        return sqlExplainInterceptor;
    }

    /**
     * 性能分析器，更新到3.4.2版本后依赖不可用
     * @return

    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(300);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }*/

    /**
     * 乐观锁插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    /**
     * 自定义sql注入器
     * @return

    @Bean
    public MySqlInjector mySqlInjector(){
        return new MySqlInjector();
    }
     */
}
