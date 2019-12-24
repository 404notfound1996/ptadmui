package com.goshine.ptadmui.core.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量参数类
 * @author goshine
 */
public class Constant{
    /**
	 * 需要记录日志的接口集合
	 * 键为url，值为类型
	 */
	public static Map<String,String> configMap=new HashMap<String,String>();
	/**
	 * 超级管理员用户编码(ID)
	 */
	public static String SUPER__ADMIN_CODE="10010101";
	/**
	 * 超级管理员所属角色编码(ID)
	 */
	public static String ADMIN__ROLE_CODE="100101";
	/**
	 * 默认内置角色组编码
	 */
	public static String DEFAULT_ROLEGROUP_CODE="1001";
	/**
	 * 岗位内置角色组编码
	 */
	public static String STATION_ROLEGROUP_CODE="2002";
	/**
	 * 区域内置角色组编码
	 */
	public static String REGION_ROLEGROUP_CODE="3003";
	/**
	 * 超级管理员所属权限(ID)
	 */
	public static String ADMIN__PERMISSION_CODE="1000000001";
	/**
	 * 主题路径
	 */
	public static String SYSTEM_LAYOUT_PATH="base";
}
