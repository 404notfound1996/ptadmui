package com.goshine.ptadmui.sys.entity;

import com.goshine.ptadmui.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 组织机构
 * @author goshine
 */
@SuppressWarnings("serial")
@ApiModel(value="User", description="组织机构Model")
public class Organization extends BaseModel{
	@ApiModelProperty(value = "父类ID")
	private String parentId;
	@ApiModelProperty(value = "组织机构编码")
	private String orgCode;
	@ApiModelProperty(value = "组织机构名称")
	private String orgName;

	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
