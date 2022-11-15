package com.z_admin.back.common.service;

import com.aliyun.oss.OSSClient;
import com.z_admin.back.common.config.AliyunOSSConfig;
import com.z_admin.back.common.vo.PicUploadResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

/**
 * 阿里云图片上传的业务层代码
 */
@Service //注解为业务层
@Slf4j
public class PicUploadService {
    //定义允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp",".jpg",".jpeg",".gif",".png"};

    @Autowired   //注入OSS上传的客户端对象
    private OSSClient ossClient;
    @Autowired//注入阿里云配置类
    private AliyunOSSConfig aliyunOSSConfig;

    /**
     * 文件上传的方法
     * @param multipartFile  参数
     * @return  返回自定义的图片上传封装类
     */
    public PicUploadResult upload(MultipartFile multipartFile){
        //创建返回类对象
        PicUploadResult picUploadResult = new PicUploadResult();
        //校验图片格式，默认状态为false,格式吻合则改为true
        boolean isLegal = false;
        //遍历前面自定义的支持格式类型
        for(String type : IMAGE_TYPE){
            //判断上传的文件后缀名是否在支持的格式中
            if(StringUtils.endsWithIgnoreCase(multipartFile.getOriginalFilename(),type)){
                //如果格式吻合，则修改状态为true
                isLegal = true;
                break; //找到吻合的格式，或者遍历结束，退出循环
            }
        }
        //判断当前isLegal的值,如果为当前值为false，则返回错误
        if(!isLegal){
            picUploadResult.setStatus("error");
            return picUploadResult;
        }
        //如果当前isLegal的值为true,则执行保存文件的操作
        //首先获取文件的新路径
        String fileName = multipartFile.getOriginalFilename();  //获取上传的文件名
        String filePath = this.getFilePath(fileName);
        try{
            //使用阿里云OSS上传数据
            // 目录结构：images/2018/12/29/xxxx.jpg
            ossClient.putObject(aliyunOSSConfig.getBucketName(),filePath,
                    new ByteArrayInputStream(multipartFile.getBytes()));
        }catch (Exception e){
            //出现错误，打印日志，返回数据
            log.error("阿里云OSS文件上传失败："+e);
            picUploadResult.setStatus("error");
            return picUploadResult;
        }
        //如果文件上传成功，则执行到此处
        picUploadResult.setStatus("done");
        picUploadResult.setName(this.aliyunOSSConfig.getUrlPrefix() + filePath);
        picUploadResult.setUid(String.valueOf(System.currentTimeMillis()));

        return picUploadResult;
    }

    /**
     * 生成文件存储路径的方法
     * @param sourceFileName  上传的源文件名称
     * @return  返回的文件路径名称
     */
    public String getFilePath(String sourceFileName){
        //获取当前的时间
        DateTime dateTime = new DateTime();
        //返回文件路径
        return "images/" + dateTime.toString("yyyy")
                + "/" + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/" + System.currentTimeMillis() +
                RandomUtils.nextInt(100, 9999) + "." +
                StringUtils.substringAfterLast(sourceFileName, ".");
    }

}
