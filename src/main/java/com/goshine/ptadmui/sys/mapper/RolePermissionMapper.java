package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.sys.entity.RolePermission;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapper extends BaseMapper<RolePermission>{
   /**
	* 根据条件删除
	* @param id
	* @return
	*/
    int deleteByParams(Map<String,Object> params);
    /**
	* 批量删除角色权限关系
	* @param id
	* @return
	*/
    int batchDeleteByParams(Map<String,Object> params);
   /**
    * 角色授权
    * @param list 角色权限对象集合
    * @return
    */
	int batchInsert(@Param("list") List<RolePermission> list);

	List<String> queryByRoleId(String id);
}