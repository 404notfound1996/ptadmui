package com.goshine.ptadmui.sys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.goshine.ptadmui.core.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Log;
import com.goshine.ptadmui.sys.service.LogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 日志Controller
 * @author goshine
 */
@Controller
@RequestMapping("/log")
@Api("日志Controller")
public class LogController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private LogService logService;

	@ApiOperation(value = "分页查询日志信息列表", notes = "分页查询日志信息列表",produces = "application/json")
	@RequestMapping(value="/logs",method=RequestMethod.GET)
    @ResponseBody
    public Response<Page<Log>> queryLogListByPage(HttpServletRequest request, Page<Log> page){
         try{
        	Context context=this.getContext();
        	warpPage(page);
			page=logService.queryListByPage(context,page);
		}catch (AdmuiException e) {
			logger.debug("LogController queryLogListByPage:分页查询日志信息异常！"+e.getMessage());
		}
        return Response.success(page);
    }

	private void warpPage(Page<Log> page){
		try{
			if(!StringHandler.isNullOrEmpty(page.getSortField())) {
				switch (Integer.parseInt(page.getSortField())) {
					case 0:
						page.setSortField("url");
						break;
					case 1:
						page.setSortField("type");
						break;
					case 3:
						page.setSortField("create_user");
						break;
					case 4:
						page.setSortField("operate_ip");
						break;
					case 5:
						page.setSortField("create_date");
						break;
					default:
						page.setSortField("create_date");
						break;
				}
			} else {
				page.setSortField("create_date");
			}
		}catch(Exception e){
			logger.debug("LogController wrapPage error:"+e.getMessage());
			page.setSortField("create_date");
			page.setSortType("ASC");
		}
	}
}