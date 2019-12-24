package com.goshine.ptadmui.sys.vo;

import java.util.List;

import com.goshine.ptadmui.sys.entity.OrgUser;
import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * 用户VO
 * @author goshine
 */
@SuppressWarnings("serial")
public class UserVo extends User{
	private String sexName;//性别名称
	private String positionName;//职位名称
	private String orgNames;//部门名称，对部门用逗号分隔显示
	private String roleNames;//拥有的角色，多个角色逗号分隔
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Role> roles;//用户所拥有的权限集合
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<OrgUser> orgs;//用户所拥有的权限集合
	
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getOrgNames() {
		return orgNames==null?"":orgNames;
	}
	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getRoleNames() {
		return roleNames==null?"":roleNames.trim();
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public List<OrgUser> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<OrgUser> orgs) {
		this.orgs = orgs;
	}
}
