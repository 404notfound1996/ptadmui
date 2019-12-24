package com.goshine.ptadmui.core.model.vo;

import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.sys.entity.Role;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * 上下文Vo
 * @author goshine
 */
public class ContextVo extends Context {
	@ApiModelProperty(value = "登录次数")
	private Long loginCount;
	@ApiModelProperty(value = "最后登录时间")
	private String lastLoginTime;
	@ApiModelProperty(value = "最后登录IP")
	private String lastLoginIp;
	@ApiModelProperty(value = "用户菜单集合")
	private Map<String,String> menuMap;
	@ApiModelProperty(value = "角色集合")
	private  List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Map<String,String> getMenuMap() {
		return menuMap;
	}
	public void setMenuMap(Map<String,String> menuMap) {
		this.menuMap = menuMap;
	}
}
