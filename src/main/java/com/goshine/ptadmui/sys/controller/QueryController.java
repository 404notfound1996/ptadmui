package com.goshine.ptadmui.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.utils.IPUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
/**
 * 查询Controller
 * @author goshine
 */
@Controller
@RequestMapping("/info")
@Api("信息查询Controller")
public class QueryController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ApiOperation(value="查询IP信息", notes="查看IP信息时用于查询IP信息",produces = "application/json")
	@ApiImplicitParam(name = "ip", value = "IP地址",required=true,dataType = "String")
	@RequestMapping(value="/ip", method = RequestMethod.GET)
	@ResponseBody
	public Response<String> queryIp(String ip){
		try {
			String result = IPUtils.query(ip);
			return Response.success(result,result);
		} catch (Exception e) {
			logger.error("查询黑名单归属失败！", e);
		}
		return Response.error("查询黑名单归属失败！");
	}
}
