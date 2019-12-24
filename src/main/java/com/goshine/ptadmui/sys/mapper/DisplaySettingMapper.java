package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.sys.entity.DisplaySetting;
import java.util.List;
/**
 * 系统显示设置Mapper
 * @author goshine
 */
public interface DisplaySettingMapper extends BaseMapper<DisplaySetting>{
	/**
	 * 查询默认显示设置
	 * @return
	 */
	DisplaySetting queryDefault();
	/**
	 * 查询全局显示设置
	 * @return
	 */
	DisplaySetting queryGlobal();
    /**
     * 根据条件查询系统显示设置信息
     * @param page
     * @return
     */
    List<DisplaySetting> queryListByCond(DisplaySetting model);
}