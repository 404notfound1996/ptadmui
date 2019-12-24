package com.goshine.ptadmui.core.base;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 基础Model
 * @author goshine
 */
@SuppressWarnings("serial")
@ApiModel(value="BaseModel", description="基础Model")
public class BaseModel implements java.io.Serializable{
	@ApiModelProperty(value = "主键ID")
	private String id;
	@ApiModelProperty(value = "备注")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String remark;//备注
	@JsonIgnore
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private String createUser;//创建人ID
	@ApiModelProperty(value = "创建人名称")
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private String createUserName;//创建人名称
	@ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createDate;//创建时间
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modifyUser;//修改人ID
    @ApiModelProperty(value = "修改人姓名")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modifyUserName;//修改人名称
    @ApiModelProperty(value = "修改时间")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date modifyDate;//修改时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getModifyUserName() {
		return modifyUserName;
	}
	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
