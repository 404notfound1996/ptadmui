package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.sys.entity.RoleGroup;
import com.goshine.ptadmui.sys.vo.RoleGroupVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
/**
 * 角色组Mapper
 * @author goshine
 */
public interface RoleGroupMapper extends BaseMapper<RoleGroup>{
    /**
     * 根据条件查询角色组信息列表
     * @param page
     * @return
     */
    List<RoleGroupVo> queryAll(@Param("name") String name);
}