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
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.DropDownModel;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.RoleGroup;
import com.goshine.ptadmui.sys.service.RoleGroupService;
import com.goshine.ptadmui.sys.vo.RoleGroupVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value="/rolegroup")
@Api("角色组Controller")
public class RoleGroupController extends BaseController{
	@Autowired
	private RoleGroupService roleGroupService;
	
	@ApiOperation(value="新增或更新角色组", notes="新增或更新角色组，新增时ID为空",produces = "application/json")
	@ApiImplicitParam(name="rolegroup", value = "角色组对象", paramType="body", required=true, dataType = "RoleGroup")
	@RequestMapping(method= RequestMethod.POST)
	@ResponseBody
	public Response<RoleGroup> saveRoleGroup(HttpServletRequest request,@RequestBody RoleGroup model){
		try{
		   if(model==null){
			   return Response.error("参数异常！");
		   }
		   int result=-1;
		   Context context=this.getContext();
		   if(!StringHandler.isNullOrEmpty(model.getId())){
			   result=roleGroupService.update(context,model);
		   }else{
			   result=roleGroupService.insert(context,model);
		   }
		   return result>0?Response.success(model,"保存成功！"):Response.error("保存失败！");
		}catch(AdmuiException ae){
			logger.debug(">>> 保存角色组信息异常，error:"+ae.getMessage(),ae);
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug(">>> 保存角色组信息异常，error:"+e.getMessage(),e);
			return Response.error("保存角色组信息异常！");
		}
	}
	
	@ApiOperation(value="删除角色组", notes="删除角色组",produces = "application/json")
	@ApiImplicitParam(name = "id", value = "角色ID", paramType="path", required=true, dataType = "String")
	@RequestMapping(value="{id}",method= RequestMethod.DELETE)
	@ResponseBody
	public Response<RoleGroup> deleteRoleGroup(HttpServletRequest request,@PathVariable("id") String id){
		try{
		   if(StringHandler.isNullOrEmpty(id)){
			   return Response.error("请先选择删除项！");
		   }
		   Context context=this.getContext();
		   List<String> idList=new ArrayList<String>();
		   idList.add(id);
		   int result=roleGroupService.deleteByIds(context,idList);
		   return result>0?Response.success(null,"删除成功！"):Response.error("删除失败！");
		}catch(AdmuiException ae){
			logger.debug("删除角色组信息异常,error:"+ae.getMessage(),ae);
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug("删除角色组信息异常,error:"+e.getMessage(),e);
			return Response.error("删除角色组信息异常！");
		}
	}
	@ApiOperation(value = "获取角色组下拉列表", notes = "获取全部角色组下拉列表",produces = "application/json")
	@RequestMapping(value = "/dropdownlist",method=RequestMethod.GET)
	@ResponseBody
    public Response<List<DropDownModel>> queryRoleListByPage(HttpServletRequest request){
		List<DropDownModel> dropdownList=new ArrayList<DropDownModel>();
        try{
        	Context context=this.getContext();
        	List<RoleGroupVo> list=roleGroupService.queryAll(context,null);
        	if(list!=null&&list.size()>0){
        		DropDownModel model=null;
        		for(RoleGroupVo vo:list){
        			model=new DropDownModel();
        			model.setId(vo.getId());
        			model.setText(vo.getGroupName());
        			dropdownList.add(model);
        		}
        	}
        }catch(AdmuiException ae){
        	logger.debug("获取角色组信息异常"+ae.getMessage(),ae);
        }
        return Response.success(dropdownList,"获取成功！");
    }
}