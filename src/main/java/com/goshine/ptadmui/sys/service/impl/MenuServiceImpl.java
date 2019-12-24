package com.goshine.ptadmui.sys.service.impl;

import java.util.*;

import com.goshine.ptadmui.sys.vo.menu.FrontMenu;
import com.goshine.ptadmui.sys.vo.menu.NavigationBar;
import com.goshine.ptadmui.sys.vo.menu.Sidebar;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.base.BaseServiceImpl;
import com.goshine.ptadmui.core.consts.Constant;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Menu;
import com.goshine.ptadmui.sys.mapper.MenuMapper;
import com.goshine.ptadmui.sys.mapper.PermissionMenuMapper;
import com.goshine.ptadmui.sys.service.MenuService;
/**
 * 菜单业务实现类
 * @author goshine
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService{
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private PermissionMenuMapper pmMapper;
	@Override
	protected BaseMapper<Menu> getMapper() {
		return menuMapper;
	}
	/**
	 * 保存菜单
	 * layer字段逻辑判断有问题
	 */
	@Override
	public int saveAll(Context context, List<Menu> menuList) throws AdmuiException{
		int result=0;
		List<Menu> list = new ArrayList<Menu>();
		//layer字段逻辑判断有问题
		if(menuList != null && !menuList.isEmpty()){
			// 此处menu一定是顶部(一级)菜单
			for(Menu menu:menuList){
//				menu.setParentId("0");
				// 新增的顶部(一级)菜单需要生成layer
				if(StringHandler.isNullOrEmpty(menu.getId())){
					menu.setType("add");
					menu.setLayer(generateFirstLayer());
				}else{//顶部菜单编辑时
					menu.setType("update");
				}
				rebuildMenuList(context,menu,list);
			}
		}
		List<String> menuIdList=new ArrayList<String>();//用于存放新增菜单的ID
		Map<String, Menu> layerMap = new HashMap<String, Menu>();
		for(Menu menu: list){
			if("update".equals(menu.getType())){
				//更新菜单
				//字段合法性校验
		    	boolean isSuccess=this.validateModel(menu);
		        if(isSuccess){
		        	menu.setModifyUser(context.getUserId());
					menu.setModifyDate(context.getCurrentDateTime());
					result+=menuMapper.update(menu);
		        }
			}else if("add".equals(menu.getType())){
				//用map将新增的顶部(一级)菜单的menuId置入子菜单parentId
				if(StringHandler.isNullOrEmpty(menu.getParentId())){
					menu.setParentId(layerMap.get(menu.getLayer().substring(0, menu.getLayer().length() - 2)).getId());
				}
				// 保存菜单
				//字段合法性校验
		    	boolean isSuccess=this.validateModel(menu);
		        if(isSuccess){
					menu.setCreateUser(context.getUserId());
					menu.setCreateDate(context.getCurrentDateTime());
					menu.setModifyUser(context.getUserId());
					menu.setModifyDate(context.getCurrentDateTime());
					result+=menuMapper.insert(menu);
					layerMap.put(menu.getLayer(), menu);
					menuIdList.add(menu.getId());
		        }
			}
		}
		//新增菜单不为空，则将新增菜单关联到超级管理员权限下
		if(!menuIdList.isEmpty()){
			pmMapper.batchInsertWithMenu(Constant.ADMIN__PERMISSION_CODE,menuIdList);
		}
		return result;
	}

	/**
	 * 新增菜单
	 * @param model
	 * @return
	 */
	
    @Override
    @Transactional
    public int insert(Context context,Menu model) throws AdmuiException{
    	if(model==null){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setId(IDCreater.genetateKey());
        	model.setCreateUser(context.getUserId());
        	model.setCreateDate(context.getCurrentDateTime());
        	model.setModifyUser(context.getUserId());
        	model.setModifyDate(context.getCurrentDateTime());
        	if(StringHandler.isNullOrEmpty(model.getParentId())||"0".equals(model.getParentId())){//当为顶级菜单时
        		model.setParentId("0");
        		model.setLayer(this.generateFirstLayer());
        	}else{//非一级菜单
        		model.setLayer(this.generateOtherLayer(model.getParentId()));
        	}
        	return menuMapper.insert(model);
        }
        return -1;
    }
    /**
     * 更新菜单
     * @param model
     * @return
     */
    @Override
    public int update(Context context,Menu model) throws AdmuiException{
    	if(model==null||StringHandler.isNullOrEmpty(model.getId())){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setModifyUser(context.getUserId());
        	model.setModifyDate(context.getCurrentDateTime());
        	return menuMapper.update(model);
        }
        return -1;
    }
    /**
     * 根据ID批量删除菜单
     * @param list
     * @return
     */
    @Override
    public int deleteByIds(Context context,List<String> idList) throws AdmuiException{
    	if(idList==null||idList.isEmpty()){
    		throw new AdmuiException("请先选择删除项！");
    	}
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("menuId",idList.get(0));
    	//删除权限菜单关系
    	pmMapper.deleteByParams(params);
    	//删除菜单
    	return menuMapper.deleteByParams(params);
    }
    /**
     * 根据ID查询菜单信息
     * @param id
     * @return
     */
    @Override
    public Menu queryById(Context context,String id) throws AdmuiException{
    	if(StringHandler.isNullOrEmpty(id)){
    		throw new AdmuiException("参数异常！");
    	}
    	return menuMapper.queryById(id);
    }
    
    /**
     * 根据条件查询菜单信息列表
     * @param model
     * @return
     */
    @Override
    public List<Menu> queryListByCond(Context context,Menu model){
    	if(model==null){
    		model=new Menu();
    	}
    	return menuMapper.queryListByCond(model);
    }
    /**
     * 生成一级菜单的排序编码
     * @return
     */
	private String generateFirstLayer(){
		String layer=menuMapper.generateFirstLayer();
		if(layer!=null&&layer.indexOf(".")>-1){
			layer=layer.substring(0,layer.indexOf("."));
		}
		if(layer.length()==1){
			layer="0"+layer;
		}
		return layer;
	}
	/**
     * 生成非一级菜单的排序编码
     * @return
     */
	private String generateOtherLayer(String parentId){
		String layer=menuMapper.generateOtherLayer(parentId);
		if(!StringHandler.isNullOrEmpty(layer)&&layer.length()%2==1){
			layer = "0"+layer;
		}else{
			Menu model=menuMapper.queryById(parentId);
			layer=model.getLayer()+"01";
		}
		return layer;
	}
	
	/**
     * <p>递归将菜单及其子菜单置入list</p>
     * @param  menu
     * @param  list
     */
	private void rebuildMenuList(Context context,Menu menu,List<Menu> list){
		if (menu!=null){
			if(StringHandler.isNullOrEmpty(menu.getId())){
				menu.setId(IDCreater.genetateKey());
			}
			list.add(menu);

			if(menu.getChildren()!=null&&!menu.getChildren().isEmpty()){
				menu.setUrl("#".equals(menu.getUrl())?null:menu.getUrl());
				Menu info=null;
				String layer = "";
				for(int i=0;i<menu.getChildren().size();i++){
					info=menu.getChildren().get(i);
					// 同级菜单顺序发生变化，他的兄弟菜单和子菜单均需要一起重新生成					
					if(i<9){
						layer=menu.getLayer()+"0"+(i+1);
					}else{
						layer=menu.getLayer()+(i+1);
					}
					info.setLayer(layer); // 根据顶部菜单生成并设置layer
					info.setParentId(menu.getId());
					info.setCreateUser(context.getUserId());
					info.setModifyUser(context.getUserId());				
					rebuildMenuList(context,info,list);
				}
			}else{
				menu.setUrl(StringHandler.isNullOrEmpty(menu.getUrl())?"#":menu.getUrl());
			}
		}
	}
   /**
    * 查询当前登录用户所拥有的菜单权限信息
    * 无层级
    * @param context
    * @return
    */
	@Override
	public List<Menu> queryMenuListByContext(Context context) throws AdmuiException{
		if(context==null){
			throw new AdmuiException("登录已失效！");
		}
    	return menuMapper.queryMenuListByAuth(context.getUserId(),null);
    }
   /**
    * 查询当前登录用户所拥有的菜单权限信息
    * @param context
    * @return
    */
	@Override
    public List<Menu> queryMenuListByUserId(Context context){
    	return queryMenuListByAuth(context,"0",null);
    }

	/**
	 * 迭代获取菜单树
	 * @param parentId
	 * @return
	 */
	private List<Menu> queryMenuListByAuth(Context context,String parentId,List<Menu> menuList){
		List<Menu> treeList=new ArrayList<Menu>();
	    try{
			//根据父类ID查询子类菜单
			if(parentId==null||parentId=="0"){
		        menuList=menuMapper.queryMenuListByAuth(context.getUserId(),parentId);
			}
	        for(Menu menu:menuList){
	        	//根据父类ID查询子菜单列表
	        	List<Menu> childMenuList=menuMapper.queryMenuListByAuth(context.getUserId(),menu.getId());
	        	if(childMenuList!=null&&!childMenuList.isEmpty()){
	        		menu.setChildren(this.queryMenuListByAuth(context,menu.getId(),childMenuList));
	        	}
	        	treeList.add(menu);
	        }
	    }catch(Exception e){
	    	logger.debug("MenuServiceImpl queryMenuListByParentId 获取菜单树异常！"+e.getMessage());
	    }
        return treeList;
	}
    
    /**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(Menu model) throws AdmuiException{
    	//合法性校验
    	if(StringHandler.isNullOrEmpty(model.getName())){
    		throw new AdmuiException("菜单名称不能为空！");
    	}
    	if(StringHandler.empty(model.getName()).length()>60){
    		throw new AdmuiException("菜单名称长度不能超过60字符！");
    	}
    	return true;
    }
    /**
     * 根据父类ID左关联获取菜单列表
     * @param context
     * @param permissionId  权限ID
     * @param parentId  父类ID
     * @return
     */
	@Override
	public List<Menu> queryPermissionMenuListByParentId(Context context, String permissionId, String parentId){
		if(StringHandler.isNullOrEmpty(permissionId)||StringHandler.isNullOrEmpty(parentId)){
			return new ArrayList<Menu>();
		}
		return menuMapper.queryPermissionMenuListByParentId(permissionId,parentId);
	}
   /**
    * 更新顶部菜单顺序
    * @param menuList
    * @return
    */
	@Override
    public int updateTopOrder(List<Menu> menuList){
    	int result=0;
    	Menu cond=new Menu();
    	cond.setParentId("0");
    	List<Menu> topMenuList=menuMapper.queryListByCond(cond);
		for (Menu menu:topMenuList){
			for (Menu topMenu:menuList) {
				if (menu.getId().equals(topMenu.getId())) {
					if (topMenu.getOrderNo().length() == 1) {
						topMenu.setOrderNo("0" + topMenu.getOrderNo());
					}
					topMenu.setOriginalOrderNo(menu.getLayer().substring(0, 2) + "%");
					topMenu.setIds(queryTopChildrenIds(topMenu));
					break;
				}
			}
		}
		for(Menu topMenu:menuList){
			topMenu.setUrl("#".equals(topMenu.getUrl())?null:topMenu.getUrl());
			result+=menuMapper.updateTopOrder(topMenu);
		}
		return result;
    }

	@Override
	public FrontMenu convertFrontMenu(List<Menu> menus) {
		if(Objects.isNull(menus)) {return new FrontMenu();}
		List<Menu> treeMenu=new ArrayList<>();
		for(Menu menu:menus){
			if("0".equals(menu.getParentId())) {
				treeMenu.add(menu);
				buildTreeMenu(menu, menus);
			}
		}
		FrontMenu frontMenu=new FrontMenu();
		Map<String,NavigationBar> navigationBarMaps=new HashMap<>();
		List<Menu> sidebarList=new ArrayList<>();
		for(Menu menu:treeMenu){
			NavigationBar navigationBar=new NavigationBar();
			navigationBar.setMenuId(menu.getId());
			navigationBar.setParentId(menu.getParentId());
			navigationBar.setHideSider(false);
			navigationBar.setIcon(menu.getIcon());
			navigationBar.setName(menu.getParentId()+"_"+menu.getId());
			navigationBar.setPath(menu.getUrl());
			navigationBar.setTitle(menu.getName());
			navigationBar.setLayer(menu.getLayer());
			navigationBarMaps.put(menu.getId(),navigationBar);
			if(!Objects.isNull(menu.getChildren())){sidebarList.addAll(menu.getChildren());}
		}
        //将输出的frontMenu 根据字段getLayer排序
		List<NavigationBar> navigationBarList = new ArrayList<>(navigationBarMaps.values());
		Collections.sort(navigationBarList,Comparator.comparing(x->x.getLayer()));
		frontMenu.setNavigationBars(navigationBarList);

		Sidebar parentSidebar=new Sidebar();
		rebuildSidebarList(parentSidebar,sidebarList);
		frontMenu.setSidebars(parentSidebar.getChildren());
		//一级侧边栏设置与导航栏关联关系
		if(Objects.isNull(parentSidebar.getChildren())) {return frontMenu;}
		for(Sidebar sidebar:parentSidebar.getChildren()){
			if(Objects.isNull(sidebar.getParentId())){continue;}
			NavigationBar navigationBar=navigationBarMaps.get(sidebar.getParentId());
			if(!Objects.isNull(navigationBar)){
				sidebar.setHeader(navigationBar.getName());
			}
		}
		return frontMenu;
	}

	/**
	 * 将菜单组装为树形菜单
	 * @param parentMenu 父节点菜单
	 * @param menus 菜单集合
	 */
	private void buildTreeMenu(Menu parentMenu,List<Menu> menus){
		if(Objects.isNull(menus) || Objects.isNull(parentMenu)) {return;}
		ArrayList<Menu> menuArrayList = new ArrayList<>();
		for(Menu menu:menus){
			if(!Objects.isNull(menu) && !StringUtils.isBlank(parentMenu.getId()) && parentMenu.getId().equals(menu.getParentId())){
			  menuArrayList.add(menu);
			}
		}
		//将输出的frontMenu的子菜单 根据字段getLayer排序
		Collections.sort(menuArrayList,Comparator.comparing(x->x.getLayer()));
		if(Objects.isNull(parentMenu.getChildren())){parentMenu.setChildren(new ArrayList<Menu>());};
		parentMenu.getChildren().addAll(menuArrayList);
		if(Objects.isNull(parentMenu.getChildren())){ return;}
		for(Menu menu:parentMenu.getChildren()){
			buildTreeMenu(menu,menus);
		}
	}
	/**
	 * 重建侧边栏菜单
	 * @param parentSidebar 父侧边栏菜单对象
	 * @param sidebarList 菜单列表
	 */
	private void rebuildSidebarList(Sidebar parentSidebar,List<Menu> sidebarList){
		List<Sidebar> sidebars=new ArrayList<>();
		for( Menu menu:sidebarList){
			Sidebar sidebar=new Sidebar();
			sidebar.setMenuId(menu.getId());
			sidebar.setParentId(menu.getParentId());
			sidebar.setHeader(menu.getIcon());
			sidebar.setPath(menu.getUrl());
			sidebar.setIcon(menu.getIcon());
			sidebar.setTitle(menu.getName());
			sidebars.add(sidebar);
			if(!Objects.isNull(menu.getChildren())){
				rebuildSidebarList(sidebar,menu.getChildren());
			}
		}
		parentSidebar.setChildren(sidebars);
	}
	/**
     * 查询某顶部菜单的所有子菜单
     * @param topMenu
     * @return
     */
	private List<String> queryTopChildrenIds(Menu topMenu) {
		return menuMapper.queryTopChildrenIds(topMenu);
	}
}
