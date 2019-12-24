package com.goshine.ptadmui.sys.vo;

import java.util.List;

/**
 * 一对多VO
 * @author goshine
 */
@SuppressWarnings("serial")
public class OneToMany implements java.io.Serializable{
    private String masterId;//机构ID
    private List<String> itemIds;//用户ID集合
    
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
}
