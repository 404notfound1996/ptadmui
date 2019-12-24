package com.goshine.ptadmui.sys.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.goshine.ptadmui.sys.entity.Permission;
import com.goshine.ptadmui.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.IconTree;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Menu;
import com.goshine.ptadmui.sys.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value="/menu")
@Api("菜单Controller")
public class MenuController extends BaseController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private PermissionService permissionService;

	@ApiOperation(value = "新增或更新菜单", notes = "新增或更新菜单，新增时ID为空", produces = "application/json")
	@ApiImplicitParam(name = "menu", value = "菜单对象集合", paramType = "body", required = true, dataType = "Menu")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Response<Menu> saveMenu(HttpServletRequest request, @RequestBody Menu menu) {
		try {
			if (menu == null) {
				return Response.error("参数异常！");
			}
			Context context = this.getContext();
			List<Menu> menuList = new ArrayList<Menu>();
			menuList.add(menu);
			menuService.saveAll(context, menuList);
			return Response.success(menu, "保存成功！");
		} catch (AdmuiException ae) {
			logger.debug(">>> 保存菜单信息异常，error:" + ae.getMessage(), ae);
			return Response.error(ae.getMessage());
		} catch (Exception e) {
			logger.debug(">>> 保存菜单信息异常，error:" + e.getMessage(), e);
			return Response.error("保存菜单信息异常！");
		}
	}

	@ApiOperation(value = "删除菜单", notes = "删除菜单", produces = "application/json")
	@ApiImplicitParam(name = "id", value = "菜单ID", paramType = "path", required = true, dataType = "String")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Response<Menu> deleteMenu(HttpServletRequest request, @PathVariable("id") String id) {
		try {
			if (StringHandler.isNullOrEmpty(id)) {
				return Response.error("请先选择删除项！");
			}
			Context context = this.getContext();
			List<String> idList = new ArrayList<String>();
			idList.add(id);
			int result = menuService.deleteByIds(context, idList);
			return result > 0 ? Response.success(null, "删除成功！") : Response.error("删除失败！");
		} catch (AdmuiException ae) {
			logger.debug("删除菜单信息异常,error:" + ae.getMessage(), ae);
			return Response.error(ae.getMessage());
		} catch (Exception e) {
			logger.debug("删除菜单信息异常,error:" + e.getMessage(), e);
			return Response.error("删除菜单信息异常！");
		}
	}

	@ApiOperation(value = "根据id查询菜单信息", notes = "查询菜单表中某个菜单信息", produces = "application/json")
	@ApiImplicitParam(name = "id", value = "菜单ID", paramType = "path", required = true, dataType = "String")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Response<Menu> queryMenuById(HttpServletRequest request, @PathVariable("id") String id) {
		try {
			Context context = this.getContext();
			Menu model = menuService.queryById(context, id);
			return Response.success(model, "查询成功！");
		} catch (AdmuiException ae) {
			logger.debug("获取菜单信息异常" + ae.getMessage(), ae);
			return Response.error(ae.getMessage());
		}
	}

    @RequestMapping(value="/trees",method=RequestMethod.GET)
    @ResponseBody
	public Response<List<Menu>> queryMenuTree(HttpServletRequest request,String orgId){
		Context context=this.getContext();
		List<String> idList = new ArrayList<String>();
		List<Menu> treeList1=queryTreeList(context,orgId,null,idList);

		return Response.success(treeList1);
}


//	@ApiOperation(value = "菜单管理中获取菜单树",notes = "菜单管理中查询菜单树",produces = "application/json")
//    @RequestMapping(value="/trees",method=RequestMethod.GET)
//    @ResponseBody
//	public Response<List<Menu>> queryMenuTree(HttpServletRequest request){
//		Context context=this.getContext();
//		List<Menu> treeList=queryTreeList(context,"0",null);
//
//		return Response.success(treeList);
//	}
	/**
	 * 迭代获取树
	 * @param parentId
	 * @return
	 */
//	private List<Menu> queryTreeList(Context context,String parentId,List<Menu> menuList){
//		List<Menu> treeList=new ArrayList<Menu>();
//		//得到结果为idlist里面套idlist  怎么分页  就算查出来人员信息 也是list套list。所以只能有一个list  里面放所有orgid 然后查询用 in
////		ArrayList<String> idList = new ArrayList<String>();
////		idList.add(parentId);
//		try{
//			//根据父类ID查询子类菜单
//			Menu queryModel=new Menu();
//			if(parentId==null||parentId=="0"){
//				queryModel.setParentId("0");
//				queryModel.setExpand(true);
//		        menuList=menuService.queryListByCond(context,queryModel);
//			}
//	        for(Menu model:menuList){
//	        	//根据父类ID查询子机构列表
//	        	queryModel.setParentId(model.getId());
//	        	model.setExpand(true);
//	        	List<Menu> childList=menuService.queryListByCond(context,queryModel);
//	        	if(childList!=null&&!childList.isEmpty()){
//	        		model.setChildren(this.queryTreeList(context,model.getId(),childList));
//	        	}
//	        	treeList.add(model);
//	        }
//	    }catch(Exception e){
//	    	logger.debug("获取机构树异常！"+e.getMessage());
//	    }
//        return treeList;
//	}
	private List<Menu> queryTreeList(Context context,String parentId,List<Menu> menuList,List<String> idList){
		List<Menu> treeList=new ArrayList<Menu>();
		//得到结果为idlist里面套idlist  怎么分页  就算查出来人员信息 也是list套list。所以只能有一个list  里面放所有orgid 然后查询用 in
//		ArrayList<String> idList = new ArrayList<String>();
		idList.add(parentId);
		try{
			//根据父类ID查询子类菜单
			Menu queryModel=new Menu();
			if(parentId==null||parentId=="0"){
				queryModel.setParentId("0");
				queryModel.setExpand(true);
				menuList=menuService.queryListByCond(context,queryModel);
			}
			for(Menu model:menuList){
				//根据父类ID查询子机构列表
				queryModel.setParentId(model.getId());
				model.setExpand(true);
				List<Menu> childList=menuService.queryListByCond(context,queryModel);
				if(childList!=null&&!childList.isEmpty()){
                    model.setChildren(this.queryTreeList(context,model.getId(),childList,idList));
				}
				treeList.add(model);
			}
		}catch(Exception e){
			logger.debug("获取机构树异常！"+e.getMessage());
		}
		return treeList;
	}



	@ApiOperation(value = "权限管理中获取菜单树",notes = "权限管理中查询菜单树",produces = "application/json")
    @RequestMapping(value="/authmenutrees",method=RequestMethod.GET)
    @ResponseBody
	public Response<List<IconTree>> queryPermissionMenuTree(HttpServletRequest request,String id) throws AdmuiException {
		Context context=this.getContext();
		List<String> listIds =permissionService.queryMenuIdListByPermissionId(id);
		List<IconTree> treeList=queryPermissionTreeList(context,"0",null,listIds);
		return Response.success(treeList);
	}
	/**
	 * 权限管理中迭代获取树
	 * @param parentId
	 * @return
	 */
	private List<IconTree> queryPermissionTreeList(Context context,String parentId,List<Menu> menuList,List<String> listIds){
		List<IconTree> treeList=new ArrayList<IconTree>();
		try{
			//根据父类ID查询子类菜单
			Menu queryModel=new Menu();
			if(parentId==null||parentId=="0"){
				queryModel.setParentId("0");

				menuList=menuService.queryListByCond(context,queryModel);
			}
			IconTree tree=null;
			for(Menu model:menuList){
				queryModel.setParentId(model.getId());
				List<Menu> childList=menuService.queryListByCond(context,queryModel);
				//根据父类ID查询子菜单列表
				tree=new IconTree();
				//判断ListIds权限集合中是否包含该菜单权限
				if(parentId==null||parentId=="0"){
					tree.setSelected(false);
					tree.setSelected(listIds.contains(model.getId()));
				}else{
					tree.setChecked(false);
					tree.setChecked(listIds.contains(model.getId()));
				}
				tree.setId(model.getId());
				tree.setText(model.getName());
				tree.setIcon(model.getIcon());
				tree.setExpand(true);
				if(childList!=null&&!childList.isEmpty()){
					tree.setChildren(this.queryPermissionTreeList(context,model.getId(),childList,listIds));
				}
				treeList.add(tree);
			}
		}catch(Exception e){
			logger.debug("获取权限授权菜单树异常！"+e.getMessage());
		}
		return treeList;
	}
//	/**
//	 * 权限管理中迭代获取树
//	 * @param parentId
//	 * @return
//	 */
//	private List<IconTree> queryPermissionTreeList(Context context,String parentId,List<Menu> menuList){
//		List<IconTree> treeList=new ArrayList<IconTree>();
//	    try{
//			//根据父类ID查询子类菜单
//			Menu queryModel=new Menu();
//			if(parentId==null||parentId=="0"){
//				queryModel.setParentId("0");
//
//		        menuList=menuService.queryListByCond(context,queryModel);
//			}
//			IconTree tree=null;
//	        for(Menu model:menuList){
//	        	queryModel.setParentId(model.getId());
//	        	List<Menu> childList=menuService.queryListByCond(context,queryModel);
//	        	//根据父类ID查询子菜单列表
//
//	        	tree=new IconTree();
//				if(parentId==null||parentId=="0"){
//					tree.setSelected(false);
//				}else{
//					tree.setChecked(false);
//				}
//
//				tree.setChecked();
//	        	tree.setId(model.getId());
//
//	        	tree.setText(model.getName());
//	        	tree.setIcon(model.getIcon());
//	        	tree.setExpand(true);
//	        	if(childList!=null&&!childList.isEmpty()){
//	        		tree.setChildren(this.queryPermissionTreeList(context,model.getId(),childList));
//	        	}
//	        	treeList.add(tree);
//	        }
//	    }catch(Exception e){
//	    	logger.debug("获取权限授权菜单树异常！"+e.getMessage());
//	    }
//        return treeList;
//	}

	@ApiOperation(value = "调整顶部菜单顺序",notes = "菜单管理中调整顶部菜单顺序",produces = "application/json")
	@ApiImplicitParam(name = "menuList", value = "菜单对象", paramType="body", required=true, dataType = "Menu")
    @RequestMapping(value="updatetoporder",method=RequestMethod.POST)
    @ResponseBody
	public Response<Menu> updateTopOrder(@RequestBody List<Menu> menuList){
		try {
		    if(menuList==null||menuList.size()<=0){
		    	return Response.error("参数异常！");
		    }
			int result=menuService.updateTopOrder(menuList);
			return result>0?Response.success(null,"保存调整成功！"):Response.error("保存调整失败！");
		} catch (Exception e) {
			logger.error("更改顶部菜单顺序失败！", e);
			return Response.error("保存调整失败！");
		}
	}
}