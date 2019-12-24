package com.goshine.ptadmui.core.enums;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Enums{
	private static Map<String, Map<String, String>> allMap;	
	/**
	 * 是否
	 * 0：否，1：是
	 * @author goshine
	 */
	public enum YesOrNo{
		No("否",0),Yes("是",1);
	    private String name;  
	    private int index;  
	    // 构造方法  
	    private YesOrNo(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    //普通方法  
	    public static String getName(int index){  
	        for (YesOrNo c:YesOrNo.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	    public void setIndex(int index) {  
	        this.index = index;  
	    }  
	}
	
	/**
	 * 停启用状态
	 * 0：启用，1：停用
	 * @author goshine
	 */
	public enum DataState{
		Enable("启用",0),Disable("禁用",1);
	    private String name;  
	    private int index;  
	    // 构造方法  
	    private DataState(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    //普通方法  
	    public static String getName(int index){  
	        for (DataState c:DataState.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	    public void setIndex(int index) {  
	        this.index = index;  
	    }  
	}
	
	/**
	 * 消息类型
	 * 1：系统消息
	 * @author goshine
	 */
	public enum MessageType{
		SystemMsg("系统消息",1);
	    private String name;  
	    private int index;  
	    // 构造方法  
	    private MessageType(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    //普通方法  
	    public static String getName(int index){  
	        for (MessageType c:MessageType.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	    public void setIndex(int index) {  
	        this.index = index;  
	    }  
	}
	
	/**
	 * 角色类型
	 * 1：功能角色，2：数据角色
	 * @author goshine
	 */
	public enum RoleType{
		FunctionRole("功能角色",1),DataRole("数据角色",2);
	    private String name;
	    private int index;
	    // 构造方法  
	    private RoleType(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    //普通方法  
	    public static String getName(int index){  
	        for (RoleType c:RoleType.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	    public void setIndex(int index) {  
	        this.index = index;  
	    }  
	}
	
	/**
	 * 授权项类型
	 * 1：菜单，2：按钮
	 * @author goshine
	 */
	public enum AuthItemType{
		Menu("菜单",1),Button("按钮",2);
	    private String name;  
	    private int index;  
	    // 构造方法  
	    private AuthItemType(String name, int index){
	        this.name = name;
	        this.index = index;
	    }  
	    //普通方法  
	    public static String getName(int index){  
	        for(AuthItemType c:AuthItemType.values()) {  
	            if(c.getIndex()==index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	    public void setIndex(int index) {  
	        this.index = index;  
	    }  
	}
	
	/**
	 * 性别
	 * 1：男，2：女
	 * @author goshine
	 */
	public enum SexType{
		Male("男",1),Female("女",2);
	    private String name; 
	    private int index;
	    // 构造方法  
	    private SexType(String name, int index){
	        this.name = name;
	        this.index = index;
	    }  
	    //普通方法  
	    public static String getName(int index){  
	        for(SexType c:SexType.values()) {  
	            if(c.getIndex()==index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	    public void setIndex(int index) {  
	        this.index = index;  
	    }  
	}
	
	/**
	 * 节点类型
	 * U：人员，C：公司或者部门
	 * @author goshine
	 */
	public enum NodeType{
		Person("人员","U"),Company("公司或部门","C"),Role("角色","R"),RoleGroup("角色组","G"),DefaultRoleGroup("默认角色组","D");
	    private String name;
	    private String index;
	    // 构造方法  
	    private NodeType(String name,String index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    //普通方法  
	    public static String getName(String index){  
	        for (NodeType c:NodeType.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public String getIndex() {  
	        return index;  
	    }  
	    public void setIndex(String index) {  
	        this.index = index;  
	    }  
	}
	
	public static Map<String, Map<String, String>> getAll(){
		if(allMap!=null){
			return allMap;
		}else{
		    allMap=new HashMap<String, Map<String, String>>();
		}
		try{
			Class<?>[] ls = Enums.class.getClasses();
			for (int j = 0; j < ls.length; j++) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				Class<?> cls = ls[j];
				String name = cls.getName().substring(cls.getName().indexOf('$') + 1);
				Method method = cls.getMethod("values");
				Object[] values = (Object[]) method.invoke(null, new Object[] {});
				Class<?> itemCls = values[0].getClass();
				for (int i = 0; i < values.length; i++) {
					Object item = values[i];
					String desc = itemCls.getMethod("getName").invoke(item, new Object[] {}).toString();
					String index = itemCls.getMethod("getIndex").invoke(item, new Object[] {}).toString();
					map.put(index, desc);
				}
				allMap.put(name, map);
			}
		}catch(Exception e){}
		return allMap;
	}
	/**
	 * 根据枚举名称获取值集合
	 */
	public static Map<String,String> getEnumListByEnumName(String enumName){
		 Enums.getAll();
		 Map<String,String> map=allMap.get(enumName);
         return map;
	}
}
