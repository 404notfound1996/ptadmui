package com.goshine.ptadmui.core.model;
/**
 * 键值对对象
 * @author goshine
 */
@SuppressWarnings("serial")
public class KeyValue implements java.io.Serializable{
    private String id;//KEY
    private String text;//VALUE
     
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
}
