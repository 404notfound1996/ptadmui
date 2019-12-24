package com.goshine.ptadmui.core.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.HandlerMapping;
import com.goshine.ptadmui.core.model.vo.ContextVo;
import com.goshine.ptadmui.core.utils.StringHandler;
/**
 * 公共Controller
 * @author goshine
 */
public class BaseController{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<HttpServletRequest>();
	protected static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<HttpServletResponse>();
	protected static ThreadLocal<HttpSession> sessionThreadLocal = new ThreadLocal<HttpSession>();
	protected static ThreadLocal<ContextVo> contextThreadLocal = new ThreadLocal<ContextVo>();
	
	@ModelAttribute
	protected void setContext(HttpServletRequest request,HttpServletResponse response) {
		requestThreadLocal.set(request);
		responseThreadLocal.set(response);
		sessionThreadLocal.set(request.getSession());
		if (this.getSession() != null && this.getSession().getAttribute("context") != null) {
			contextThreadLocal.set((ContextVo)getSession().getAttribute("context"));
		}
		// 根据当前url自动定位顶部及左侧菜单
		String currUrl = request.getRequestURI().replaceFirst(request.getContextPath(), "");
		String tempCurrUrl=currUrl;
		if (getContext()!= null) {
			Map<String,String> menuMap=this.getContext().getMenuMap();
			currUrl=generateCurrUrl(currUrl,menuMap);
		}
		//针对table示例部分做特殊处理
		if(!StringHandler.isNullOrEmpty(tempCurrUrl)&&tempCurrUrl.indexOf("data-tables/basic-init")>-1){
			currUrl="/tables/data-tables/basic-init/zero-configuration";
		}
		if(!StringHandler.isNullOrEmpty(tempCurrUrl)&&tempCurrUrl.indexOf("data-tables/advanced-init")>-1){
			currUrl="/tables/data-tables/advanced-init/events-live";
		}
		if(!StringHandler.isNullOrEmpty(tempCurrUrl)&&tempCurrUrl.indexOf("data-tables/data-sources/")>-1){
			currUrl="/tables/data-tables/data-sources/dom";
		}
		if(!StringHandler.isNullOrEmpty(tempCurrUrl)&&tempCurrUrl.indexOf("data-tables/api")>-1){
			currUrl="/tables/data-tables/api/add-row";
		}
		if(!StringHandler.isNullOrEmpty(tempCurrUrl)&&tempCurrUrl.indexOf("data-tables/ajax")>-1){
			currUrl="/tables/data-tables/ajax/simple";
		}
		if(!StringHandler.isNullOrEmpty(tempCurrUrl)&&tempCurrUrl.indexOf("data-tables/server-side")>-1){
			currUrl="/tables/data-tables/server-side/simple";
		}
		if(!StringHandler.isNullOrEmpty(tempCurrUrl)&&tempCurrUrl.indexOf("data-tables/plug-ins")>-1){
			currUrl="/tables/data-tables/plug-ins/api";
		}
		if(!StringHandler.isNullOrEmpty(tempCurrUrl)&&tempCurrUrl.indexOf("data-tables/others")>-1){
			currUrl="/tables/data-tables/others/fixed-header";
		}
		if(!StringHandler.isNullOrEmpty(tempCurrUrl)&&tempCurrUrl.indexOf("bootstrap-table/options")>-1){
			currUrl="/tables/bootstrap-table/options/boolean-options";
		}
		if(!StringHandler.isNullOrEmpty(tempCurrUrl)&&tempCurrUrl.indexOf("bootstrap-table/methods")>-1){
			currUrl="/tables/bootstrap-table/methods/getOptions";
		}
		if(!StringHandler.isNullOrEmpty(tempCurrUrl)&&tempCurrUrl.indexOf("bootstrap-table/extensions")>-1){
			currUrl="/tables/bootstrap-table/extensions/export";
		}
		request.setAttribute("currUrl",currUrl);
		request.setAttribute("fullCurrUrl",request.getRequestURI().replaceFirst(request.getContextPath(),""));
	}
	
	private String generateCurrUrl(String currUrl, Map<String,String> menuMap){
		if(StringHandler.isNullOrEmpty(currUrl)){
			return "/";
		}
		if (!currUrl.equals("/") &&currUrl.indexOf("/")!=-1&&!menuMap.containsKey(currUrl)) {
			currUrl=currUrl.substring(0,currUrl.lastIndexOf("/"));
			return generateCurrUrl(currUrl,menuMap);
		}
		return currUrl;
	}
	
	protected String extractPathFromPattern(){
        String path = (String)getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String)getRequest().getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	protected void addCookie(String name, String value, int maxAge) {
		try {
		    Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		    cookie.setPath("/");
		    if(maxAge > 0)  cookie.setMaxAge(maxAge);
		    getResponse().addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			logger.error("add " + value + " to " + name + " failed");
		}
	}
	
	/**
	 * 获取登录用户的IP
	 * @throws Exception 
	 */
	protected String getLoginIp(){
		String ip = getRequest().getHeader("X-Forwarded-For");
		if(!StringHandler.isNullOrEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = getRequest().getHeader("X-Real-IP");
		if(!StringHandler.isNullOrEmpty(ip)&& !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return getRequest().getRemoteAddr();
	}
	public HttpServletRequest getRequest() {
		return requestThreadLocal.get();
	}

	public HttpServletResponse getResponse() {
		return responseThreadLocal.get();
	}

	public HttpSession getSession() {
		return sessionThreadLocal.get();
	}

	public ContextVo getContext(){
		return contextThreadLocal.get();
	}
}
