package com.goshine.ptadmui.sys.service;

import java.util.List;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.sys.entity.Permission;
import com.goshine.ptadmui.sys.vo.PermissionAuthVo;
/**
 * 权限Service接口层
 * @author goshine
 */
public interface PermissionService extends BaseService<Permission>{
    /**
     * 根据id查询
     * @param id
     * @return
     */
   public Permission queryById(Context context,String id) throws AdmuiException;
    /**
     * 条件查询
     * @param record
     * @return
     */
   public List<Permission> queryListByCond(Context context,Permission record) throws AdmuiException;
   /**
    * 根据角色ID集合获得权限列表
    * @param roleIds
    * @return
    * @throws AdmuiException
    */
   public List<Permission> queryPermissionListByRoleIds(List<String> roleIds);
    /**
     * 根据角色ID左关联查询权限列表信息 
     * @param context
     * @param roleId
     * @param parentId
     * @return
     */
    public List<Permission> queryPermissionListByRoleId(Context context,String roleId);
    /**
     * 权限分配菜单和操作
     * @param context
     * @param model
     * @return
     * @throws AdmuiException
     */
	public int assign(Context context, PermissionAuthVo model) throws AdmuiException;
	/**
	 * 查询某权限下所有的有权限的菜单ID集合
	 * @param permissionId
	 * @return
	 * @throws AdmuiException
	 */
	 public List<String> queryMenuIdListByPermissionId(String permissionId) throws AdmuiException;
}
