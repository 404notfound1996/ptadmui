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
import com.goshine.ptadmui.core.model.KeyValue;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Log;
import com.goshine.ptadmui.sys.mapper.LogMapper;
import com.goshine.ptadmui.sys.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 系统日志业务实现
 * @author goshine
 */
@Service("logService")
@Transactional
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService{
    @Autowired
    private LogMapper logMapper;
    
	@Override
	protected BaseMapper<Log> getMapper() {
		return logMapper;
	}
    
	/**
	 * 新增日志
	 * @param model
	 * @return
	 */
    @Override
    public int insert(Context context,Log model) throws AdmuiException{
    	if(context==null||model==null){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setId(IDCreater.genetateKey());
        	model.setCreateUser(context.getUserId());
        	model.setCreateDate(context.getCurrentDateTime());
        	return logMapper.insert(model);
        }
        return -1;
    }
    /**
     * 更新
     * @param model
     * @return
     */
    @Override
    public int update(Context context,Log model) throws AdmuiException{
    	if(context==null||model==null||StringHandler.isNullOrEmpty(model.getId())){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
            return logMapper.update(model);
        }
        return -1;
    }
    /**
     * 根据ID批量删除
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
    	return logMapper.deleteByParams(params);
    }

    /**
     * 分页查询日志信息列表
     * @param page
     * @return
     */
    @Override
    public Page<Log> queryListByPage(Context context,Page<Log> page) throws AdmuiException{
    	PageHelper.startPage(page.getPageIndex(),page.getPageSize()," sys_log."+page.getSortField()+" "+(StringHandler.isNullOrEmpty(page.getSortType())?" desc":page.getSortType()));
    	if(page.getData()!=null){
    		page.getData().put("createUser",context.getUserId());
    	}else{
    		Map<String,Object> params=new HashMap<String,Object>();
    		params.put("createUser",context.getUserId());
    		page.setData(params);
    	}
		List<Log> pageList=logMapper.queryListByPage(page);
		PageInfo<Log> pageInfo = new PageInfo<Log>(pageList);
		return page.generatePage(page,pageInfo);
    }
	/**
	 * 根据登录用户获取当前日志条数
	 * @param context
	 * @return
	 * @throws AdmuiException
	 */
	public long queryCountByUserId(Context context) throws AdmuiException{
		if(context==null){
			throw new RuntimeException("Session Invalid!");
		}
		return logMapper.queryCountByUserId(context.getUserId());
	}
	/**
	 * 查询所有日志类型
	 * @return
	 */
	public List<String> queryLogTypeList(){
		return logMapper.queryLogTypeList();
	}
	/**
	 * 查询所有有日志记录的用户信息
	 * @return
	 */
	public List<KeyValue> queryUserList(){
		return logMapper.queryUserList();
	}
    /**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(Log model) throws AdmuiException{
    	return true;
    }
}
