package com.goshine.ptadmui.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 复选框结构模型
 * @author goshine
 */
@SuppressWarnings("serial")
@ApiModel(value="CheckBox", description="复选框结构模型")
public class CheckBox implements java.io.Serializable{
	@ApiModelProperty(value = "复选框ID")
    private String id;
	@ApiModelProperty(value = "复选框名称")
    private String text;
	@ApiModelProperty(value = "节点是否选中 true：选中，false：未选中")
    private boolean selected;
	
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
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}