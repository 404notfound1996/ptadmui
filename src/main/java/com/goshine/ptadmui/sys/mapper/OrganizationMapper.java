package com.goshine.ptadmui.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Organization;

/**
 * 组织机构DAO
 * @author goshine
 */
public interface OrganizationMapper extends BaseMapper<Organization>{
    /**
     * 根据ID查询组织机构信息
     * @param id
     * @return
     */
    Organization queryById(String id);
    /**
     * 根据条件查询组织机构信息列表
     * @param page
     * @return
     */
    List<Organization> queryListByCond(Organization model);
    /**
     * 根据条件分页查询组织机构信息
     * @param page
     * @return
     */
	List<Organization> queryListByPage(Page<Organization> page);
    /**
     * 验证组织机构编码和组织机构名称是否存在
     * @param model
     * @return
     */
    List<Organization> checkOrganizationInfo(Organization model);
	/**
	 * 根据组织机构ID获取父节点的ID字符串
	 * @param orgId
	 * @return
	 */
	String getParentIdListByOrgId(@Param("orgId") String orgId);
	/**
	 * 根据组织机构ID获取子节点的ID字符串
	 * @param orgId
	 * @return
	 */
	String getChildIdListByOrgId(@Param("orgId") String orgId);
	/**
	 * 根据系统上下文获取拥有权限的机构ID集合
	 * @param orgId
	 * @return
	 */
	List<String> getAuthOrgListByContext(@Param("context") Context context);
}
