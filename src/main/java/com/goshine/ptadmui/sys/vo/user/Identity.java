package com.goshine.ptadmui.sys.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户身份信息
 * @author litao
 * @date 2019-11-11
 */
@ApiModel(value="Identity",description = "用户身份信息")
@Data
public class Identity {
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "高管模式 true：是，false：否")
    private Boolean executiveMode;
    @ApiModelProperty(value = "身份类型 1：部门主管，2：普通成员")
    private Integer userType;
    @ApiModelProperty(value = "用户头像信息")
    private String avatar;
}
