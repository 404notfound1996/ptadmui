package com.goshine.ptadmui.sys.service;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Entry;

/**
 * 示例条目Service
 * @author goshine
 */
public interface EntryService extends BaseService<Entry>{
    /**
     * 分页查询示例条目列表
     * @param page
     * @return
     * @throws AdmuiException 
     */
	public Page<Entry> queryListByPage(Context context,Page<Entry> page);
}
