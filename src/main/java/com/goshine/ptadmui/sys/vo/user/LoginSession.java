package com.goshine.ptadmui.sys.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录会话信息
 * @author litao
 * @date 2019-11-12
 */
@ApiModel(value = "loginSession",description = "用户登录会话信息")
@Data
public class LoginSession {
    @ApiModelProperty(value = "登录次数")
    private Long loginCount;
    @ApiModelProperty(value = "最后登录时间")
    private String lastLoginTime;
    @ApiModelProperty(value = "最后登录IP")
    private String lastLoginIp;
    @ApiModelProperty(value = "用户token信息")
    private String token;
    private String IP;
    private String Address;
}
