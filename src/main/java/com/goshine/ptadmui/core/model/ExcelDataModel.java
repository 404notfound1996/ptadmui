package com.goshine.ptadmui.core.model;

import java.io.Serializable;
import java.util.List;

/**
 * Excel数据模型
 * @author goshine
 */
@SuppressWarnings("serial")
public class ExcelDataModel implements Serializable{
    /**
     * 列头
     */
    private List<String> titles;

    /**
     * 数据
     */
    private List<List<Object>> rows;

    /**
     * SHEET页名称
     */
    private String sheetName;

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	public List<List<Object>> getRows() {
		return rows;
	}

	public void setRows(List<List<Object>> rows) {
		this.rows = rows;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}