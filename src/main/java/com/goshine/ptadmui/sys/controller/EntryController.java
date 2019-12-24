package com.goshine.ptadmui.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Entry;
import com.goshine.ptadmui.sys.service.EntryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 条目Controller
 * @author goshine
 */
@Controller
@RequestMapping("/entry")
@Api("示例条目分页查询Controller")
public class EntryController extends BaseController{
	@Autowired
	private EntryService entryService;
	
	@ApiOperation(value = "GET方式分页查询条目信息列表", notes = "分页查询条目信息列表",produces = "application/json")
	@RequestMapping(value="/entrys",method=RequestMethod.GET)
    @ResponseBody
    public Page<Entry> getEntryListByPage(HttpServletRequest request,Page<Entry> page){
         try{
        	warpPage(page);
        	Context context=this.getContext();
			page=entryService.queryListByPage(context,page);
		}catch (Exception e) {
			logger.debug("EntryController getEntryListByPage:分页查询组织机构信息异常！"+e.getMessage());
		}
        return page;
    }
	
	@ApiOperation(value = "POST方式分页查询条目信息列表", notes = "分页查询条目信息列表",produces = "application/json")
	@RequestMapping(value="/entrys",method=RequestMethod.POST)
    @ResponseBody
    public Page<Entry> queryEntryListByPage(HttpServletRequest request,@RequestBody Page<Entry> page){
         try{
        	warpPage(page);
        	Context context=this.getContext();
			page=entryService.queryListByPage(context,page);
		}catch (Exception e) {
			logger.debug("EntryController queryEntryListByPage:分页查询组织机构信息异常！"+e.getMessage());
		}
        return page;
    }
	
	@ApiOperation(value="分页查询条目信息", notes="分页查询条目信息",produces = "application/json")
	@RequestMapping(value="/entrypages",method=RequestMethod.GET)
	@ResponseBody
    public Page<Entry> getEntryListByPage(HttpServletRequest request, @RequestParam(value = "offset", required=false) int offset,
            @RequestParam(value = "limit", required=false) int limit,@RequestParam(value = "sort", required=false) String sort,
            @RequestParam(value = "order", required=false) String order,@RequestParam(value = "search", required=false) String search){
		Page<Entry> page=new Page<Entry>();
		try{
        	if(offset==0){
        		offset=1;
        	}
        	page.setPageIndex(offset);
        	page.setPageSize(limit);
        	page.setSortField(sort);
        	page.setSortType(StringHandler.isNullOrEmpty(order)?" ASC":order);
        	Map<String,Object> params=new HashMap<String,Object>();
        	params.put("entryName",search);
        	page.setData(params);
        	Context context=this.getContext();
        	warpColumnPage(page);
			page=entryService.queryListByPage(context,page);
		}catch (Exception e) {
			logger.debug("EntryController queryEntryListByPage:分页查询组织机构信息异常！"+e.getMessage());
		}
        return page;
	}
	
	private void warpPage(Page<Entry> page){
		try{
			if(!StringHandler.isNullOrEmpty(page.getSortField())) {
				switch (Integer.parseInt(page.getSortField())) {
					case 0:
						page.setSortField("id");
						break;
					case 1:
						page.setSortField("entry_name");
						break;
					case 2:
						page.setSortField("entry_price");
						break;
					default:
						page.setSortField("id");
						break;
				}
			} else {
				page.setSortField("id");
			}
		}catch(Exception e){
			logger.debug("EntryController wrapPage error:"+e.getMessage());
			page.setSortField("id");
			page.setSortType("ASC");
		}
	}
	
	private void warpColumnPage(Page<Entry> page){
		if(!StringHandler.isNullOrEmpty(page.getSortField())){
			switch(page.getSortField()){
				case "id":
					page.setSortField("id");
					break;
				case "name":
					page.setSortField("entry_name");
					break;
				case "price":
					page.setSortField("entry_price");
					break;
				default:
					page.setSortField("id");
					break;
			}
		} else {
			page.setSortField("id");
		}
	}
}
