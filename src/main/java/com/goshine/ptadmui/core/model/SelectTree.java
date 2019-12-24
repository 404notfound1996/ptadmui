package com.goshine.ptadmui.core.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 树结构模型
 * @author goshine
 */
@SuppressWarnings("serial")
@ApiModel(value="SelectTree", description="选择树模型")
public class SelectTree implements java.io.Serializable{
	@ApiModelProperty(value = "树ID")
    private String id;
	@ApiModelProperty(value = "树节点名称")
    private String text;
	@ApiModelProperty(value = "节点类型 1：菜单，2：按钮")
    private int type;
	@ApiModelProperty(value = "节点是否选中 true：选中，false：未选中")
    private boolean checked;
	@ApiModelProperty(value = "子节点集合")
    private List<SelectTree> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<SelectTree> getChildren() {
		return children;
	}
	public void setChildren(List<SelectTree> children) {
		this.children = children;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}