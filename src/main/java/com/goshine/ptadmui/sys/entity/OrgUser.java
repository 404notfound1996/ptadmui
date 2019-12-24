package com.goshine.ptadmui.sys.entity;

import java.io.Serializable;
/**
 * 机构用户关系对象
 * @author goshine
 */
@SuppressWarnings("serial")
public class OrgUser implements Serializable{
	/**
	 * 用户ID
	 */
    private String userId;
    /**
     * 机构ID
     */
    private String orgId;
    /**
     * 机构名称
     * 非持久化属性
     */
    private String orgName;//机构名称
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}