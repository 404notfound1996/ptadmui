package com.goshine.ptadmui.core.base;

import java.util.Map;
/**
 * 基础映射接口
 * @author goshine
 */
public interface BaseMapper<T>{
    /**
     * 插入
     * @param record
     * @return
     */
    int insert(T record);
    /**
     * 更新数据
     * @param record
     * @return
     */
    int update(T record);
	 /**
      * 根据条件删除
      * @param id
      * @return
      */
    int deleteByParams(Map<String,Object> params);
}
