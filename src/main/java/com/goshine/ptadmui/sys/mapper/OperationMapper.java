package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Operation;
import java.util.List;

import org.apache.ibatis.annotations.Param;
/**
 * 操作Mapper
 * @author goshine
 */
public interface OperationMapper extends BaseMapper<Operation>{
    /**
     * 根据ID查询操作信息
     * @param id
     * @return
     */
	Operation queryById(String id);
    /**
     * 根据菜单ID查询操作信息列表
     * @param page
     * @return
     */
    List<Operation> queryListByMenuId(String menuId);
    /**
     * 根据条件查询操作信息列表
     * @param page
     * @return
     */
    List<Operation> queryListByCond(Operation model);
    /**
     * 根据条件分页查询操作信息
     * @param page
     * @return
     */
	List<Operation> queryListByPage(Page<Operation> page);
	/**
	 * 根据菜单ID左关联查询权限操作列表
	 * @param permissionId
	 * @param menuId
	 * @return
	 */
	List<Operation> queryPermissionOperationListByMenuId(@Param("permissionId") String permissionId,@Param("menuId") String menuId);
    /**
     * 根据用户ID查询可操作项的集合
     * @param userId
     * @return
     */
	List<Operation> queryOperationListByUserId(@Param("userId") String userId);
}