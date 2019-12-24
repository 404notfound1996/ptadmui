package com.goshine.ptadmui.core.model;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 分页对象
 * @author goshine
 * @param <T>
 */
@ApiModel(value="Page", description="分页对象")
public class Page<T>{
	@ApiModelProperty(value = "当前页码")
    private int pageIndex=1; 
	@ApiModelProperty(value = "每页展示条数")
    private int pageSize=15;
	@ApiModelProperty(value = "总页数")
    private int totalPage=-1;
	@ApiModelProperty(value = "排序字段")
    private String sortField;
	@ApiModelProperty(value = "排序类型 DESC:倒序,ASC:正序")
    private String sortType="DESC";
	@ApiModelProperty(value = "总条数")
    private long total=-1;
	@ApiModelProperty(value = "数据集合")
    private List<T> pageList;
	@ApiModelProperty(value = "查询参数集合，用于存放查询的参数信息")
    private Map<String,Object> data;
    
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	public List<T> getPageList() {
		return pageList;
	}
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	/**
	 * 生成返回Page
	 * @return
	 */
	public Page<T> generatePage(Page<T> page,PageInfo<T> pageInfo){
		page.setTotal(pageInfo.getTotal());
		page.setTotalPage(pageInfo.getPages());
		page.setPageList(pageInfo.getList());
		return page;
	}
}
