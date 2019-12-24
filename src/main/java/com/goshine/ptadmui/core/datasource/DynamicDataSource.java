package com.goshine.ptadmui.core.datasource;


import com.goshine.ptadmui.core.datasource.context.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 设置动态数据源
 * @author litao
 * @date 2019年11月5日
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName= DynamicDataSourceContextHolder.getCurrentDataSourceType();
        return dataSourceName;
    }
}
