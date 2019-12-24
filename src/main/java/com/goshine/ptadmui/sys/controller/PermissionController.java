package com.goshine.ptadmui.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.CheckBox;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Permission;
import com.goshine.ptadmui.sys.service.PermissionService;
import com.goshine.ptadmui.sys.vo.PermissionAuthVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value="/permission")
@Api("权限Controller")
public class PermissionController extends BaseController{
	@Autowired
	private PermissionService permissionService;
	
	@ApiOperation(value="新增或更新权限", notes="新增或更新权限，新增时ID为空",produces = "application/json")
	@ApiImplicitParam(name="permission", value = "权限对象", paramType="body", required=true, dataType = "Permission")
	@RequestMapping(method= RequestMethod.POST)
	@ResponseBody
	public Response<Permission> savePermission(HttpServletRequest request,@RequestBody Permission model){
		try{
		   if(model==null){
			   return Response.error("参数异常！");
		   }
		   int result=-1;
		   Context context=this.getContext();
		   if(!StringHandler.isNullOrEmpty(model.getId())){
			   result=permissionService.update(context,model);
		   }else{
			   result=permissionService.insert(context,model);
		   }
		   return result>0?Response.success(model,"保存成功！"):Response.error("保存失败！");
		}catch(AdmuiException ae){
			logger.debug(">>> 保存权限信息异常，error:"+ae.getMessage(),ae);
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug(">>> 保存权限信息异常，error:"+e.getMessage(),e);
			return Response.error("保存权限信息异常！");
		}
	}
	
	@ApiOperation(value="删除权限", notes="删除权限",produces = "application/json")
	@ApiImplicitParam(name = "id", value = "权限ID", paramType="path", required=true, dataType = "String")
	@RequestMapping(value="{id}",method= RequestMethod.DELETE)
	@ResponseBody
	public Response<Permission> deletePermission(HttpServletRequest request,@PathVariable("id") String id){
		try{
		   if(StringHandler.isNullOrEmpty(id)){
			   return Response.error("请先选择删除项！");
		   }
		   Context context=this.getContext();
		   List<String> idList=new ArrayList<String>();
		   idList.add(id);
		   int result=permissionService.deleteByIds(context,idList);
		   return result>0?Response.success(null,"删除成功！"):Response.error("删除失败！");
		}catch(AdmuiException ae){
			logger.debug("删除权限信息异常,error:"+ae.getMessage());
			if("warning".equals(ae.getMessage())){
				return Response.warn("该权限下存在可用角色，不允许删除，请解除角色关系后再进行删除操作！");
			}
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug("删除权限信息异常,error:"+e.getMessage(),e);
			return Response.error("删除权限信息异常！");
		}
	}
	
	@ApiOperation(value = "根据id查询权限信息", notes = "查询权限表中某个权限信息",produces = "application/json")
    @ApiImplicitParam(name = "id", value = "权限ID", paramType="path", required=true, dataType = "String")
	@RequestMapping(value = "{id}",method=RequestMethod.GET)
	@ResponseBody
    public Response<Permission> queryPermissionById(HttpServletRequest request,@PathVariable("id") String id){
        try{
        	Context context=this.getContext();
        	Permission model=permissionService.queryById(context,id);
        	return Response.success(model,"查询成功！");
        }catch(AdmuiException ae){
        	logger.debug("获取权限信息异常"+ae.getMessage(),ae);
        	return Response.error(ae.getMessage());
        }
    }
	
	@ApiOperation(value = "根据条件查询权限信息列表", notes = "根据条件查询权限信息列表",produces = "application/json")
    @ApiImplicitParam(name = "name", value = "权限名称",required=false, dataType = "String")
	@RequestMapping(value = "/permissions",method=RequestMethod.GET)
	@ResponseBody
    public Response<List<Permission>> queryPermissionList(HttpServletRequest request,String name){
        try{
        	Context context=this.getContext();
        	Permission queryModel=new Permission();
        	queryModel.setName(name);
        	List<Permission> permissionList=permissionService.queryListByCond(context,queryModel);
        	return Response.success(permissionList,"查询成功！");
        }catch(AdmuiException ae){
        	logger.debug("获取权限列表信息异常"+ae.getMessage(),ae);
        }
        return Response.error("获取权限列表信息异常！");
    }
	
	@ApiOperation(value="权限分配菜单和操作", notes="给某权限批量分配菜单和操作",produces = "application/json")
	@RequestMapping(value="/assign",method= RequestMethod.POST)
	@ResponseBody
	public Response<String> assign(HttpServletRequest request,@RequestBody PermissionAuthVo model){
		try{
		   if(model==null||StringHandler.isNullOrEmpty(model.getPermissionId())){
			   return Response.error("请选择要分配的权限！");
		   }
		   Context context=this.getContext();
		   permissionService.assign(context,model);
		   return Response.success(null,"分配成功！");
		}catch(AdmuiException ae){
			logger.debug(">>> 权限分配异常，error:"+ae.getMessage());
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug(">>> 权限分配异常，error:"+e.getMessage());
			return Response.error("权限分配异常！");
		}
	}
	

	@ApiOperation(value = "查询授权权限树",notes = "左关联查询授权权限树（全部）",produces = "application/json")
    @ApiImplicitParam(name = "roleId", value = "角色ID",required=false, dataType="String")
	@RequestMapping(value="/checkboxlist",method=RequestMethod.GET)
    @ResponseBody
	public Response<List<CheckBox>> queryAuthTree(HttpServletRequest request,String roleId){
		Context context=this.getContext();
		List<CheckBox> checkboxList=new ArrayList<CheckBox>();
		List<Permission> permissionList=permissionService.queryPermissionListByRoleId(context,roleId);
		if(permissionList!=null&&permissionList.size()>0){
			CheckBox tree=null;
			for(Permission permission:permissionList){
				tree=new CheckBox();
				tree.setId(permission.getId());
				tree.setText(permission.getName());
				if(!StringHandler.isNullOrEmpty(permission.getRoleId())){
				    tree.setSelected(true);
				}
				checkboxList.add(tree);
			}
		}
		return Response.success(checkboxList);
	}
}