package com.goshine.ptadmui.sys.entity;

import java.io.Serializable;
/**
 * 用户角色关系对象
 * @author goshine
 */
@SuppressWarnings("serial")
public class UserRole implements Serializable{
	/**
	 * 用户ID
	 */
    private String userId;
    /**
     * 角色ID
     */
    private String roleId;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}