package com.goshine.ptadmui.sys.entity;

import java.util.ArrayList;
import java.util.List;

import com.goshine.ptadmui.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="Permission", description="权限Model")
public class Permission extends BaseModel{
    @ApiModelProperty(value = "权限名称")
    private String name;
    @ApiModelProperty(value = "权限备注")
    private String remark;
    //非持久化属性
    @ApiModelProperty(value = "角色ID，非持久化属性，授权时用于判断是否拥有该权限")
    private String roleId;//角色ID
    @ApiModelProperty(value = "菜单ID集合，非持久化属性，授权时用于返回该权限拥有的所有菜单权限ID")
    private List<String> menuIds=new ArrayList<String>();//角色ID
    @ApiModelProperty(value = "操作ID集合，非持久化属性，授权时用于返回该权限拥有的所有操作权限ID")
    private List<String> operIds=new ArrayList<String>();//角色ID

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
    
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public List<String> getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(List<String> menuIds) {
		this.menuIds = menuIds;
	}
	public List<String> getOperIds() {
		return operIds;
	}
	public void setOperIds(List<String> operIds) {
		this.operIds = operIds;
	}
}