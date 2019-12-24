package com.goshine.ptadmui.sys.entity;

import com.goshine.ptadmui.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="Operation", description="操作Model")
public class Operation extends BaseModel{
    @ApiModelProperty(value = "操作名称")
    private String name;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "操作类型")
    private String opertype;
    @ApiModelProperty(value = "接口地址")
    private String url;
    @ApiModelProperty(value = "菜单ID")
    private String menuId;
    //非持久化属性
    @ApiModelProperty(value = "权限ID，授权时用于判断是否属于谋权限")
    private String permissionId;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOpertype() {
        return opertype;
    }
    public void setOpertype(String opertype){
        this.opertype = opertype == null ? null : opertype.trim();
    }
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url==null?null:url.trim();
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}