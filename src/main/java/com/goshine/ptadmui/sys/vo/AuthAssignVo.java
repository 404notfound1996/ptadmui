package com.goshine.ptadmui.sys.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色成员关系授权VO
 * 用于按组织机构给角色分配成员
 * @author goshine
 */
@SuppressWarnings("serial")
@ApiModel(value="AuthAssignVo", description="角色分配用户Model")
public class AuthAssignVo extends AuthVo{
	@ApiModelProperty(value = "部门ID集合")
    private List<String> deptIds;//部门ID集合
	@ApiModelProperty(value = "角色ID集合")
    private List<String> roleIds;//角色ID集合

	public List<String> getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}
	public List<String> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
}
