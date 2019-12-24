package com.goshine.ptadmui.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 下拉框对象
 * @author goshine
 */
public class DropDownModel{
    private String id;
    private String text;
    private Map<String,String> item=new HashMap<String,String>();//明细Map
    
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
	public Map<String, String> getItem() {
		return item;
	}
	public void setItem(Map<String, String> item) {
		this.item = item;
	}
}
