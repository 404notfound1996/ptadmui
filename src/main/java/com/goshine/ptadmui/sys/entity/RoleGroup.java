package com.goshine.ptadmui.sys.entity;

import com.goshine.ptadmui.core.base.BaseModel;
/**
 * 角色组对象
 * @author goshine
 */
@SuppressWarnings("serial")
public class RoleGroup extends BaseModel{
    /**
     * 角色组名称
     */
    private String groupName;

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}