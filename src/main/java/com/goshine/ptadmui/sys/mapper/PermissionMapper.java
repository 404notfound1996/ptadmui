package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Permission;
import java.util.List;

import org.apache.ibatis.annotations.Param;
/**
 * 权限Mapper
 * @author goshine
 */
public interface PermissionMapper extends BaseMapper<Permission>{
    /**
     * 根据ID查询权限信息
     * @param id
     * @return
     */
	Permission queryById(String id);
    /**
     * 根据条件查询权限信息列表
     * @param page
     * @return
     */
    List<Permission> queryListByCond(Permission model);
    /**
     * 根据条件分页查询权限信息
     * @param page
     * @return
     */
	List<Permission> queryListByPage(Page<Permission> page);
	/**
	 * 根据角色ID集合查询角色拥有的权限
	 * @param roleIds
	 * @return
	 */
	List<Permission> queryPermissionListByRoleIds(@Param("list") List<String> roleIds);
	/**
	 * 根据角色ID左关联查询权限列表信息
	 * @param parentId
	 * @param roleId
	 * @return
	 */
	List<Permission> queryPermissionListByRoleId(String roleId);
}