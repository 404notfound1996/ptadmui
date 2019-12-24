package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.sys.entity.PermissionOperation;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 权限操作关系Mapper
 * @author goshine
 */
public interface PermissionOperationMapper{
	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	int batchInsert(@Param("list") List<PermissionOperation> list);
	/**
	 * 权限分配操作
	 * @param permissionId
	 * @param list
	 * @return
	 */
	int batchInsertWithOperation(@Param("permissionId") String permissionId,@Param("list") List<String> list);
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
	 * 根据权限ID查询操作ID集合
	 * @param permissionId
	 * @return
	 */
	List<String> queryOperIdListByPermissionId(@Param("permissionId") String permissionId);
}