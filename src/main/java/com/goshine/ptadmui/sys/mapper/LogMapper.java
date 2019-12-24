package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.model.KeyValue;
import com.goshine.ptadmui.sys.entity.Log;

import java.util.List;
/**
 * 日志Mapper
 * @author goshine
 */
public interface LogMapper  extends BaseMapper<Log>{
    /**
     * 根据条件分页查询日志信息
     * @param page
     * @return
     */
	List<Log> queryListByPage(Page<Log> page);
	/**
	 * 查询当前登录用户的日志总数
	 * @param userId
	 * @return
	 */
	long queryCountByUserId(String userId);
	/**
	 * 查询所有日志类型
	 * @return
	 */
	List<String> queryLogTypeList();
	/**
	 * 查询所有用户列表
	 * @return
	 */
	List<KeyValue> queryUserList();
}