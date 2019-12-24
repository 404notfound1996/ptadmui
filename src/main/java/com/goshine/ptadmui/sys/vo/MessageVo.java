package com.goshine.ptadmui.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 系统消息VO
 * @author goshine
 */
@ApiModel(value="MessageVo", description="系统消息VO")
public class MessageVo{
	@ApiModelProperty(value = "总条数")
	 private long totalCount;
	@ApiModelProperty(value = "已阅数量")
	 private long readCount;
	@ApiModelProperty(value = "未阅数量")
	 private long noReadCount;
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getReadCount() {
		return readCount;
	}
	public void setReadCount(long readCount) {
		this.readCount = readCount;
	}
	public long getNoReadCount() {
		return noReadCount;
	}
	public void setNoReadCount(long noReadCount) {
		this.noReadCount = noReadCount;
	} 
}
