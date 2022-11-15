package com.z_admin.back.common.config;

import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ${张骞}
 * @version 1.0.1
 * @Description 阿里云OSS配置模板类，如果使用OSS上传数据，需要保留
 */
@Configuration
@PropertySource("classpath:aliyun.properties")   //这里指向配置文件地址
@ConfigurationProperties(prefix = "aliyun.oss")  //定义配置文件前缀
@Data
public class AliyunOSSConfig {
    //配置项目
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String urlPrefix;

    //初始化OSS客户端
    @Bean
    public OSSClient oSSClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}