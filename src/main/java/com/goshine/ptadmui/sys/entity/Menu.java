package com.goshine.ptadmui.sys.entity;

import java.util.List;

import com.goshine.ptadmui.core.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="Menu", description="菜单Model")
public class Menu extends BaseModel{
    @ApiModelProperty(value = "菜单ID")
    private String name;
    @ApiModelProperty(value = "菜单父类ID")
    private String parentId;
    @ApiModelProperty(value = "菜单层级")
    private String layer;
    @ApiModelProperty(value = "菜单图标")
    private String icon;
    @ApiModelProperty(value = "菜单链接")
    private String url;
    @ApiModelProperty(value = "前端要求字段，父类显示")
    private Boolean selected;
    @ApiModelProperty(value = "前端要求字段，子类显示")
    private Boolean checked;
    @ApiModelProperty(value = "是否展开")
    private Boolean expand;
    private String type;//操作类型 add：新增

    //非持久化属性
    @ApiModelProperty(value = "子菜单集合，非持久化属性")
    private List<Menu> children;
    @ApiModelProperty(value = "权限管理中查询菜单时用于判断该菜单是否授予该权限，非持久化属性")
    private String permissionId;//权限ID，权限管理中查询菜单列表时用于判断该菜单是否已经授权
	@JsonInclude(Include.NON_EMPTY)
	private String orderNo;
	@JsonIgnore
	private String originalOrderNo;
	@JsonIgnore
	private List<String> ids;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getLayer() {
        return layer;
    }
    public void setLayer(String layer) {
        this.layer = layer == null ? null : layer.trim();
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOriginalOrderNo() {
		return originalOrderNo;
	}
	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }
}
