package com.goshine.ptadmui.sys.service.impl;

import java.util.HashMap;
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
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.LogConfig;
import com.goshine.ptadmui.sys.mapper.LogConfigMapper;
import com.goshine.ptadmui.sys.service.LogConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 日志配置SERVICE实现
 * @author goshine
 */

@Service("logConfigService")
@Transactional
public class LogConfigServiceImpl extends BaseServiceImpl<LogConfig> implements LogConfigService{
   @Autowired
    private LogConfigMapper configMapper;
    
	@Override
	protected BaseMapper<LogConfig> getMapper() {
		return configMapper;
	}
    
	/**
	 * 新增日志配置
	 * @param model
	 * @return
	 */
    @Override
    public int insert(Context context,LogConfig model) throws AdmuiException{
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
        	int result=configMapper.insert(model);
        	if(result>0){//日志配置变更之后清空日志配置集合，以便下次拦截时重新加载
        		if(Constant.configMap!=null){
        			Constant.configMap.clear();
        		}
        	}
        	return result;
        }
        return -1;
    }
    /**
     * 更新日志配置
     * @param model
     * @return
     */
    @Override
    public int update(Context context,LogConfig model) throws AdmuiException{
    	if(model==null||StringHandler.isNullOrEmpty(model.getId())){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setModifyUser(context.getUserId());
        	model.setModifyDate(context.getCurrentDateTime());
        	int result=configMapper.update(model);
        	if(result>0){//日志配置变更之后清空日志配置集合，以便下次拦截时重新加载
        		if(Constant.configMap!=null){
        			Constant.configMap.clear();
        		}
        	}
        	return result;
        }
        return -1;
    }
    /**
     * 根据ID批量删除日志配置
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
    	int result=configMapper.deleteByParams(params);
    	if(result>0){//日志配置变更之后清空日志配置集合，以便下次拦截时重新加载
    		if(Constant.configMap!=null){
    			Constant.configMap.clear();
    		}
    	}
    	return result;
    }
    /**
     * 根据ID查询日志配置信息
     * @param id
     * @return
     */
    @Override
    public LogConfig queryById(Context context,String id) throws AdmuiException{
    	if(StringHandler.isNullOrEmpty(id)){
    		throw new AdmuiException("参数异常！");
    	}
    	return configMapper.queryById(id);
    }
    
    /**
     * 根据条件查询日志配置信息列表
     * @param model
     * @return
     */
    @Override
    public List<LogConfig> queryListByCond(Context context,LogConfig model){
    	if(model==null){
    		model=new LogConfig();
    	}
    	return configMapper.queryListByCond(model);
    }
    
    /**
     * 分页查询日志配置信息列表
     * @param page
     * @return
     */
    @Override
    public Page<LogConfig> queryListByPage(Context context,Page<LogConfig> page) throws AdmuiException{
    	PageHelper.startPage(page.getPageIndex(),page.getPageSize()," sys_log_config.id desc");
		List<LogConfig> pageList=configMapper.queryListByPage(page);
		PageInfo<LogConfig> pageInfo = new PageInfo<LogConfig>(pageList);
		return page.generatePage(page,pageInfo);
    }
    /**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(LogConfig model) throws AdmuiException{
    	//合法性校验
    	if(StringHandler.isNullOrEmpty(model.getType())){
    		throw new AdmuiException("日志类型不能为空！");
    	}
    	if(StringHandler.isNullOrEmpty(model.getUrl())){
    		throw new AdmuiException("日志地址不能为空！");
    	}
    	return true;
    }
    /**
     * 以键值对的形式返回日志配置
     */
	@Override
	public Map<String, String> queryConfigMap(Context context, LogConfig model) {
		Map<String,String> map=new HashMap<String,String>();
		List<LogConfig> configList=this.queryListByCond(context,model);
		if(configList!=null&&configList.size()>0){
			for(LogConfig info:configList){
				map.put(info.getUrl(),info.getType());
			}
		}
		return map;
	}
}
