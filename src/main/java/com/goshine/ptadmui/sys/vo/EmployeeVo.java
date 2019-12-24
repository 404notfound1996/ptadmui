package com.goshine.ptadmui.sys.vo;

import com.goshine.ptadmui.sys.entity.Employee;

@SuppressWarnings("serial")
public class EmployeeVo extends Employee{
	private Integer start;
	private Integer length;
	private String searchValue;
	private String orderColumn;
	private String orderDir;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOrderDir() {
		return orderDir;
	}

	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
}
