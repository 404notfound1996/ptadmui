package com.goshine.ptadmui.sys.entity;

import java.util.List;
import java.util.Map;

import com.goshine.ptadmui.core.base.BaseModel;
import com.goshine.ptadmui.sys.vo.UserVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="Role", description="角色Model")
public class Role extends BaseModel{
	@ApiModelProperty(value = "角色组ID")
	private String roleGroupId;
	@ApiModelProperty(value = "角色ID")
	private String roleName;
	@ApiModelProperty(value = "角色类型")
	private int roleType;
	//非持久化属性
	@ApiModelProperty(value = "权限ID集合，用于角色权限授权时存放权限ID集合")
	private List<String> permissionIds;
	@ApiModelProperty(value = "角色下的用户列表，用于角色授权时返回角色下的用户信息,授权保存用户时调用assignuser接口")
	private List<UserVo> users;//角色下的用户列表
	@ApiModelProperty(value = "角色拥有的权限信息，用于授权时的参数返回")
	private List<Permission> permissions;//角色的权限集合
	//数据角色相关参数
	@ApiModelProperty(value = "数据ID集合，用于角色数据授权时存放数据ID集合")
	private List<String> dataIds;
	@ApiModelProperty(value = "查询角色树")
	private Map<String,Object> roleTrees;

	public Map<String, Object> getRoleTrees() {
		return roleTrees;
	}

	public void setRoleTrees(Map<String, Object> roleTrees) {
		this.roleTrees = roleTrees;
	}

	public String getRoleGroupId() {
		return roleGroupId;
	}
	public void setRoleGroupId(String roleGroupId) {
		this.roleGroupId = roleGroupId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleType(){
		return roleType;
	}
	public void setRoleType(int roleType){
		this.roleType = roleType;
	}
	public List<String> getPermissionIds() {
		return permissionIds;
	}
	public void setPermissionIds(List<String> permissionIds) {
		this.permissionIds = permissionIds;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public List<UserVo> getUsers() {
		return users;
	}
	public void setUsers(List<UserVo> users) {
		this.users = users;
	}
	public List<String> getDataIds() {
		return dataIds;
	}
	public void setDataIds(List<String> dataIds) {
		this.dataIds = dataIds;
	}
}