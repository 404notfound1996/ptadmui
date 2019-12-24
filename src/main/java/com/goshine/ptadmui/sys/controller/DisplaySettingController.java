package com.goshine.ptadmui.sys.controller;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.DisplaySetting;
import com.goshine.ptadmui.sys.service.DisplaySettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 * 显示设置Controller
 * @author goshine
 */
@Controller
@RequestMapping("/display")
@Api("显示设置Controller")
public class DisplaySettingController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DisplaySettingService displaySettingService;

	@ApiOperation(value="新增或更新显示设置", notes="保存显示设置，新增时ID为空",produces = "application/json")
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Response<DisplaySetting> saveDisplay(HttpServletRequest request,@RequestBody DisplaySetting model){
    	try{
	    	Context context=this.getContext();
            if(model==null){
            	return Response.error("参数异常！");
            }

            if(Strings.isNullOrEmpty(model.getUserId())){
            	model.setUserId(context.getUserId());
			}

            int result=displaySettingService.save(context,model);
            return result>0?Response.success(model,"保存成功！"):Response.error("保存失败！");
    	}catch(AdmuiException e){
    		return Response.error(e.getMessage());
    	}
    }
    
	@ApiOperation(value = "查询当前登录用户的显示设置信息", notes = "查询当前登录用户的显示设置",produces = "application/json")
	@RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public Response<DisplaySetting> queryDisplaySettingByCurrentUser(HttpServletRequest request){
         try{
        	 Context context=this.getContext();
        	 DisplaySetting info=displaySettingService.queryDisplaySettingByCurrentUser(context);
        	 return info!=null?Response.success(info):Response.error("未找到对应记录！");
         }catch(Exception e){
        	 logger.debug("DisplaySettingController queryDisplaySettingByCurrentUser error:"+e.getMessage(),e);
        	 return Response.error("查询显示设置信息失败！");
         }
    }
	
	@ApiOperation(value="恢复默认显示设置", notes="恢复默认显示设置，当id为空，则恢复系统级显示设置")
    @RequestMapping(value="/default",method=RequestMethod.POST)
    @ResponseBody
    public Response<DisplaySetting> resetUserDisplay(HttpServletRequest request,String userId){
    	try{
    		if(!StringHandler.isNullOrEmpty(userId)){//账号信息不为空，则为恢复个人显示设置，则直接删除该用户对应信息
    			displaySettingService.deleteByIds(this.getContext(),null);//删除个人显示设置
    		}else{//假如用户信息为空，则删除的是系统级别显示设置，则复制默认显示设置覆盖全局的
    			DisplaySetting setting=displaySettingService.queryDefault();
    			DisplaySetting globalSetting=displaySettingService.queryGlobal();
    			setting.setId(globalSetting.getId());
    			displaySettingService.update(this.getContext(),setting);
    		}
    		//不管是系统还是个人的都查全局的返回
            DisplaySetting setting=displaySettingService.queryGlobal();
            return Response.success(setting,"恢复默认成功！");
    	}catch(AdmuiException e){
    		logger.debug("DisplaySettingController deleteUserDisplay error:"+e.getMessage(),e);
    		return Response.error("恢复默认失败！");
    	}
    }
}