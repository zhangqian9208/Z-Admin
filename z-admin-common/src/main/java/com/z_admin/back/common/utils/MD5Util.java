package com.z_admin.back.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * 密码加密工具类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class MD5Util {

    /**
     * 密码加密
     * @param password
     * @return
     * @throws Exception
     */
    public static String  md5(String password){
        try {
            //1.创建加密对象
            MessageDigest md5 = MessageDigest.getInstance("md5");
            //2.加密密码
            byte[] by = md5.digest(password.getBytes());
            //3.创建编码对象
            //BASE64Encoder encoder = new BASE64Encoder();
            Base64 encoder = new Base64();
            //4.对结果编码
            return encoder.encode(by).toString();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
