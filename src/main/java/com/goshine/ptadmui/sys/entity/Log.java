package com.goshine.ptadmui.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 系统日志
 * @author goshine
 */
@ApiModel(value="Log", description="日志Model")
@SuppressWarnings("serial")
public class Log implements Serializable{
	@ApiModelProperty(value = "日志ID")
    private String id;//日志ID
	@ApiModelProperty(value = "请求路径")
    private String url;//日志路径
	@ApiModelProperty(value = "日志类型")
    private String type;//日志类型
	@ApiModelProperty(value = "访问参数")
    private String params;//日志参数
	@ApiModelProperty(value = "操作人ID")
    private String createUser;//创建人
	@ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createDate;//创建时间
	@ApiModelProperty(value = "操作IP")
    private String operateIp;//操作IP
	//非持久化属性
	private String createUserName;//操作人姓名
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getOperateIp() {
		return operateIp;
	}
	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
}