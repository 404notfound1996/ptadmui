package com.goshine.ptadmui.sys.service;

import java.util.List;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.model.KeyValue;
import com.goshine.ptadmui.sys.entity.Log;
/**
 * 日志业务接口
 * @author goshine
 */
public interface LogService extends BaseService<Log>{
    /**
     * 分页查询日志列表
     * @param page
     * @return
     * @throws AdmuiException 
     */
	public Page<Log> queryListByPage(Context context,Page<Log> page) throws AdmuiException;
	/**
	 * 根据登录用户获取当前日志条数
	 * @param context
	 * @return
	 * @throws AdmuiException
	 */
	public long queryCountByUserId(Context context) throws AdmuiException;
	/**
	 * 查询所有日志类型
	 * @return
	 */
	public List<String> queryLogTypeList();
	/**
	 * 查询所有有日志记录的用户信息
	 * @return
	 */
	public List<KeyValue> queryUserList();
}
