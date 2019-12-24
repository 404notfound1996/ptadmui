package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.sys.entity.Menu;
import java.util.List;

import org.apache.ibatis.annotations.Param;
/**
 * 菜单Mapper
 * @author goshine
 */
public interface MenuMapper extends BaseMapper<Menu>{
	/**
	 * 更新顶部菜单顺序
	 * @param menu
	 * @return
	 */
	int updateTopOrder(Menu menu);
    /**
     * 根据ID查询菜单信息
     * @param id
     * @return
     */
	Menu queryById(String id);
    /**
     * 根据条件查询菜单信息列表
     * @param page
     * @return
     */
    List<Menu> queryListByCond(Menu model);
    /**
     * 获取一级菜单排列码
     * @return
     */
    String generateFirstLayer();
    /**
     * 获取非一级菜单的排序编码
     * @param parentId
     * @return
     */
	String generateOtherLayer(String parentId);
	/**
	 * 根据父类ID查询当前登录用户拥有的所有菜单权限
	 * @param userId
	 * @param parentId
	 * @return
	 */
	List<Menu> queryMenuListByAuth(@Param("userId") String userId,@Param("parentId") String parentId);
	/**
	 * 根据父类ID左关联查询权限菜单列表
	 * @param permissionId
	 * @param parentId
	 * @return
	 */
	List<Menu> queryPermissionMenuListByParentId(@Param("permissionId") String permissionId,@Param("parentId") String parentId);
	/**
	 * 查询某顶部菜单的所有子菜单
	 * 包括孙子菜单
	 * @param topMenu
	 * @return
	 */
	List<String> queryTopChildrenIds(Menu topMenu);
}