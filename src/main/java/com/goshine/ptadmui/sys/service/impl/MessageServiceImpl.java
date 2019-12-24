package com.goshine.ptadmui.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.base.BaseServiceImpl;
import com.goshine.ptadmui.core.enums.Enums;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Message;
import com.goshine.ptadmui.sys.mapper.MessageMapper;
import com.goshine.ptadmui.sys.service.MessageService;
import com.goshine.ptadmui.sys.vo.MessageVo;
import com.goshine.ptadmui.sys.websocket.SocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 系统消息业务实现
 * @author goshine
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService{
    @Autowired
    private MessageMapper msgMapper;
	@Autowired
	private SocketHandler webSocketService;
    
	@Override
	protected BaseMapper<Message> getMapper(){
		return msgMapper;
	}
	
	/**
	 * 发送消息
	 * @param userId 接收人
	 * @param title 标题
	 * @param content 内容
	 * @throws AdmuiException
	 */
	public void sendMsg(Context context,String userId,String title,String content){
		 try{
			 Message msg=new Message();
			 msg.setUserId(userId);
			 msg.setTitle(title);
			 msg.setContent(content);
			 int result=this.insert(context,msg);
			 if(result>0){
				 msg.setSendTime(context.getCurrentDateTime());
				 msg.setReadFlag(0);
				 ObjectMapper mapper = new ObjectMapper();  
			     String json =  mapper.writeValueAsString(msg);  
				 webSocketService.sendMsg(userId,new TextMessage(json));
			 }
		 }catch(Exception e){
			 logger.debug("MessageService sendMsg error:"+e.getMessage(),e);
		 }
	}
    
	/**
	 * 新增组织机构
	 * @param model
	 * @return
	 */
    @Override
    public int insert(Context context,Message model) throws AdmuiException{
    	if(model==null){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setId(IDCreater.genetateKey());
        	model.setType(Enums.MessageType.SystemMsg.getIndex());
        	model.setReadFlag(Enums.YesOrNo.No.getIndex());//未读
        	model.setSendTime(context.getCurrentDateTime());
        	return msgMapper.insert(model);
        }
        return -1;
    }
    /**
     * 更新组织机构
     * @param model
     * @return
     */
    @Override
    public int update(Context context,Message model) throws AdmuiException{
    	if(model==null||StringHandler.isNullOrEmpty(model.getId())){
    		throw new AdmuiException("参数异常！");
    	}
    	model.setUserId(context.getUserId());
    	model.setReadFlag(Enums.YesOrNo.Yes.getIndex());
    	model.setReadTime(context.getCurrentDateTime());
    	return msgMapper.update(model);
    }
    /**
     * 根据ID批量删除组织机构
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
    	return msgMapper.deleteByParams(params);
    }
    
    /**
     * 根据用户ID查询消息数量信息
     * @param model
     * @return
     */
    @Override
    public MessageVo queryCountByUserId(Context context){
    	Message message=new Message();
    	message.setUserId(context.getUserId());
    	MessageVo model=msgMapper.queryCountByUserId(message);
    	if(model!=null){
    		model.setNoReadCount(model.getTotalCount()-model.getReadCount());
    	}
    	return model;
    }
    
    /**
     * 分页查询组织机构信息列表
     * @param page
     * @return
     */
    @Override
    public Page<Message> queryListByPage(Context context,Page<Message> page) throws AdmuiException{
    	PageHelper.startPage(page.getPageIndex(),page.getPageSize()," sys_message.send_time desc");
    	if(page.getData()!=null){
    		page.getData().put("userId",context.getUserId());
    	}else{
    		Map<String,Object> params=new HashMap<String,Object>();
    		params.put("userId",context.getUserId());
	    	page.setData(params);
    	}
		List<Message> pageList=msgMapper.queryListByPage(page);
		PageInfo<Message> pageInfo = new PageInfo<Message>(pageList);
		return page.generatePage(page,pageInfo);
    }
    /**
     * 对象校验
     * @param model
     * @throws BizException
     */
    private boolean validateModel(Message model) throws AdmuiException{
    	//合法性校验
    	if(StringHandler.isNullOrEmpty(model.getUserId())){
    		throw new AdmuiException("接收人不能为空！");
    	}
    	//合法性校验
    	if(StringHandler.isNullOrEmpty(model.getTitle())){
    		throw new AdmuiException("标题不能为空！");
    	}
    	if(StringHandler.empty(model.getTitle()).length()>50){
    		throw new AdmuiException("标题长度不能超过50字符！");
    	}
    	if(StringHandler.isNullOrEmpty(model.getContent())){
    		throw new AdmuiException("内容不能为空！");
    	}
    	return true;
    }
}