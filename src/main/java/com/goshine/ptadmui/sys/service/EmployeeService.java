package com.goshine.ptadmui.sys.service;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Employee;

/**
 * 示例员工Service
 * @author goshine
 */
public interface EmployeeService extends BaseService<Employee>{
    /**
     * 分页查询示例员工列表
     * @param page
     * @return
     * @throws AdmuiException 
     */
	public Page<Employee> queryListByPage(Context context,Page<Employee> page);
}
