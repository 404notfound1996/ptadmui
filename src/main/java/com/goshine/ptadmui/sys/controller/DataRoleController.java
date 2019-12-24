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
import com.goshine.ptadmui.core.enums.Enums;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value="/datarole")
@Api(value = "/datarole",description="数据角色Controller")
public class DataRoleController extends BaseController{
	@Autowired
	private RoleService roleService;
	
	@ApiOperation(value="新增或更新数据角色", notes="新增或更新数据角色，新增时ID为空",produces = "application/json")
	@ApiImplicitParam(name="model", value = "角色对象", paramType="body", required=true, dataType = "Role")
	@RequestMapping(method= RequestMethod.POST)
	@ResponseBody
	public Response<Role> saveRole(HttpServletRequest request,@RequestBody Role model){
		try{
		   if(model==null){
			   return Response.error("参数异常！");
		   }
		   int result=-1;
		   Context context=this.getContext();
		   model.setRoleType(Enums.RoleType.DataRole.getIndex());
		   if(!StringHandler.isNullOrEmpty(model.getId())){
			   result=roleService.update(context,model);
		   }else{
			   result=roleService.insert(context,model);
		   }
		   return result>0?Response.success(model,"保存成功！"):Response.error("保存失败！");
		}catch(AdmuiException ae){
			logger.debug(">>> 保存数据角色信息异常，error:"+ae.getMessage(),ae);
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug(">>> 保存数据角色信息异常，error:"+e.getMessage(),e);
			return Response.error("保存数据角色信息异常！");
		}
	}
	
	@ApiOperation(value="删除数据角色", notes="删除数据角色",produces = "application/json")
	@ApiImplicitParam(name = "id", value = "角色ID", paramType="path", required=true, dataType = "String")
	@RequestMapping(value="{id}",method= RequestMethod.DELETE)
	@ResponseBody
	public Response<Role> deleteRole(HttpServletRequest request,@PathVariable("id") String id){
		try{
		   if(StringHandler.isNullOrEmpty(id)){
			   return Response.error("请先选择删除项！");
		   }
		   Context context=this.getContext();
		   List<String> idList=new ArrayList<String>();
		   idList.add(id);
		   int result=roleService.deleteByIds(context,idList);
		   return result>0?Response.success(null,"删除成功！"):Response.error("删除失败！");
		}catch(AdmuiException ae){
			logger.debug("删除数据角色信息异常,error:"+ae.getMessage(),ae);
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug("删除数据角色信息异常,error:"+e.getMessage(),e);
			return Response.error("删除数据角色信息异常！");
		}
	}
	
	@ApiOperation(value = "根据id查询数据角色信息", notes = "查询数据角色表中某个数据角色信息",produces = "application/json")
    @ApiImplicitParam(name = "id", value = "角色ID", paramType="path", required=true, dataType = "String")
	@RequestMapping(value = "{id}",method=RequestMethod.GET)
	@ResponseBody
    public Response<Role> queryRoleById(HttpServletRequest request,@PathVariable("id") String id){
        try{
        	Context context=this.getContext();
        	Role model=roleService.queryById(context,id);
        	return Response.success(model,"查询成功！");
        }catch(AdmuiException ae){
        	logger.debug("获取数据角色信息异常"+ae.getMessage(),ae);
        	return Response.error(ae.getMessage());
        }
    }
	
	@ApiOperation(value = "分页查询数据角色信息列表", notes = "分页查询数据角色信息列表",produces = "application/json")
    @ApiImplicitParam(name = "page", value = "分页对象", paramType="body", required=true, dataType = "Page")
	@RequestMapping(value = "/dataroles",method=RequestMethod.GET)
	@ResponseBody
    public Response<Page<Role>> queryRoleListByPage(HttpServletRequest request,Page<Role> page){
        try{
        	Context context=this.getContext();
        	page=roleService.queryListByPage(context,page);
			return Response.success(page);
        }catch(AdmuiException ae){
        	logger.debug("获取数据角色信息异常"+ae.getMessage(),ae);
        	return Response.error("获取失败");
        }

}
}
