package com.goshine.ptadmui.sys.service;

import java.util.List;
import java.util.Map;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.LogConfig;
/**
 * 日志配置业务接口
 * @author goshine
 */
public interface LogConfigService extends BaseService<LogConfig>{
    /**
     * 根据ID查询日志配置信息
     * @param id
     * @return
     */
    public LogConfig queryById(Context context,String id) throws AdmuiException;
    /**
     * 根据条件查询日志配置信息列表
     * @param model
     * @return
     */
    public List<LogConfig> queryListByCond(Context context,LogConfig model);
    /**
     * 根据条件查询日志配置信息MAP
     * @param model
     * @return
     */
    public Map<String,String> queryConfigMap(Context context,LogConfig model);
    /**
     * 分页查询日志配置列表
     * @param page
     * @return
     * @throws AdmuiException 
     */
	public Page<LogConfig> queryListByPage(Context context,Page<LogConfig> page) throws AdmuiException;
}
