package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.sys.entity.PermissionMenu;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 权限菜单关系Mapper
 * @author goshine
 */
public interface PermissionMenuMapper{
	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	int batchInsert(@Param("list") List<PermissionMenu> list);
	/**
	 * 权限分配菜单
	 * @param permissionId
	 * @param list
	 * @return
	 */
	int batchInsertWithMenu(@Param("permissionId") String permissionId,@Param("list") List<String> list);
	/**
	 * 根据参数删除
	 * @param params
	 * @return
	 */
	int deleteByParams(Map<String,Object> params);
	/**
	 * 根据参数批量删除
	 * @param params
	 * @return
	 */
	int batchDeleteByParams(Map<String,Object> params);
	/**
	 * 根据权限ID查询菜单ID集合
	 * @param permissionId
	 * @return
	 */
	List<String> queryMenuIdListByPermissionId(@Param("permissionId") String permissionId);
	/**
	 * 根据权限ID查询该权限所有的末级菜单ID集合
	 * @param permissionId
	 * @return
	 */
	List<String> queryLeafMenuIdListByPermissionId(@Param("permissionId") String permissionId);
}