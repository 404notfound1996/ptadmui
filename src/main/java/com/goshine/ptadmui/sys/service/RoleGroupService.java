package com.goshine.ptadmui.sys.service;

import java.util.List;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.sys.entity.RoleGroup;
import com.goshine.ptadmui.sys.vo.RoleGroupVo;
/**
 * 角色组Service接口层
 * @author goshine
 */
public interface RoleGroupService extends BaseService<RoleGroup>{
    /**
     * 条件查询
     * @param record
     * @return
     */
   public List<RoleGroupVo> queryAll(Context context,String name) throws AdmuiException;
}
