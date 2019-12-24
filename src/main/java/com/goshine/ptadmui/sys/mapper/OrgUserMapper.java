package com.goshine.ptadmui.sys.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.goshine.ptadmui.sys.entity.OrgUser;
/**
 * 机构用户关系Mapper
 * @author goshine
 */
public interface OrgUserMapper{
	 /**
     * 根据条件删除
     * @param id
     * @return
     */
   int deleteByParams(Map<String,Object> params);
    /**
     * 用户批量分配部门
     * @param userId
     * @param orgIds
     * @return
     */
    int batchInsertWithOrg(@Param("userId") String userId,@Param("list") List<String> orgIds);
    /**
     * 部门批量分配用户
     * @param orgId
     * @param userIds
     * @return
     */
    int batchInsertWithUser(@Param("orgId") String orgId,@Param("list") List<String> userIds);
    /**
     * 根据用户ID查询组织机构列表
     * @param userId
     * @return
     */
    List<OrgUser> queryOrgListByUserId(@Param("userId") String userId);
}