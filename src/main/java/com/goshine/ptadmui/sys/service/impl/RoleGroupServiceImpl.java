package com.goshine.ptadmui.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.base.BaseServiceImpl;
import com.goshine.ptadmui.core.consts.Constant;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.entity.RoleGroup;
import com.goshine.ptadmui.sys.mapper.RoleGroupMapper;
import com.goshine.ptadmui.sys.mapper.RoleMapper;
import com.goshine.ptadmui.sys.service.RoleGroupService;
import com.goshine.ptadmui.sys.vo.RoleGroupVo;
/**
 * 角色组业务实现类
 * @author goshine
 */
@Service("roleGroupService")
public class RoleGroupServiceImpl extends BaseServiceImpl<RoleGroup> implements RoleGroupService{
	@Autowired
	private RoleGroupMapper roleGroupMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Override
	protected BaseMapper<RoleGroup> getMapper() {
		return roleGroupMapper;
	}
	
	/**
	 * 新增角色组
	 * @param model
	 * @return
	 */
    @Override
    public int insert(Context context,RoleGroup model) throws AdmuiException{
    	if(model==null){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setId(IDCreater.genetateKey());
        	model.setCreateUser(context.getUserId());
        	model.setCreateDate(context.getCurrentDateTime());
        	model.setModifyUser(context.getUserId());
        	model.setModifyDate(context.getCurrentDateTime());
        	return roleGroupMapper.insert(model);
        }
        return -1;
    }
    /**
     * 更新角色
     * @param model
     * @return
     */
    @Override
    public int update(Context context,RoleGroup model) throws AdmuiException{
    	if(model==null||StringHandler.isNullOrEmpty(model.getId())){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setModifyUser(context.getUserId());
        	model.setModifyDate(context.getCurrentDateTime());
        	return roleGroupMapper.update(model);
        }
        return -1;
    }
    /**
     * 根据ID批量删除角色
     * @param list
     * @return
     */
    @Override
    public int deleteByIds(Context context,List<String> idList) throws AdmuiException{
    	if(idList==null||idList.isEmpty()){
    		throw new AdmuiException("请先选择删除项！");
    	}
    	for(String id:idList){
    		if(Constant.DEFAULT_ROLEGROUP_CODE.equals(id)||Constant.STATION_ROLEGROUP_CODE.equals(id)||Constant.REGION_ROLEGROUP_CODE.equals(id)){
    			throw new AdmuiException("内置角色组不允许删除！");
    		}
    		Role queryRole=new Role();
    		queryRole.setRoleGroupId(id);
    		List<Role> roleList=roleMapper.queryListByCond(queryRole);
    		if(roleList!=null&&roleList.size()>0){
    			throw new AdmuiException("该角色组下尚存在角色，不允许删除！");
    		}
    	}
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("ids",idList);
    	params.put("modifyUser",context.getUserId());
    	params.put("modifyDate",context.getCurrentDateTime());
    	return roleGroupMapper.deleteByParams(params);
    }
 
    /**
     * 根据条件查询组织机构信息列表
     * @param model
     * @return
     */
    @Override
    public List<RoleGroupVo> queryAll(Context context,String name){
    	return roleGroupMapper.queryAll(name);
    }
    
    /**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(RoleGroup model) throws AdmuiException{
    	//合法性校验
    	if(StringHandler.isNullOrEmpty(model.getGroupName())){
    		throw new AdmuiException("角色组名称不能为空！");
    	}
    	if(StringHandler.empty(model.getGroupName()).length()>40){
    		throw new AdmuiException("角色组名称长度不能超过40字符！");
    	}
    	return true;
    }
}
