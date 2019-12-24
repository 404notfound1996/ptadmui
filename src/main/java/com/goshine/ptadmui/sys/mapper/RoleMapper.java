package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Role;
import java.util.List;

import org.apache.ibatis.annotations.Param;
/**
 * 角色Mapper
 * @author goshine
 */
public interface RoleMapper extends BaseMapper<Role>{
    /**
     * 根据ID查询角色信息
     * @param id
     * @return
     */
	Role queryById(String id);
    /**
     * 根据条件查询角色信息列表
     * @param page
     * @return
     */
    List<Role> queryListByCond(Role model);
    /**
     * 根据条件分页查询角色信息
     * @param page
     * @return
     */
	List<Role> queryListByPage(Page<Role> page);
	/**
	 * 根据用户ID查询用户所属角色集合
	 * @param userId
	 * @return
	 */
	List<Role> queryRoleListByUserId(@Param("roleType") int roleType,@Param("userId") String userId);
	/**
	 * 根据权限信息查询拥有该权限的角色列表
	 * @param permissionId
	 * @return
	 * @throws AdmuiException
	 */
	List<Role> queryRoleListByPermissionId(@Param("permissionId") String permissionId);

	/**
	 * 根据角色ID查询用户ID集合
	 * @param roleId
	 * @return
	 */
	List<String> queryUserIdListByRoleId(@Param("roleId") String roleId);
}