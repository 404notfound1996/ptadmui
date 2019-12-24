package com.goshine.ptadmui.sys.service;

import java.util.List;
import java.util.Map;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.vo.AuthAssignVo;
import com.goshine.ptadmui.sys.vo.AuthVo;
/**
 * 角色Service接口层
 * @author goshine
 */
public interface RoleService extends BaseService<Role>{
    /**
     * 根据id查询
     * @param id
     * @return
     */
   public Role queryById(Context context,String id) throws AdmuiException;
    /**
     * 条件查询
     * @param record
     * @return
     */
   public List<Role> queryListByCond(Context context,Role record) throws AdmuiException;
    /**
     * 分页条件查询
     * @param record
     * @return
     */
   public Page<Role> queryListByPage(Context context,Page<Role> page) throws AdmuiException;
   /**
    * 根据用户ID查询用户分配的角色集合
    * @param userId
    * @return
    */
	public List<Role> queryRoleListByUserId(int roleType,String userId);
	/**
	 * 角色批量分配用户
	 * @param context
	 * @param model
	 * @return
	 * @throws AdmuiException
	 */
    public int assignUser(Context context, AuthAssignVo model) throws AdmuiException;
    /**
     * 角色授权
     * @param context
     * @param model
     * @return
     * @throws AdmuiException
     */
	public int assignPermission(Context context, AuthVo model) throws AdmuiException;
	/**
	 * 删除角色用户关系
	 * @param info
	 * @return
	 */
	public int deleteUserRole(String roleId,List<String> userIds) throws AdmuiException;

	public Map<String,Object> queryRoleTrees(String structureType,String name,Context context,List<String> dateIds,List<String> userIdList) throws AdmuiException;

	public List<String> queryUserIdListByRoleId(String roleId);
}
