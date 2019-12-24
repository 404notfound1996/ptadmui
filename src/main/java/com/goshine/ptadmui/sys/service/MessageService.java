package com.goshine.ptadmui.sys.service;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Message;
import com.goshine.ptadmui.sys.vo.MessageVo;
/**
 * 系统消息业务接口层
 * @author goshine
 */
public interface MessageService extends BaseService<Message>{
	/**
	 * 发送消息
	 * @param userId 接收人
	 * @param title 标题
	 * @param content 内容
	 * @throws AdmuiException
	 */
	public void sendMsg(Context context,String userId,String title,String content);
    /**
     * 根据用户ID查询系统消息信息
     * @param model
     * @return
     */
    public MessageVo queryCountByUserId(Context context);
    /**
     * 分页查询系统消息列表
     * @param page
     * @return
     * @throws AdmuiException 
     */
	public Page<Message> queryListByPage(Context context,Page<Message> page) throws AdmuiException;
}
