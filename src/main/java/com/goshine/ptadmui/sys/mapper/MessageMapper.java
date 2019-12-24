package com.goshine.ptadmui.sys.mapper;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.Message;
import com.goshine.ptadmui.sys.vo.MessageVo;

import java.util.List;
/**
 * 系统消息类
 * @author goshine
 */
public interface MessageMapper extends BaseMapper<Message>{
    /**
     * 根据条件查询数量信息
     * @param page
     * @return
     */
    MessageVo queryCountByUserId(Message model);
    /**
     * 根据条件查询
     * @param model
     * @return
     */
    List<Message> queryListByCond(Message model);
    /**
     * 根据条件分页查询系统信息列表
     * @param page
     * @return
     */
	List<Message> queryListByPage(Page<Message> page);
}