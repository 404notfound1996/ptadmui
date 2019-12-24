package com.goshine.ptadmui.core.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
/**
 * ID生成类
 * Created by syf on 2016-12-22.
 */
public class IDCreater{
    public static String createId(){
        return UUID.randomUUID().toString().substring(0,32);
    }
    public static String getNewFileName(){
        return UUID.randomUUID().toString().substring(0,25);
    }
    public static String createToken(){
        return UUID.randomUUID().toString().substring(0,32);
    }
    
    /**
     * 获取单据号
     * 格式LIKE：IN+yyMMddHHmmss+n位随机数
     * @return
     */
    public static String getRecordNo(String preSuffix,int digit){
		 String dateTimeStr=DateHandler.formatDate("yyMMddHHmmss",new Date());//单据固定格式前缀
		 return preSuffix+dateTimeStr+StringHandler.getRandomNum(digit);
    }
    
    /**
	 * 商品id生成
	 */
	public static String genetateKey() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//加上三组两位随机数
		Random random = new Random();
		int endFix=random.nextInt(9999999);
		//如果不足两位前面补0
		return millis+String.format("%07d",endFix);
	}
	
	public static void main(String[] args){
		List<String> ids=new ArrayList<String>();
		for(int i=0;i<100000;i++){
			String id=genetateKey();
			if(ids.contains(id)){
				System.out.println("==================cf>>>>>>>>>>"+id);
			}else{
				System.out.println("==================id:"+id);
				ids.add(id);
			}
		}
		System.out.println("========finished========="+ids.size());
	}
}
