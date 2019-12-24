package com.goshine.ptadmui.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="PasswordVo", description="密码重置VO")
public class PasswordVo implements java.io.Serializable{
	@ApiModelProperty(value="旧密码")
     private String oldPassword;
	@ApiModelProperty(value = "新密码")
     private String newPassword;
	@ApiModelProperty(value = "重复新密码")
     private String repPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRepPassword() {
		return repPassword;
	}
	public void setRepPassword(String repPassword) {
		this.repPassword = repPassword;
	}
}
