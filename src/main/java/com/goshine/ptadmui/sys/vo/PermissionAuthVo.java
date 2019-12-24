package com.goshine.ptadmui.sys.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 权限授权VO
 * 用于权限分配菜单和按钮
 * @author goshine
 */
@ApiModel(value="PermissionAuthVo", description="权限授权VO")
@SuppressWarnings("serial")
public class PermissionAuthVo implements java.io.Serializable{
	@ApiModelProperty(value = "权限ID")
	private String permissionId;//主ID
	@ApiModelProperty(value = "菜单ID集合")
    private List<String> menuIds;//项集合
	@ApiModelProperty(value = "菜单操作集合")
    private List<String> operationIds;//项集合
	
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public List<String> getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(List<String> menuIds) {
		this.menuIds = menuIds;
	}
	public List<String> getOperationIds() {
		return operationIds;
	}
	public void setOperationIds(List<String> operationIds) {
		this.operationIds = operationIds;
	}
}
