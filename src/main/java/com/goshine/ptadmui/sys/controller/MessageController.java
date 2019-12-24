package com.goshine.ptadmui.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.goshine.ptadmui.sys.entity.Message;
import com.goshine.ptadmui.sys.service.MessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 * 系统消息Controller
 * @author goshine
 */
@Controller
@RequestMapping("/message")
@Api("系统消息Controller")
public class MessageController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MessageService messageService;

	@ApiOperation(value="更新消息", notes="阅读之后更新消息",produces = "application/json")
    @RequestMapping(value = "{id}",method=RequestMethod.POST)
    @ResponseBody
    public Response<Message> updateMessage(HttpServletRequest request,@PathVariable("id") String id){
    	try{
	    	Context context=this.getContext();
            if(StringHandler.isNullOrEmpty(id)){
            	return Response.error("参数异常！");
            }
            Message model=new Message();
            model.setId(id);
            model.setReadFlag(Enums.YesOrNo.Yes.getIndex());
            int result=messageService.update(context,model);
            return result>0?Response.success(model,"更新成功！"):Response.error("更新失败！");
    	}catch(AdmuiException e){
    		return Response.error(e.getMessage());
    	}
    }

	@ApiOperation(value="删除消息", notes="删除消息，id为消息ID",produces = "application/json")
	@ApiImplicitParam(name = "id", value = "消息ID", paramType="path", required=true, dataType = "String")
    @RequestMapping(value = "{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public Response<String> deleteMessage(HttpServletRequest request,@PathVariable("id") String id){
    	try{
    		if(StringHandler.isNullOrEmpty(id)){
    			return Response.error("请先选择删除项！");
    		}
	    	Context context=this.getContext();
	    	List<String> idList=new ArrayList<String>();
	    	idList.add(id);
	        int result=messageService.deleteByIds(context,idList);
	        return result>0?Response.success(null,"删除成功！"):Response.error("删除失败！");
    	}catch(AdmuiException e){
    		return Response.error(e.getMessage());
    	}
    }
    
	@ApiOperation(value = "分页查询消息列表", notes = "分页查询消息信息列表",produces = "application/json")
	@RequestMapping(value="/messages",method=RequestMethod.GET)
    @ResponseBody
    public Response<Page<Message>> queryMessageListByPage(Page<Message> page){
         try{
        	Context context=this.getContext();
			page=messageService.queryListByPage(context,page);
			Response.success(page);
		}catch (AdmuiException e) {
			logger.debug("MessageController queryMessageListByPage:分页查询组织机构信息异常！"+e.getMessage(),e);
        return Response.error("获取失败");
		}
         return Response.success(page);
    }
}