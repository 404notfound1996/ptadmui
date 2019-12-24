package com.goshine.ptadmui.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.base.BaseServiceImpl;
import com.goshine.ptadmui.core.consts.Constant;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Permission;
import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.mapper.PermissionMapper;
import com.goshine.ptadmui.sys.mapper.PermissionMenuMapper;
import com.goshine.ptadmui.sys.mapper.PermissionOperationMapper;
import com.goshine.ptadmui.sys.mapper.RoleMapper;
import com.goshine.ptadmui.sys.mapper.RolePermissionMapper;
import com.goshine.ptadmui.sys.service.PermissionService;
import com.goshine.ptadmui.sys.vo.PermissionAuthVo;
/**
 * 权限相关业务实现类
 * @author goshine
 */
@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService{
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private PermissionMenuMapper pmMapper;
	@Autowired
	private PermissionOperationMapper poMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RolePermissionMapper rpMapper;
	@Override
	protected BaseMapper<Permission> getMapper() {
		return permissionMapper;
	}

	/**
	 * 新增权限
	 * @param model
	 * @return
	 */
    @Override
    public int insert(Context context,Permission model) throws AdmuiException{
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
        	return permissionMapper.insert(model);
        }
        return -1;
    }
    /**
     * 更新权限
     * @param model
     * @return
     */
    @Override
    public int update(Context context,Permission model) throws AdmuiException{
    	if(model==null||StringHandler.isNullOrEmpty(model.getId())){
    		throw new AdmuiException("参数异常！");
    	}
		if(Constant.ADMIN__PERMISSION_CODE.equals(model.getId())){
			throw new AdmuiException("超级管理员所属权限不允许删除！");
		}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setModifyUser(context.getUserId());
        	model.setModifyDate(context.getCurrentDateTime());
        	return permissionMapper.update(model);
        }
        return -1;
    }
    /**
     * 根据ID批量删除权限
     * @param list
     * @return
     */
    @Override
    @Transactional
    public int deleteByIds(Context context,List<String> idList) throws AdmuiException{
    	if(idList==null||idList.isEmpty()){
    		throw new AdmuiException("请先选择删除项！");
    	}
    	for(String id:idList){
    		if(Constant.ADMIN__PERMISSION_CODE.equals(id)){
    			throw new AdmuiException("超级管理员所属权限不允许删除！");
    		}
    		List<Role> roleList=roleMapper.queryRoleListByPermissionId(id);
    		if(roleList!=null&&roleList.size()>0){
    			throw new AdmuiException("warning");
    		}
    	}
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("ids",idList);
    	params.put("permissionIds",idList);
    	params.put("modifyUser",context.getUserId());
    	params.put("modifyDate",context.getCurrentDateTime());
    	//删除权限角色关系
    	rpMapper.batchDeleteByParams(params);
    	//删除权限菜单关系
    	pmMapper.batchDeleteByParams(params);
    	//删除权限操作关系
    	poMapper.batchDeleteByParams(params);
    	return permissionMapper.deleteByParams(params);
    }
    
    /**
     * 根据ID查询权限信息
     * @param id
     * @return
     */
    @Override
    public Permission queryById(Context context,String id) throws AdmuiException{
    	if(StringHandler.isNullOrEmpty(id)){
    		throw new AdmuiException("参数异常！");
    	}
    	return permissionMapper.queryById(id);
    }
    
    /**
     * 根据条件查询权限信息列表
     * @param model
     * @return
     */
    @Override
    public List<Permission> queryListByCond(Context context,Permission model){
    	if(model==null){
    		model=new Permission();
    	}
    	List<Permission> permissionList=permissionMapper.queryListByCond(model);
    	if(permissionList!=null&&!permissionList.isEmpty()){
    		for(Permission permission:permissionList){
    			List<String> menuIds=pmMapper.queryLeafMenuIdListByPermissionId(permission.getId());
    			if(menuIds==null){
    				menuIds=new ArrayList<String>();
    			}
    			permission.setMenuIds(menuIds);
    		}
    		for(Permission permission:permissionList){
    			List<String> operIds=poMapper.queryOperIdListByPermissionId(permission.getId());
    			if(operIds==null){
    				operIds=new ArrayList<String>();
    			}
    			permission.setOperIds(operIds);
    		}
    	}
    	return permissionList;
    }
    
    /**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(Permission model) throws AdmuiException{
    	if(StringHandler.isNullOrEmpty(model.getName())){
    		throw new AdmuiException("权限名称不能为空！");
    	}
    	if(StringHandler.empty(model.getName()).length()>60){
    		throw new AdmuiException("权限名称长度不能超过60字符！");
    	}
    	return true;
    }
    /**
     * 根据角色ID集合查询角色拥有的权限
     */
	@Override
	public List<Permission> queryPermissionListByRoleIds(List<String> roleIds) {
		if(roleIds==null||roleIds.size()<=0){
			return new ArrayList<Permission>();
		}
		return permissionMapper.queryPermissionListByRoleIds(roleIds);
	}
	/**
     * 根据角色ID左关联查询权限列表信息 
     * @param context
     * @param roleId 角色ID
     * @param parentId 父类ID
     * @return
     */
	@Override
	public List<Permission> queryPermissionListByRoleId(Context context,String roleId){
		return permissionMapper.queryPermissionListByRoleId(roleId);
	}
    /**
     * 权限分配菜单和操作
     */
	@Override
	@Transactional
	public int assign(Context context, PermissionAuthVo model) throws AdmuiException{
		 if(model==null||StringHandler.isNullOrEmpty(model.getPermissionId())){
			 throw new AdmuiException("请选择要分配的权限！");
		 }
		 //超级管理员所属的权限不允许修改和删除
		 if(Constant.ADMIN__PERMISSION_CODE.equals(model.getPermissionId())){
			 //查询原权限的菜单集合
			 List<String> menuIds=pmMapper.queryMenuIdListByPermissionId(model.getPermissionId());
			 //查询原权限的操作集合
			 List<String> operIds=poMapper.queryOperIdListByPermissionId(model.getPermissionId());
			 //比对原权限的菜单是否在当前要保存的菜单里边
			 for(String menuId:menuIds){
				 if(!model.getMenuIds().contains(menuId)){
					 throw new AdmuiException("超级管理员原有菜单权限不允许变更！");
				 }
			 }
			//比对原权限的操作是否在当前要保存的操作里边
			 for(String operId:operIds){
				 if(!model.getOperationIds().contains(operId)){
					 throw new AdmuiException("超级管理员原有操作权限不允许变更！");
				 }
			 }
		 }
		 int result=0;
		 //保存之前先删除原权限菜单数据
		 Map<String,Object> params=new HashMap<String,Object>();
		 params.put("permissionId",model.getPermissionId());
		 result+=pmMapper.deleteByParams(params);
		 result+=poMapper.deleteByParams(params);
		 if(!model.getMenuIds().isEmpty()){
		    result=pmMapper.batchInsertWithMenu(model.getPermissionId(),new ArrayList<String>(new HashSet<String>(model.getMenuIds())));
		    if(result<=0){
		    	throw new AdmuiException("保存权限菜单信息异常！");
		    }
		 }
		 if(!model.getOperationIds().isEmpty()){
		    result=poMapper.batchInsertWithOperation(model.getPermissionId(),new ArrayList<String>(new HashSet<String>(model.getOperationIds())));
		    if(result<=0){
		    	throw new AdmuiException("保存权限操作信息异常！");
		    }
		 }
		 return result;
	}
	/**
	 * 查询某权限下所有的有权限的菜单ID集合
	 * @param permissionId
	 * @return
	 * @throws AdmuiException
	 */
	 public List<String> queryMenuIdListByPermissionId(String permissionId) throws AdmuiException{
		 if(StringHandler.isNullOrEmpty(permissionId)){
			 throw new AdmuiException("参数异常！");
		 }
		 return pmMapper.queryMenuIdListByPermissionId(permissionId);
	 }
}
