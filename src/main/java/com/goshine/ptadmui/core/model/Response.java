package com.goshine.ptadmui.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.goshine.ptadmui.core.consts.ResultCodeConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 响应类
 * @author goshine
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="Response", description="返回对象")
@Data
public class Response<T>{

	@ApiModelProperty(value = "状态编码")
	private String code;
	@ApiModelProperty(value = "处理结果描述")
    private String msg;
	@ApiModelProperty(value = "返回的对象信息")

    private T data;

	/**
	 * 处理成功返回
	 * @return
	 */
	public static <T> Response<T> success(){

		return result(ResultCodeConstants.SUCCESS_CODE,"success",null);
	}
	
	/**
	 * 处理成功返回
	 * @return
	 */
	public static <T> Response<T> success(T obj){

		return result(ResultCodeConstants.SUCCESS_CODE,"success",obj);
	}
	/**
	 * 处理成功返回
	 * @return
	 */
	public static <T> Response<T> success(T obj,String msg){

		return result(ResultCodeConstants.SUCCESS_CODE,msg,obj);
	}
	/**
	 * 处理成功返回
	 * @return
	 */
	public static <T> Response<T> success(String msg){

		return result(ResultCodeConstants.SUCCESS_CODE,msg,null);
	}
	/**
	 * 处理异常返回
	 * @param msg
	 * @return
	 */

	public static <T> Response<T> error(String msg){
		return result(ResultCodeConstants.FAILED_CODE,msg,null);
    }

	public static <T> Response<T> error(String code,String msg){
		return result(code,msg,null);
	}
	public static <T> Response<T> error(String code,String msg,T obj){
		return result(code,msg,obj);
	}

	public static <T> Response<T> result(String code,String msg,T obj){
		Response<T> response=new Response<T>();

		response.setMsg(msg);
		response.setCode(code);
		response.setData(obj);
		return response;
	}
	/**
	 * 处理警告返回
	 * @param msg
	 * @return
	 */
	public static <T> Response<T> warn(String msg){

		 return result(ResultCodeConstants.WARN_CODE,msg,null);
    }
}