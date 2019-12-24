package com.goshine.ptadmui.sys.entity;

import java.io.Serializable;
/**
 * 角色数据关系对象
 * @author goshine
 */
@SuppressWarnings("serial")
public class RoleData implements Serializable{
	/**
	 *关系主键ID
	 */
    private String id;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 数据ID
     */
    private String dataId;
    /**
     * 数据类型
     * ORG:机构
     */
    private String dataType;
    
	public String getId() {
		return id;
	}
	public String getRoleId() {
		return roleId;
	}
	public String getDataId() {
		return dataId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}