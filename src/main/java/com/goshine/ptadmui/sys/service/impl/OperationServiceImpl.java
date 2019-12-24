package com.goshine.ptadmui.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.base.BaseServiceImpl;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Operation;
import com.goshine.ptadmui.sys.mapper.OperationMapper;
import com.goshine.ptadmui.sys.service.OperationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 操作点业务实现类
 * @author goshine
 */
@Service("operationService")
public class OperationServiceImpl extends BaseServiceImpl<Operation> implements OperationService{
	@Autowired
	private OperationMapper operationMapper;
	@Override
	protected BaseMapper<Operation> getMapper() {
		return operationMapper;
	}
	
	/**
	 * 新增操作项
	 * @param model
	 * @return
	 */
    @Override
    public int insert(Context context,Operation model) throws AdmuiException{
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
        	return operationMapper.insert(model);
        }
        return -1;
    }
    /**
     * 更新操作项
     * @param model
     * @return
     */
    @Override
    public int update(Context context,Operation model) throws AdmuiException{
    	if(model==null||StringHandler.isNullOrEmpty(model.getId())){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setModifyUser(context.getUserId());
        	model.setModifyDate(context.getCurrentDateTime());
        	return operationMapper.update(model);
        }
        return -1;
    }
    /**
     * 根据ID批量删除操作项
     * @param list
     * @return
     */
    @Override
    public int deleteByIds(Context context,List<String> idList) throws AdmuiException{
    	if(idList==null||idList.isEmpty()){
    		throw new AdmuiException("请先选择删除项！");
    	}
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("ids",idList);
    	params.put("modifyUser",context.getUserId());
    	params.put("modifyDate",context.getCurrentDateTime());
    	return operationMapper.deleteByParams(params);
    }
    /**
     * 根据ID查询操作项信息
     * @param id
     * @return
     */
    @Override
    public Operation queryById(Context context,String id) throws AdmuiException{
    	if(StringHandler.isNullOrEmpty(id)){
    		throw new AdmuiException("参数异常！");
    	}
    	return operationMapper.queryById(id);
    }
    
    /**
     * 根据条件查询操作项信息列表
     * @param model
     * @return
     */
    @Override
    public List<Operation> queryListByCond(Context context,Operation model){
    	if(model==null){
    		model=new Operation();
    	}
    	return operationMapper.queryListByCond(model);
    }
    
    /**
     * 根据MenuId查询操作项信息列表
     * @param model
     * @return
     */
    @Override
    public List<Operation> queryListByMenuId(Context context,String menuId) throws AdmuiException{
    	if(StringHandler.isNullOrEmpty(menuId)){
    		logger.debug("OperationServiceImpl queryListByMenuId menuId is null");
    		throw new AdmuiException("参数异常！");
    	}
    	return operationMapper.queryListByMenuId(menuId);
    }
    
    /**
     * 分页查询操作项信息列表
     * @param page
     * @return
     */
    @Override
    public Page<Operation> queryListByPage(Context context,Page<Operation> page) throws AdmuiException{
    	PageHelper.startPage(page.getPageIndex(),page.getPageSize()," sys_operation.name desc");
		List<Operation> pageList=operationMapper.queryListByPage(page);
		PageInfo<Operation> pageInfo = new PageInfo<Operation>(pageList);
		return page.generatePage(page,pageInfo);
    }
    /**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(Operation model) throws AdmuiException{
    	//合法性校验
    	if(StringHandler.isNullOrEmpty(model.getOpertype())){
    		throw new AdmuiException("操作类型不能为空！");
    	}
    	if(StringHandler.isNullOrEmpty(model.getName())){
    		throw new AdmuiException("操作项名称不能为空！");
    	}
    	if(StringHandler.empty(model.getName()).length()>60){
    		throw new AdmuiException("操作项名称长度不能超过60字符！");
    	}
    	return true;
    }
    /**
     * 根据菜单ID左关联查询权限按钮列表
     * @param context
     * @param permissionId 权限ID
     * @param menuId 菜单ID
     */
	@Override
	public List<Operation> queryPermissionOperationListByMenuId(Context context, String permissionId, String menuId) {
		if(StringHandler.isNullOrEmpty(permissionId)||StringHandler.isNullOrEmpty(menuId)){
			return new ArrayList<Operation>();
		}
		return operationMapper.queryPermissionOperationListByMenuId(permissionId,menuId);
	}
    /**
     * 根据用户ID查询可操作的操作项集合
     */
	@Override
	public List<Operation> queryOperationListByContext(Context context){
		 if(context==null){
			return new ArrayList<Operation>();
		 }
		 return operationMapper.queryOperationListByUserId(context.getUserId());
	}
}
