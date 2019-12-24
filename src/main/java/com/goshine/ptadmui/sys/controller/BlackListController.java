package com.goshine.ptadmui.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.BlackList;
import com.goshine.ptadmui.sys.service.BlackListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 * 黑名单Controller
 * @author goshine
 */


@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping("/blacklist")
@Api("黑名单Controller")
public class BlackListController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BlackListService blackListService;

	@ApiOperation(value="新增或更新黑名单", notes="保存黑名单，新增时ID为空",produces = "application/json")
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Response<BlackList> saveBlackList(HttpServletRequest request,@RequestBody BlackList model){
    	try{
	    	Context context=this.getContext();
            if(model==null){
            	return Response.error("参数异常！");
            }
            int result=0;
            if(StringHandler.isNullOrEmpty(model.getId())){
            	result=blackListService.insert(context,model);
            }else{
            	result=blackListService.update(context,model);
            }
            return result>0?Response.success(model,"保存成功！"):Response.error("保存失败！");
    	}catch(AdmuiException e){
    		logger.debug("saveBlackList error:"+e.getMessage());
    		return Response.error(e.getMessage());
    	}
    }

	@ApiOperation(value="删除黑名单", notes="删除黑名单，id为黑名单ID",produces = "application/json")
	@ApiImplicitParam(name = "id", value = "黑名单ID", paramType="path", required=true, dataType = "String")
    @RequestMapping(value = "{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public Response<String> deleteBlackList(HttpServletRequest request,@PathVariable("id") String id){
    	try{
    		if(StringHandler.isNullOrEmpty(id)){
    			return Response.error("请先选择删除项！");
    		}
	    	Context context=this.getContext();
	    	List<String> idList=new ArrayList<String>();
	    	idList.add(id);
	        int result=blackListService.deleteByIds(context,idList);
	        return result>0?Response.success(null,"删除成功！"):Response.error("删除失败！");
    	}catch(AdmuiException e){
    		return Response.error(e.getMessage());
    	}
    }

	@ApiOperation(value = "根据条件查询黑名单信息", notes = "查询黑名单的信息",produces = "application/json")
	@RequestMapping(value="/query",method=RequestMethod.GET)
    @ResponseBody
    public Response<List<BlackList>> queryBlackListById(HttpServletRequest request,BlackList blackList){
         try{
             if(StringHandler.isNullOrEmpty(blackList)){
            	 return Response.error("参数异常！");
             }
			 List<BlackList> list=blackListService.queryListByCond(this.getContext(),blackList);
        	 return list!=null?Response.success(list):Response.error("未找到对应记录！");
         }catch(Exception e){
        	 return Response.error(e.getMessage());
         }
    }
    
	@ApiOperation(value = "查询所有黑名单信息列表", notes = "查询所有黑名单信息列表",produces = "application/json")
	@RequestMapping(value="/blacklists",method=RequestMethod.GET)
    @ResponseBody
    public Response<List<BlackList>> queryAllBlackList(HttpServletRequest request){
         try{
        	Context context=this.getContext();
        	List<BlackList> list=blackListService.queryListByCond(context,null);
        	return Response.success(list);
		}catch(Exception e){
			logger.debug("BlackListController queryOrganzationListByPage:分页查询黑名单信息异常！"+e.getMessage());
		}
        return Response.success(new ArrayList<BlackList>());
    }
}