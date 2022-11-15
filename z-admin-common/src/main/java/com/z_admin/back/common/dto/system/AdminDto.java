package com.z_admin.back.common.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 张骞
 * @version 1.0.0
 * 管理员用户（后台用户）登录接收的数据
 */
@ApiModel("后台用户DTO")  //knife4j框架注解，对类的解释说明
@Data
public class AdminDto  implements Serializable {
    @ApiModelProperty("用户登录名")
    private String userName;  //用户登录名

    @ApiModelProperty("用户密码")
    private String password;  //用户密码

    @ApiModelProperty("用户验证码二次验签")
    private String captchaVerification;  //用户密码
}
