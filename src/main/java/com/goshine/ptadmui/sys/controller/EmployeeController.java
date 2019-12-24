package com.goshine.ptadmui.sys.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.goshine.ptadmui.core.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Employee;
import com.goshine.ptadmui.sys.service.EmployeeService;
import com.goshine.ptadmui.sys.vo.EmployeeVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

@Api("示例员工分页查询Controller")
@Controller
@RequestMapping("/private/api/employee")
public class EmployeeController extends BaseController{
	@Autowired
	private EmployeeService employeeService;
	/**
	 * @param request
	 * @param employeeVo
	 * @return
	 */
	@ApiOperation(value = "jsonp方式分页查询员工信息列表", notes = "jsonp方式分页查询员工信息列表",produces = "application/json")
	@RequestMapping(value ="/all", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Response<String> all(HttpServletRequest request,EmployeeVo employeeVo){
		warpEmployeeVo(employeeVo);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Page<Employee> page=new Page<Employee>();
		page.setPageIndex(employeeVo.getStart());
		page.setPageSize(employeeVo.getLength());
		Map<String,Object> params=new HashMap<String,Object>();
		if(!StringHandler.isNullOrEmpty(getRequest().getParameter("search[value]"))) {
			params.put("name",getRequest().getParameter("search[value]"));
		}
		page.setData(params);
		page=employeeService.queryListByPage(this.getContext(),page);

		List<List<String>> dataList = new ArrayList<List<String>>();
		List<String> itemList = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

		for (Employee employee : page.getPageList()) {
			itemList = new ArrayList<String>();
			itemList.add(employee.getName());
			itemList.add(employee.getTitle());
			itemList.add(employee.getBase());
			itemList.add(employee.getAge() + "");
			itemList.add(formatter.format(employee.getHireDate()));
			itemList.add("&yen;" + employee.getSalary());
			dataList.add(itemList);
		}

		resultMap.put("recordsTotal", page.getTotalPage());
		resultMap.put("recordsFiltered", page.getTotal());
		resultMap.put("data", dataList);

		ObjectMapper objectMapper = new ObjectMapper();
		String str = null;
		try {
			str = objectMapper.writeValueAsString(resultMap);
		} catch (JsonProcessingException e) {
			Response.error(e.getMessage());
		}

		// jsonp
		if(!StringHandler.isNullOrEmpty(getRequest().getParameter("callback"))){
			str=getRequest().getParameter("callback") + "(" + str + ");";
		}
		return Response.success(str);
	}
	/**
	 * @param employeeVo
	 * @return
	 */
	@ApiOperation(value = "POST方式分页查询员工信息列表", notes = "POST方式分页查询员工信息列表",produces = "application/json")
	@RequestMapping(value = "/allpost", method = RequestMethod.POST)
	@ResponseBody
	public Response<Map<String, Object>> allPost(HttpServletRequest request,EmployeeVo employeeVo) {

		warpEmployeeVo(employeeVo);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Page<Employee> page=new Page<Employee>();
		page.setPageIndex(employeeVo.getStart());
		page.setPageSize(employeeVo.getLength());
		Map<String,Object> params=new HashMap<String,Object>();
		if(!StringHandler.isNullOrEmpty(getRequest().getParameter("search[value]"))) {
			params.put("name",getRequest().getParameter("search[value]"));
		}
		page.setData(params);
		page=employeeService.queryListByPage(this.getContext(),page);

		jsonMap.put("recordsTotal", page.getTotalPage());
		jsonMap.put("recordsFiltered", page.getTotal());
		jsonMap.put("data",page.getPageList());

		return Response.success(jsonMap);
	}
	/**
	 * @param employeeVo
	 * @return
	 */
	@RequestMapping(value = "/allget", method = RequestMethod.GET)
	@ResponseBody
	public Response<Map<String, Object>> allGet(HttpServletRequest request,EmployeeVo employeeVo) {

		warpEmployeeVo(employeeVo);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Page<Employee> page=new Page<Employee>();
		page.setPageIndex(employeeVo.getStart());
		page.setPageSize(employeeVo.getLength());
		Map<String,Object> params=new HashMap<String,Object>();
		if(!StringHandler.isNullOrEmpty(getRequest().getParameter("search[value]"))) {
			params.put("name",getRequest().getParameter("search[value]"));
		}
		page.setData(params);
		page=employeeService.queryListByPage(this.getContext(),page);

		jsonMap.put("draw", 0);
		jsonMap.put("recordsTotal", page.getTotalPage());
		jsonMap.put("recordsFiltered", page.getTotal());
		jsonMap.put("data", page.getPageList());

		return Response.success(jsonMap);
	}
	/**
	 * @param employeeVo
	 * @return
	 */
	@ApiOperation(value = "GET方式分页查询员工信息列表", notes = "分页查询员工信息列表",produces = "application/json")
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public Response<Map<String, Object>> query(HttpServletRequest request,EmployeeVo employeeVo) {

		warpEmployeeVo(employeeVo);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Page<Employee> page=new Page<Employee>();
		page.setPageIndex(employeeVo.getStart());
		page.setPageSize(employeeVo.getLength());
		Map<String,Object> params=new HashMap<String,Object>();
		if(!StringHandler.isNullOrEmpty(getRequest().getParameter("search[value]"))) {
			params.put("name",getRequest().getParameter("search[value]"));
		}
		page.setData(params);
		page=employeeService.queryListByPage(this.getContext(),page);

		jsonMap.put("recordsTotal", page.getTotalPage());
		jsonMap.put("recordsFiltered", page.getTotal());
		jsonMap.put("data", page.getPageList());
		return Response.success(jsonMap);
	}

	private void warpEmployeeVo(EmployeeVo employeeVo){
		String orderColumnNum = getRequest().getParameter("order[0][column]");
		if(!StringHandler.isNullOrEmpty(orderColumnNum)) {
			switch (Integer.parseInt(orderColumnNum)) {
				case 0:
					employeeVo.setOrderColumn("name");
					break;
				case 1:
					employeeVo.setOrderColumn("title");
					break;
				case 2:
					employeeVo.setOrderColumn("base");
					break;
				case 3:
					employeeVo.setOrderColumn("age");
					break;
				case 4:
					employeeVo.setOrderColumn("hire_date");
					break;
				case 5:
					employeeVo.setOrderColumn("salary");
					break;
				default:
					employeeVo.setOrderColumn("name");
					break;
			}
		} else {
			employeeVo.setOrderColumn("name");
		}
		if (!StringHandler.isNullOrEmpty(getRequest().getParameter("order[0][dir]"))) {
			employeeVo.setOrderDir(getRequest().getParameter("order[0][dir]"));
		}
	}
}