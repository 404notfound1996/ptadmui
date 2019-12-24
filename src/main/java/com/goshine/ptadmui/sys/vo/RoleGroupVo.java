package com.goshine.ptadmui.sys.vo;

import java.util.List;

import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.entity.RoleGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@SuppressWarnings("serial")
@ApiModel(value="RoleGroupVo", description="角色组VO")
public class RoleGroupVo extends RoleGroup{
	 @ApiModelProperty(value = "角色集合")
     private List<Role> roleList;

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}
