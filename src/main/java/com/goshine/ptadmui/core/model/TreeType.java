package com.goshine.ptadmui.core.model;
/**
 * 角色授权查询组织机构和角色列表
 * 为了前端方便绑定参数
 * @author goshine
 *
 */
@SuppressWarnings("serial")
public class TreeType implements java.io.Serializable{
    private String Id;//节点ID

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
}
