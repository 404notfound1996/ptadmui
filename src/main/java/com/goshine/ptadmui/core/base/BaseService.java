package com.goshine.ptadmui.core.base;

import java.util.List;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
/**
 * 基础业务接口
 * @author goshine
 * @param <T>
 */
public interface BaseService<T>{
    /**
     * 插入
     * @param record
     * @return
     */
    public int insert(Context context,T record) throws AdmuiException;
    /**
     * 更新数据
     * @param record
     * @return
     */
    public int update(Context context,T record) throws AdmuiException;
	 /**
      * 根据条件删除
      * @param id
      * @return
      */
    public int deleteByIds(Context context,List<String> params) throws AdmuiException;
}
