package com.z_admin.back.common.vo;

import lombok.Data;

/**
 * 图片上传的返回对象
 */
@Data
public class PicUploadResult {
    //文件唯一标识
    private String uid;
    //文件名
    private String name;
    //返回的状态 uploading  done  error  removed
    private String status;
    //服务端的响应内容 如：'{"status": "success"}'
    private String response;
}
