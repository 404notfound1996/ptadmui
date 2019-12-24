package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.sys.entity.RoleData;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RoleDataMapper extends BaseMapper<RoleData>{
    /**
	* 批量删除角色数据关系
	* @param id
	* @return
	*/
    int batchDeleteByParams(Map<String,Object> params);
   /**
    * 角色数据授权
    * @param list 角色数据权限对象集合
    * @return
    */
	int batchInsert(@Param("list") List<RoleData> list);
	/**
	 * 根据角色ID查询数据ID集合
	 * @param roleId
	 * @return
	 */
	List<String> queryDataIdListByRoleId(@Param("roleId") String roleId);
}