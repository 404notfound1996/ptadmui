package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.BlackList;
import java.util.List;
/**
 * 黑名单Mapper
 * @author goshine
 */
public interface BlackListMapper extends BaseMapper<BlackList>{
    /**
     * 根据ID查询黑名单信息
     * @param id
     * @return
     */
	BlackList queryById(String id);
    /**
     * 根据条件查询黑名单信息列表
     * @param page
     * @return
     */
    List<BlackList> queryListByCond(BlackList model);
    /**
     * 根据条件分页查询黑名单信息
     * @param page
     * @return
     */
	List<BlackList> queryListByPage(Page<BlackList> page);
}