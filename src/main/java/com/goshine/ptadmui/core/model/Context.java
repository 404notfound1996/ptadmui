package com.goshine.ptadmui.core.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 上下文信息
 * @author goshine
 */
@ApiModel(value="Context", description="上下文对象")
public class Context{
	@ApiModelProperty(value = "用户ID")
    private String userId;
	@ApiModelProperty(value = "用户名")
    private String userName;
	@ApiModelProperty(value = "姓名")
    private String name;
	@ApiModelProperty(value = "高管模式 true：是，false：否")
	private Boolean executiveMode;
	@ApiModelProperty(value = "身份类型 1：部门主管，2：普通成员")
	private Integer userType;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getExecutiveMode() {
		return executiveMode;
	}
	public void setExecutiveMode(Boolean executiveMode) {
		this.executiveMode = executiveMode;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public  Date getCurrentDateTime(){
        return new Date();
	}
}
