package com.goshine.ptadmui.sys.service;

import java.util.List;
import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.sys.entity.Menu;
import com.goshine.ptadmui.sys.vo.menu.FrontMenu;

/**
 * 菜单Service接口层
 * @author goshine
 */
public interface MenuService extends BaseService<Menu>{
	/**
	 * 批量保存菜单信息
	 * @param context
	 * @param menuList 菜单集合
	 * @return
	 * @throws AdmuiException
	 */
	public int saveAll(Context context,List<Menu> menuList) throws AdmuiException;
    /**
     * 根据id查询
     * @param id
     * @return
     */
   public Menu queryById(Context context,String id) throws AdmuiException;
    /**
     * 条件查询
     * @param record
     * @return
     */
   public List<Menu> queryListByCond(Context context,Menu record) throws AdmuiException;
   /**
    * 查询当前登录用户所拥有的菜单权限信息
    * 无层级
    * @param context
    * @return
    */
    public List<Menu> queryMenuListByContext(Context context) throws AdmuiException;
   /**
    * 查询当前登录用户所拥有的菜单权限信息
    * 有层级
    * @param context
    * @return
    */
   public List<Menu> queryMenuListByUserId(Context context);
   /**
    * 根据父类ID左关联获取菜单列表
    * @param context
    * @param permissionId  权限ID
    * @param parentId  父类ID
    * @return
    */
   public List<Menu> queryPermissionMenuListByParentId(Context context,String permissionId,String parentId);
   /**
    * 更新顶部菜单顺序
    * @param menuList
    * @return
    */
    public int updateTopOrder(List<Menu> menuList);

    /**
     * 根据当前登录用户所拥有的前置菜单权限信息封装前置菜单
     * @param menus 当前登录用户具有的菜单权限列表
     */
    public FrontMenu convertFrontMenu(List<Menu> menus);
}
