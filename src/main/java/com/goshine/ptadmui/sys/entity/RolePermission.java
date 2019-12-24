package com.goshine.ptadmui.sys.entity;

import java.io.Serializable;
/**
 * 角色权限关系对象
 * @author goshine
 */
@SuppressWarnings("serial")
public class RolePermission implements Serializable{
	/**
	 *关系主键ID
	 */
    private String id;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 权限ID
     */
    private String permissionId;
    
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getRoleId(){
		return roleId;
	}
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	public String getPermissionId(){
		return permissionId;
	}
	public void setPermissionId(String permissionId){
		this.permissionId = permissionId;
	}
}