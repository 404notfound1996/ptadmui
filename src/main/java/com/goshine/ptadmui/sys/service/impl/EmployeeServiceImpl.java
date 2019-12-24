package com.goshine.ptadmui.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.base.BaseServiceImpl;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Employee;
import com.goshine.ptadmui.sys.mapper.EmployeeMapper;
import com.goshine.ptadmui.sys.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 员工示例SERVICE实现
 * @author goshine
 */
@Service("employeeService")
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService{
    @Autowired
    private EmployeeMapper employeeMapper;
    
	@Override
	protected BaseMapper<Employee> getMapper() {
		return employeeMapper;
	}
    
	/**
	 * 新增员工
	 * @param model
	 * @return
	 */
    @Override
    public int insert(Context context,Employee model) throws AdmuiException{
    	if(model==null){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setEmployeeId(Long.parseLong(IDCreater.genetateKey()));
        	return employeeMapper.insert(model);
        }
        return -1;
    }
    /**
     * 更新员工
     * @param model
     * @return
     */
    @Override
    public int update(Context context,Employee model) throws AdmuiException{
    	if(model==null||StringHandler.isNullOrEmpty(model.getEmployeeId())){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	return employeeMapper.update(model);
        }
        return -1;
    }
    /**
     * 根据ID批量删除员工
     * @param list
     * @return
     */
    @Override
    public int deleteByIds(Context context,List<String> idList) throws AdmuiException{
    	if(idList==null||idList.isEmpty()){
    		throw new AdmuiException("请先选择删除项！");
    	}
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("ids",idList);
    	return employeeMapper.deleteByParams(params);
    }
    
    /**
     * 分页查询员工信息列表
     * @param page
     * @return
     */
    @Override
    public Page<Employee> queryListByPage(Context context,Page<Employee> page){
    	PageHelper.startPage(page.getPageIndex(),page.getPageSize()," demo_employee."+(StringHandler.isNullOrEmpty(page.getSortField())?"employee_id":page.getSortField())+" "+(StringHandler.isNullOrEmpty(page.getSortType())?" ASC":page.getSortType()));
		if(page.getData()==null){
			page.setData(new HashMap<String,Object>());
		}
    	List<Employee> pageList=employeeMapper.queryListByPage(page);
		PageInfo<Employee> pageInfo = new PageInfo<Employee>(pageList);
		return page.generatePage(page,pageInfo);
    }
    
    /**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(Employee model) throws AdmuiException{
    	//合法性校验
    	return true;
    }
}
