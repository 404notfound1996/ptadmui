package com.goshine.ptadmui.sys.service;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.sys.entity.DisplaySetting;
/**
 * 显示设置Service
 * @author goshine
 */
public interface DisplaySettingService extends BaseService<DisplaySetting>{
	/**
     * 保存显示设置
     * @param record
     * @return
     */
    public int save(Context context,DisplaySetting model) throws AdmuiException;
    /**
     * 查询当前登录用户的显示设置信息
     * @param model
     * @return
     */
    public DisplaySetting queryDisplaySettingByCurrentUser(Context context);
	/**
     * <p>查询默认显示设置</p>
     * @return  默认显示设置
     */
	DisplaySetting queryDefault();
	/**
     * <p>查询全局显示设置</p>
     * @return  全局显示设置
     */
	DisplaySetting queryGlobal();
}
