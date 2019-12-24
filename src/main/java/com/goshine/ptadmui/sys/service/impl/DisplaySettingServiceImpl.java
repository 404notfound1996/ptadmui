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
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.DisplaySetting;
import com.goshine.ptadmui.sys.mapper.DisplaySettingMapper;
import com.goshine.ptadmui.sys.service.DisplaySettingService;

/**
 * 显示设置SERVICE实现
 * @author goshine
 */
@Service("displaySettingService")
@Transactional
public class DisplaySettingServiceImpl extends BaseServiceImpl<DisplaySetting> implements DisplaySettingService{
    @Autowired
    private DisplaySettingMapper configMapper;
    
	@Override
	protected BaseMapper<DisplaySetting> getMapper() {
		return configMapper;
	}
    
	/**
	 * 保存显示设置
	 * @param model
	 * @return
	 */
    @Override
    public int save(Context context,DisplaySetting model) throws AdmuiException{
    	if(model==null){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	int result=-1;
        	model.setModifyDate(context.getCurrentDateTime());
        	if(StringHandler.isNullOrEmpty(model.getUserId())){//保存全局显示设置
        		DisplaySetting info=configMapper.queryGlobal();
        		if(info!=null){//全局变量存在，则更新
        			model.setId(info.getId());
        			result=configMapper.update(model);
        		}else{//全局变量不存在
        			model.setId(IDCreater.genetateKey());
        			model.setGlobalFlag(1);
        			result=configMapper.insert(model);
        		}
        	}else{//保存个人显示设置
        		DisplaySetting info=this.queryDisplaySettingByCurrentUser(context);
        		if(info!=null){
        			model.setId(info.getId());
        			model.setUserId(info.getUserId());
        			result=configMapper.update(model);
        		}else{
        			model.setId(IDCreater.genetateKey());
        			model.setUserId(context.getUserId());
        			result=configMapper.insert(model);
        		}
        	}
        	return result;
        }
        return -1;
    }
    
	/**
	 * 废弃
	 * @param model
	 * @return
	 */
    @Override
    public int insert(Context context,DisplaySetting model) throws AdmuiException{
        return -1;
    }
    /**
     * 废弃
     * @param model
     * @return
     */
    @Override
    public int update(Context context,DisplaySetting model) throws AdmuiException{
        return configMapper.update(model);
    }
    /**
     * 根据ID批量删除显示设置
     * @param list
     * @return
     */
    @Override
    public int deleteByIds(Context context,List<String> idList) throws AdmuiException{
    	if(context==null){
    		throw new AdmuiException("登录信息已失效，请重新登录后进行操作！");
    	}
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("userId",context.getUserId());
    	return configMapper.deleteByParams(params);
    }
    
    /**
     * 根据条件查询显示设置信息列表
     * @param model
     * @return
     */
    @Override
    public DisplaySetting queryDisplaySettingByCurrentUser(Context context){
    	DisplaySetting model=new DisplaySetting();
    	model.setUserId(context.getUserId());
    	List<DisplaySetting> settingList=configMapper.queryListByCond(model);
    	if(settingList!=null&&settingList.size()>0){
    		return settingList.get(0);
    	}
    	return null;
    }
    
	@Override
	@Transactional(readOnly = true)
	public DisplaySetting queryDefault() {
		return configMapper.queryDefault();
	}

	@Override
	@Transactional(readOnly = true)
	public DisplaySetting queryGlobal() {
		return configMapper.queryGlobal();
	}
    
    /**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(DisplaySetting model) throws AdmuiException{
    	//合法性校验
    	return true;
    }
}
