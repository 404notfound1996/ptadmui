package com.goshine.ptadmui.sys.vo.user;

import com.goshine.ptadmui.sys.entity.Menu;
import com.goshine.ptadmui.sys.entity.Operation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 用户角色信息
 * @author litao
 * @date 2019-11-12
 */
@ApiModel(value="UserRole", description="用户角色Model")
@Data
public class UserRole{
    @ApiModelProperty(value = "角色ID")
    private String roleId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色类型")
    private int roleType;
    @ApiModelProperty(value = "角色组信息")
    private UserRoleGroup userRoleGroup;
}