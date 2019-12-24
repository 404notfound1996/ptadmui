package com.goshine.ptadmui.sys.service;

import java.util.List;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Operation;
/**
 * 操作项Service接口层
 * @author goshine
 */
public interface OperationService extends BaseService<Operation>{
    /**
     * 根据id查询
     * @param id
     * @return
     */
   public Operation queryById(Context context,String id) throws AdmuiException;
   /**
    * 根据menuId查询
    * @param id
    * @return
    */
   public List<Operation> queryListByMenuId(Context context,String menuId) throws AdmuiException;
    /**
     * 条件查询
     * @param record
     * @return
     */
    public List<Operation> queryListByCond(Context context,Operation record) throws AdmuiException;
    /**
     * 分页条件查询
     * @param record
     * @return
     */
    public Page<Operation> queryListByPage(Context context,Page<Operation> page) throws AdmuiException;
    /**
     * 根据菜单ID左关联查询权限按钮列表
     * @param context
     * @param permissionId 权限ID
     * @param menuId 菜单ID
     */
	public List<Operation> queryPermissionOperationListByMenuId(Context context, String permissionId, String menuId);
    /**
     * 根据用户ID查询可操作的操作项集合
     * @param context
     */
	public List<Operation> queryOperationListByContext(Context context);
}
