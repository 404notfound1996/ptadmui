package com.goshine.ptadmui.sys.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
/**
 * 用户角色关系Mapper
 * @author goshine
 */
public interface UserRoleMapper{
	 /**
     * 根据条件删除
     * @param id
     * @return
     */
   int deleteByParams(Map<String,Object> params);
   /**
    * 批量删除用户角色关系
    * @param params
    * @return
    */
   int batchDeleteByParams(Map<String,Object> params);
    /**
     * 用户批量分配角色
     * @param userId
     * @param roleIds
     * @return
     */
    int batchInsertWithRole(@Param("userId") String userId,@Param("list") List<String> roleIds);
    /**
     * 角色批量分配用户
     * @param roleId
     * @param userIds
     * @return
     */
    int batchInsertWithUser(@Param("roleId") String roleId,@Param("list") List<String> userIds);
}