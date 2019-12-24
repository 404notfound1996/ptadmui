package com.goshine.ptadmui.sys.entity;

import com.goshine.ptadmui.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="LogConfig", description="日志配置Model")
@SuppressWarnings("serial")
public class LogConfig extends BaseModel{
	@ApiModelProperty(value = "日志路径")
    private String url;
	@ApiModelProperty(value = "日志类型")
    private String type;
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}