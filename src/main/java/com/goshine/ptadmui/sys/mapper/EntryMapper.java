package com.goshine.ptadmui.sys.mapper;

import java.util.List;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Entry;

/**
 *  条目Mapper
 * @author goshine
 */
public interface EntryMapper extends BaseMapper<Entry>{
	/**
	 * 分页查询条目信息列表
	 * @param page
	 * @return
	 */
	List<Entry> queryListByPage(Page<Entry> page);
}