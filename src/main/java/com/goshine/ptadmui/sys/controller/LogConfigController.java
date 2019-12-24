package com.goshine.ptadmui.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.LogConfig;
import com.goshine.ptadmui.sys.service.LogConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 日志配置Controller
 * @author goshine
 */
@Controller
@RequestMapping("/logconfig")
@Api("日志配置Controller")
public class LogConfigController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private LogConfigService logConfigService;

	@ApiOperation(value="新增或更新日志配置", notes="保存日志配置，新增时ID为空",produces = "application/json")
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Response<LogConfig> saveLogConfig(HttpServletRequest request,@RequestBody LogConfig model){
    	try{
	    	Context context=this.getContext();
            if(model==null){
            	return Response.error("参数异常！");
            }
            int result=0;
            if(StringHandler.isNullOrEmpty(model.getId())){
            	result=logConfigService.insert(context,model);
            }else{
            	result=logConfigService.update(context,model);
            }
            model.setModifyUserName(context.getName());
            return result>0?Response.success(model,"保存成功！"):Response.error("保存失败！");
    	}catch(AdmuiException e){
    		logger.debug("saveLogConfig error:"+e.getMessage());
    		return Response.error(e.getMessage());
    	}
    }

	@ApiOperation(value="删除日志配置", notes="删除日志配置，id为日志配置ID",produces = "application/json")
	@ApiImplicitParam(name = "id", value = "日志配置ID", paramType="path", required=true, dataType = "String")
    @RequestMapping(value = "{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public Response<String> deleteLogConfig(HttpServletRequest request,@PathVariable("id") String id){
    	try{
    		if(StringHandler.isNullOrEmpty(id)){
    			return Response.error("请先选择删除项！");
    		}
	    	Context context=this.getContext();
	    	List<String> idList=new ArrayList<String>();
	    	idList.add(id);
	        int result=logConfigService.deleteByIds(context,idList);
	        return result>0?Response.success(null,"删除成功！"):Response.error("删除失败！");
    	}catch(AdmuiException e){
    		return Response.error(e.getMessage());
    	}
    }
    
	@ApiOperation(value = "根据id查询日志配置信息", notes = "查询日志配置表中某个机构的信息",produces = "application/json")
    @ApiImplicitParam(name = "id", value = "日志配置ID", paramType="path", required=true, dataType = "String")
	@RequestMapping(value="{id}",method=RequestMethod.GET)
    @ResponseBody
    public Response<LogConfig> queryLogConfigById(HttpServletRequest request,@PathVariable("id") String id){
         try{
             if(StringHandler.isNullOrEmpty(id)){
            	 return Response.error("参数异常！");
             }
        	 LogConfig info=logConfigService.queryById(this.getContext(),id);
        	 return info!=null?Response.success(info):Response.error("未找到对应记录！");
         }catch(AdmuiException e){
        	 return Response.error(e.getMessage());
         }
    }
    
	@ApiOperation(value = "查询所有日志配置信息列表", notes = "查询所有日志配置信息列表",produces = "application/json")
	@RequestMapping(value="/logconfigs",method=RequestMethod.GET)
    @ResponseBody
    public Response<List<LogConfig>> queryLogConfigList(HttpServletRequest request){
         try{
        	Context context=this.getContext();
			List<LogConfig> configList=logConfigService.queryListByCond(context,null);
			return Response.success(configList);
		 }catch (Exception e) {
			logger.debug("LogConfigController queryLogConfigListByPage:查询日志配置信息异常！"+e.getMessage());
		 }
         return Response.success(new ArrayList<LogConfig>());
    }
}