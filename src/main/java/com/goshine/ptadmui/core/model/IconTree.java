package com.goshine.ptadmui.core.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 带图标的树
 * @author goshine
 */
@SuppressWarnings("serial")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="IconTree", description="带图标的基础树模型")
public class IconTree implements java.io.Serializable{
	@ApiModelProperty(value = "树ID")
    private String id;
	@ApiModelProperty(value = "树节点名称")
    private String text;
	@ApiModelProperty(value = "图标")
    private String icon;
	@ApiModelProperty(value = "是否展开")
	private Boolean expand;
	@ApiModelProperty(value = "子类显示")
	private Boolean checked;
	@ApiModelProperty(value = "父类显示")
	private Boolean selected;
	@ApiModelProperty(value = "子节点集合")
    private List<IconTree> children;

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

	public List<IconTree> getChildren() {
		return children;
	}

	public void setChildren(List<IconTree> children) {
		this.children = children;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getExpand() {
		return expand;
	}

	public void setExpand(Boolean expand) {
		this.expand = expand;
	}
}
