package com.goshine.ptadmui.sys.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 条目Model
 * @author goshine
 */
@SuppressWarnings("serial")
public class Entry implements Serializable{
    private Long id;//ID
    @JsonProperty("name")
    private String entryName;//条目名称
    @JsonProperty("price")
    private String entryPrice;//条目价格
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEntryName() {
		return entryName;
	}
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	public String getEntryPrice() {
		return entryPrice;
	}
	public void setEntryPrice(String entryPrice) {
		this.entryPrice = entryPrice;
	}
}