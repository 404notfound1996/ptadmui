package com.goshine.ptadmui.core.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 树结构模型
 * @author goshine
 */
@SuppressWarnings("serial")
@Data
@ApiModel(value="Tree", description="基础树模型")
public class Tree implements java.io.Serializable{
	 @ApiModelProperty(value = "树ID")
	 @JsonInclude(JsonInclude.Include.NON_NULL)
     private String id;
	 @ApiModelProperty(value = "树ID")
	 @JsonInclude(JsonInclude.Include.NON_NULL)
	 private String dataId; 
	@ApiModelProperty(value = "树节点名称")
     private String title;
	 @ApiModelProperty(value = "类型，U:代表人员，C：代表公司或部门")
	 @JsonInclude(JsonInclude.Include.NON_NULL)
     private String type;
	 @ApiModelProperty(value = "子节点集合")
	 @JsonInclude(JsonInclude.Include.NON_NULL)
     private List<Tree> children;
	 @JsonInclude(JsonInclude.Include.NON_NULL)
	 private TreeType li_attr;//前端要求名称
	 @ApiModelProperty(value = "数据权限ID集合")
	 private List<String> dataIds;
	@ApiModelProperty(value = "是否展开")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	 private Boolean Expand;
	@ApiModelProperty(value = "等于ID")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String  value;
	@ApiModelProperty(value = "tree第一层字段")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean Selected;
	@ApiModelProperty(value = "tree除第一层字段")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean Checked;


     

}