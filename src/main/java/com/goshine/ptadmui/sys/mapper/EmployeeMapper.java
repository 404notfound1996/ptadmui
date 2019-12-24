package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Employee;
import java.util.List;

/**
 *  员工Mapper
 * @author goshine
 */
public interface EmployeeMapper extends BaseMapper<Employee>{
	/**
	 * 分页查询员工信息列表
	 * @param page
	 * @return
	 */
	List<Employee> queryListByPage(Page<Employee> page);
}