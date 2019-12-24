package com.goshine.ptadmui.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 全局异常监听类
 * @author goshine
 */
@ControllerAdvice
public class GlobalDefultExceptionAdvice implements HandlerExceptionResolver{
	private Logger logger=LoggerFactory.getLogger(GlobalDefultExceptionAdvice.class);
	//声明要捕获的异常
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv=new ModelAndView();
        //进行异常判断。如果捕获异常请求跳转。
        if(ex instanceof UnauthorizedException){
        	logger.debug(">>>>>>当前请求没有权限，跳转至没有权限页面<<<<<<<"+ex.getMessage());
            mv = new ModelAndView("/errors/noauth");
            return mv;
        }else{
        	logger.debug(">>>>>>当前请求发生异常，跳转至错误页面<<<<<<<"+ex.getMessage());
            request.setAttribute("errorCode","400");
            mv.setViewName("/errors");
            return mv; 
        }
    }
}