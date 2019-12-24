package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.LogConfig;
import java.util.List;

public interface LogConfigMapper  extends BaseMapper<LogConfig>{
    /**
     * 根据ID查询日志配置信息
     * @param id
     * @return
     */
	LogConfig queryById(String id);
    /**
     * 根据条件查询日志配置信息列表
     * @param page
     * @return
     */
    List<LogConfig> queryListByCond(LogConfig model);
    /**
     * 根据条件分页查询日志配置信息
     * @param page
     * @return
     */
	List<LogConfig> queryListByPage(Page<LogConfig> page);
}