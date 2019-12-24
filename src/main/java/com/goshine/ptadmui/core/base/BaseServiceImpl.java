package com.goshine.ptadmui.core.base;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
/**
 * 基础实现类
 * @author goshine
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T>{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 获取Mapper示例
	 * @return
	 */
	protected abstract BaseMapper<T> getMapper();
	   /**
     * 插入
     * @param record
     * @return
     */
	@Override
    public abstract int insert(Context context,T record) throws AdmuiException;
    /**
     * 更新数据
     * @param record
     * @return
     */
	@Override
    public abstract int update(Context context,T record) throws AdmuiException;
	 /**
      * 根据条件删除
      * @param id
      * @return
      */
	@Override
    public abstract int deleteByIds(Context context,List<String> idsList) throws AdmuiException;
}
