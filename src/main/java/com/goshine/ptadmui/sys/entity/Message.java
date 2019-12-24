package com.goshine.ptadmui.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 系统消息类
 * @author goshine
 */
@SuppressWarnings("serial")
@ApiModel(value="Message", description="系统消息Model")
public class Message implements Serializable{
	@ApiModelProperty(value = "主键ID")
    private String id;
	@ApiModelProperty(value = "接收人ID")
    private String userId;
	@ApiModelProperty(value = "标题")
    private String title;
	@ApiModelProperty(value = "内容")
    private String content;
	@ApiModelProperty(value = "消息类型")
    private Integer type;
	@ApiModelProperty(value = "发送时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date sendTime;
	@ApiModelProperty(value = "是否已阅")
    private Integer readFlag;
	@ApiModelProperty(value = "阅读时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date readTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
}