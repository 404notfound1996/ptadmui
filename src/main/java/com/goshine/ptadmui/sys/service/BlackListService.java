package com.goshine.ptadmui.sys.service;

import java.util.List;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.BlackList;
/**
 * 黑名单Service
 * @author goshine
 */
public interface BlackListService extends BaseService<BlackList>{
    /**
     * 根据ID查询黑名单信息
     * @param id
     * @return
     */
    public BlackList queryById(Context context,String id) throws AdmuiException;
    /**
     * 根据ip查询黑名单信息
     * @param id
     * @return
     */
    public BlackList queryByIp(String ip) throws AdmuiException;
    /**
     * 根据条件查询黑名单信息列表
     * @param model
     * @return
     */
    public List<BlackList> queryListByCond(Context context,BlackList model);
    /**
     * 分页查询黑名单列表
     * @param page
     * @return
     * @throws AdmuiException 
     */
	public Page<BlackList> queryListByPage(Context context,Page<BlackList> page) throws AdmuiException;
}
