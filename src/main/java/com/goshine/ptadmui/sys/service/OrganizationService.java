package com.goshine.ptadmui.sys.service;

import java.util.List;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Organization;
import com.goshine.ptadmui.sys.vo.UserVo;

/**
 * 组织机构Service
 * @author goshine
 */
public interface OrganizationService extends BaseService<Organization>{
    /**
     * 根据ID查询组织机构信息
     * @param id
     * @return
     */
    public Organization queryById(Context context,String id) throws AdmuiException;
    /**
     * 根据条件查询组织机构信息列表
     * @param model
     * @return
     */
    public List<Organization> queryListByCond(Context context,Organization model);
    /**
     * 分页查询组织机构列表
     * @param page
     * @return
     * @throws AdmuiException 
     */
	public Page<Organization> queryListByPage(Context context,Page<Organization> page) throws AdmuiException;
    public Page<UserVo> queryListByOrgID(Context context, Page<UserVo> page);

}
