package com.goshine.ptadmui.sys.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户角色组信息
 * @author litao
 * @date 2019-11-12
 */
@ApiModel(value="UserRoleGroup", description="用户角色组Model")
@Data
public class UserRoleGroup {
    @ApiModelProperty(value = "角色组ID")
    private String roleGroupId;
    @ApiModelProperty(value = "角色组名")
    public String groupName;

}