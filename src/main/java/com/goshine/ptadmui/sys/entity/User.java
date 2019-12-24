package com.goshine.ptadmui.sys.entity;

import java.util.Date;
import java.util.List;

import com.goshine.ptadmui.core.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 用户信息
 * @author goshine
 */
@SuppressWarnings("serial")
@ApiModel(value="User", description="用户对象")
public class User extends BaseModel{
	@ApiModelProperty(value = "工号")
	private String jobNum;//工号
	@ApiModelProperty(value = "用户名")
	private String userName;//用户名
	@ApiModelProperty(value = "密码")
	private String password;//密码
	@ApiModelProperty(value = "姓名")
	private String name;//姓名
	@ApiModelProperty(value = "职位")
	private String position;//职位
	@ApiModelProperty(value = "邮箱地址")
	private String email;//邮箱地址
	@ApiModelProperty(value = "手机号")
	private String mobile;//手机号
	@ApiModelProperty(value = "性别")
	private Integer sex;//性别
	@ApiModelProperty(value = "座机号")
	private String telephone;//座机号
	@ApiModelProperty(value = "分机号")
	private String phoneExt;//分机号
	@ApiModelProperty(value = "登录次数")
	private Long loginCount;//登录次数
	@ApiModelProperty(value = "用户状态")
	private Integer status;//用户状态，0:正常，1：停用
	@ApiModelProperty(value = "最后登录时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date lastLoginTime;//最后登录时间
	@ApiModelProperty(value = "最后登录IP")
	private String lastLoginIp;//最后登录IP
	@ApiModelProperty(value = "高管模式 true：是，false：否")
	private Boolean executiveMode;
	@ApiModelProperty(value = "身份类型 1：部门主管，2：普通成员")
	private Integer userType;
	//非持久化属性
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> roleIds;//用户的角色ID集合
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> orgIds;//用户所属机构ID集合

	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPhoneExt() {
		return phoneExt;
	}
	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}
	public Long getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getLastLoginTime(){
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
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
	public List<String> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
	public List<String> getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(List<String> orgIds) {
		this.orgIds = orgIds;
	}
}