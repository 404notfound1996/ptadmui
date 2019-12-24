package com.goshine.ptadmui.sys.entity;

import java.io.Serializable;
/**
 * 权限菜单关系对象
 * @author goshine
 */
@SuppressWarnings("serial")
public class PermissionMenu implements Serializable{
	/**
	 *权限ID
	 */
    private String permissionId;
    /**
     * 菜单IDI
     */
    private String menuId;
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}