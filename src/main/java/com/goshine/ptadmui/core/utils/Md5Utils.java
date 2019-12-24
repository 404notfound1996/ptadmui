package com.goshine.ptadmui.core.utils;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * MD5算法工具类
 * @author goshine
 */
public class Md5Utils{
	private static final Logger logger = LoggerFactory.getLogger(Md5Utils.class);
	
	private static byte[] md5(String s) {
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(s.getBytes("UTF-8"));
			byte[] messageDigest = algorithm.digest();
			return messageDigest;
		} catch (Exception e) {
			logger.error("MD5 Error...", e);
		}
		return null;
	}

	private static final String toHex(byte hash[]) {
		if (hash == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(hash.length * 2);
		int i;

		for (i = 0; i < hash.length; i++) {
			if ((hash[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(hash[i] & 0xff, 16));
		}
		return buf.toString();
	}
    /**
     * 字符串MD5加密
     * @param str
     * @return
     */
	public static String encrypt(String str) {
		try{
			if(str==null||"".equals(str)){
				return null;
			}
			return new String(toHex(md5(str)).getBytes("UTF-8"), "UTF-8");
		}catch(Exception e) {
			logger.error("not supported charset...{}", e);
		}
		return str;
	}
}
