package com.goshine.ptadmui.sys.vo;

import java.util.List;

/**
 * 多对多VO
 * @author goshine
 */
@SuppressWarnings("serial")
public class ManyToMany implements java.io.Serializable{
    private List<String> masterIds;//主项ID集合
    private List<String> itemIds;//从项ID集合
    
	public List<String> getMasterIds(){
		return masterIds;
	}
	public void setMasterIds(List<String> masterIds) {
		this.masterIds = masterIds;
	}
	public List<String> getItemIds() {
		return itemIds;
	}
	public void setItemIds(List<String> itemIds) {
		this.itemIds = itemIds;
	}
}
