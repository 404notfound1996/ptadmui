package com.goshine.ptadmui.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.base.BaseServiceImpl;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.BlackList;
import com.goshine.ptadmui.sys.mapper.BlackListMapper;
import com.goshine.ptadmui.sys.service.BlackListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 黑名单SERVICE实现
 * @author goshine
 */
@Service("blackListService")
@Transactional
public class BlackListServiceImpl extends BaseServiceImpl<BlackList> implements BlackListService{
    @Autowired
    private BlackListMapper blackListMapper;
    
	@Override
	protected BaseMapper<BlackList> getMapper() {
		return blackListMapper;
	}
    
	/**
	 * 新增黑名单
	 * @param model
	 * @return
	 */
    @Override
    public int insert(Context context,BlackList model) throws AdmuiException{
    	if(model==null){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setId(IDCreater.genetateKey());
        	model.setCreateUser(context.getUserId());
        	model.setCreateDate(context.getCurrentDateTime());
        	return blackListMapper.insert(model);
        }
        return -1;
    }
    /**
     * 更新黑名单
     * @param model
     * @return
     */
    @Override
    public int update(Context context,BlackList model) throws AdmuiException{
    	if(model==null||StringHandler.isNullOrEmpty(model.getId())){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setCreateUser(context.getUserId());
        	model.setCreateDate(context.getCurrentDateTime());
        	return blackListMapper.update(model);
        }
        return -1;
    }
    /**
     * 根据ID批量删除黑名单
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
    	return blackListMapper.deleteByParams(params);
    }
    /**
     * 根据ID查询黑名单信息
     * @param id
     * @return
     */
    @Override
    public BlackList queryById(Context context,String id) throws AdmuiException{
    	if(StringHandler.isNullOrEmpty(id)){
    		throw new AdmuiException("参数异常！");
    	}
    	return blackListMapper.queryById(id);
    }
    
    /**
     * 根据IP查询黑名单信息
     * @param id
     * @return
     */
    @Override
    public BlackList queryByIp(String ip) throws AdmuiException{
    	if(StringHandler.isNullOrEmpty(ip)){
    		throw new AdmuiException("参数异常！");
    	}
    	BlackList queryModel=new BlackList();
    	queryModel.setIp(ip);
    	List<BlackList> list=blackListMapper.queryListByCond(queryModel);
    	return list!=null&&list.size()>0?list.get(0):null;
    }
    
    /**
     * 根据条件查询黑名单信息列表
     * @param model
     * @return
     */
    @Override
    public List<BlackList> queryListByCond(Context context,BlackList model){
    	if(model==null){
    		model=new BlackList();
    	}
    	return blackListMapper.queryListByCond(model);
    }
    
    /**
     * 分页查询黑名单信息列表
     * @param page
     * @return
     */
    @Override
    public Page<BlackList> queryListByPage(Context context,Page<BlackList> page) throws AdmuiException{
    	PageHelper.startPage(page.getPageIndex(),page.getPageSize()," sys_blacklist.id desc");
		List<BlackList> pageList=blackListMapper.queryListByPage(page);
		PageInfo<BlackList> pageInfo = new PageInfo<BlackList>(pageList);
		return page.generatePage(page,pageInfo);
    }
    /**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(BlackList model) throws AdmuiException{
    	//合法性校验
    	if(StringHandler.isNullOrEmpty(model.getIp())){
    		throw new AdmuiException("IP不能为空！");
    	}
    	BlackList queryModel=new BlackList();
    	queryModel.setIp(model.getIp());
    	List<BlackList> list=blackListMapper.queryListByCond(queryModel);
    	if(!StringHandler.isNullOrEmpty(model.getId())){
    		for(BlackList info:list){
    			if(info.getIp().equals(model.getIp())&&!info.getId().equals(model.getId())){
    				throw new AdmuiException("该IP已在黑名单中，无需重复添加！");
    			}
    		}
    	}else{
    		if(list!=null&&list.size()>0){
    			throw new AdmuiException("该IP已在黑名单中，无需重复添加！");
    		}
    	}
    	return true;
    }
}
