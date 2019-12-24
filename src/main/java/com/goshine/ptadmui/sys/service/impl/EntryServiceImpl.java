package com.goshine.ptadmui.sys.service.impl;

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
import com.goshine.ptadmui.sys.entity.Entry;
import com.goshine.ptadmui.sys.mapper.EntryMapper;
import com.goshine.ptadmui.sys.service.EntryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 条目示例SERVICE实现
 * @author goshine
 */
@Service("entryService")
public class EntryServiceImpl extends BaseServiceImpl<Entry> implements EntryService{
    @Autowired
    private EntryMapper entryMapper;
    
	@Override
	protected BaseMapper<Entry> getMapper() {
		return entryMapper;
	}
    
	/**
	 * 新增条目
	 * @param model
	 * @return
	 */
    @Override
    public int insert(Context context,Entry model) throws AdmuiException{
    	if(model==null){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setId(Long.parseLong(IDCreater.genetateKey()));
        	return entryMapper.insert(model);
        }
        return -1;
    }
    /**
     * 更新条目
     * @param model
     * @return
     */
    @Override
    public int update(Context context,Entry model) throws AdmuiException{
    	if(model==null||StringHandler.isNullOrEmpty(model.getId())){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	return entryMapper.update(model);
        }
        return -1;
    }
    /**
     * 根据ID批量删除条目
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
    	return entryMapper.deleteByParams(params);
    }
    
    /**
     * 分页查询条目信息列表
     * @param page
     * @return
     */
    @Override
    public Page<Entry> queryListByPage(Context context,Page<Entry> page){
    	PageHelper.startPage(page.getPageIndex(),page.getPageSize()," demo_entry."+(StringHandler.isNullOrEmpty(page.getSortField())?"employee_id":page.getSortField())+" "+(StringHandler.isNullOrEmpty(page.getSortType())?" ASC":page.getSortType()));
		if(page.getData()==null){
			page.setData(new HashMap<String,Object>());
		}
    	List<Entry> pageList=entryMapper.queryListByPage(page);
		PageInfo<Entry> pageInfo = new PageInfo<Entry>(pageList);
		return page.generatePage(page,pageInfo);
    }
    
    /**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(Entry model) throws AdmuiException{
    	//合法性校验
    	return true;
    }
}
