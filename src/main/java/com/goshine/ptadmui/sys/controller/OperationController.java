package com.goshine.ptadmui.sys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.CheckBox;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Operation;
import com.goshine.ptadmui.sys.service.OperationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value="/operation")
@Api("操作Controller")
public class OperationController extends BaseController{
	@Autowired
	private OperationService operationService;
	
	@ApiOperation(value="新增或更新操作项", notes="新增或更新操作项，新增时ID为空",produces = "application/json")
	@ApiImplicitParam(name="operation", value = "操作对象", paramType="body", required=true, dataType = "Operation")
	@RequestMapping(method= RequestMethod.POST)
	@ResponseBody
	public Response<Operation> saveOperation(HttpServletRequest request,@RequestBody Operation model){
		try{
		   if(model==null){
			   return Response.error("参数异常！");
		   }
		   int result=-1;
		   Context context=this.getContext();
		   if(!StringHandler.isNullOrEmpty(model.getId())){
			   result=operationService.update(context,model);
		   }else{
			   result=operationService.insert(context,model);
		   }
		   return result>0?Response.success(model,"保存成功！"):Response.error("保存失败！");
		}catch(AdmuiException ae){
			logger.debug(">>> 保存操作信息异常，error:"+ae.getMessage(),ae);
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug(">>> 保存操作信息异常，error:"+e.getMessage(),e);
			return Response.error("保存操作信息异常！");
		}
	}
	
	@ApiOperation(value="删除操作", notes="删除操作",produces = "application/json")
	@ApiImplicitParam(name = "id", value = "操作ID", paramType="path", required=true, dataType = "String")
	@RequestMapping(value="{id}",method= RequestMethod.DELETE)
	@ResponseBody
	public Response<Operation> deleteOperation(HttpServletRequest request,@PathVariable("id") String id){
		try{
		   if(StringHandler.isNullOrEmpty(id)){
			   return Response.error("请先选择删除项！");
		   }
		   Context context=this.getContext();
		   List<String> idList=new ArrayList<String>();
		   idList.add(id);
		   int result=operationService.deleteByIds(context,idList);
		   return result>0?Response.success(null,"删除成功！"):Response.error("删除失败！");
		}catch(AdmuiException ae){
			logger.debug("删除操作信息异常,error:"+ae.getMessage(),ae);
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug("删除操作信息异常,error:"+e.getMessage(),e);
			return Response.error("删除操作信息异常！");
		}
	}
	
	@ApiOperation(value = "根据id查询操作信息", notes = "查询操作表中某个操作信息",produces = "application/json")
    @ApiImplicitParam(name = "id", value = "操作ID", paramType="path", required=true, dataType = "String")
	@RequestMapping(value = "{id}",method=RequestMethod.GET)
	@ResponseBody
    public Response<Operation> queryOperationById(HttpServletRequest request,@PathVariable("id") String id){
        try{
        	Context context=this.getContext();
        	Operation model=operationService.queryById(context,id);
        	return Response.success(model,"查询成功！");
        }catch(AdmuiException ae){
        	logger.debug("获取操作信息异常"+ae.getMessage(),ae);
        	return Response.error(ae.getMessage());
        }
    }
	
	@ApiOperation(value = "根据菜单和权限信息查询操作信息列表", notes = "根据菜单和权限信息查询操作信息列表",produces = "application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "permissionId", value = "权限ID",required=true, dataType = "String"),
	    @ApiImplicitParam(name = "menuId", value = "菜单ID",required=true, dataType = "String")
	})
	@RequestMapping(value = "/operations",method=RequestMethod.GET)
	@ResponseBody
    public Response<List<CheckBox>> queryOperationList(HttpServletRequest request,@RequestParam("permissionId") String permissionId,@RequestParam("menuId") String menuId){
        try{
        	Context context=this.getContext();
        	List<CheckBox> cbList=new ArrayList<CheckBox>();
        	List<Operation> operList=operationService.queryPermissionOperationListByMenuId(context,permissionId,menuId);
        	if(operList!=null){
        		CheckBox cb=null;
        		for(Operation info:operList){
        			cb=new CheckBox();
        			cb.setId(info.getId());
        			cb.setText(info.getName());
        			if(!StringHandler.isNullOrEmpty(info.getPermissionId())){
        			    cb.setSelected(true);
        			}else{
        				cb.setSelected(false);
        			}
        			cbList.add(cb);
        		}
        	}
        	return Response.success(cbList,"查询成功！");
        }catch(Exception e){
        	logger.debug("获取操作信息异常"+e.getMessage(),e);
        }
        return Response.error("获取操作信息异常！");
    }
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "根据菜单路径查询该路径的权限", notes = "根据菜单路径查询该路径的权限集合",produces = "application/json")
    @ApiImplicitParam(name = "requestUrl", value = "请求路径",required=true, dataType = "String")
	@RequestMapping(value = "/operauths",method=RequestMethod.GET)
	@ResponseBody
    public Response<List<Operation>> queryOperationAuthList(HttpServletRequest request,@RequestParam("requestUrl") String requestUrl){
        try{
        	List<Operation> operList=new ArrayList<Operation>();
        	Map<String,Map<String,Operation>> map=(Map<String,Map<String,Operation>>)request.getSession().getAttribute("allOperMap");
        	Map<String,Operation> operMap=null;
        	if(map!=null){
        		operMap=map.get(requestUrl);
        	}
        	if(operMap!=null&&!operMap.isEmpty()){
	        	for(Map.Entry<String,Operation> entry:operMap.entrySet()){
	        		operList.add(entry.getValue());
	        	}
        	}
        	return Response.success(operList,"查询成功！");
        }catch(Exception e){
        	logger.debug("获取操作权限信息异常"+e.getMessage(),e);
        }
        return Response.error("获取操作权限信息异常！");
    }
}