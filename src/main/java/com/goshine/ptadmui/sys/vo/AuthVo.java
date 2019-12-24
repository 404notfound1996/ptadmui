package com.goshine.ptadmui.sys.vo;

import java.util.List;

/**
 * 授权VO
 * @author goshine
 */
@SuppressWarnings("serial")
public class AuthVo implements java.io.Serializable{
    private String masterId;//主ID
    private List<String> itemIds;//项集合
    private String itemType;//项类型，暂时用于角色数据授权时，区分数据类型
    
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public List<String> getItemIds() {
		return itemIds;
	}
	public void setItemIds(List<String> itemIds) {
		this.itemIds = itemIds;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}
