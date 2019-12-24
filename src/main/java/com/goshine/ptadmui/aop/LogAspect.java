package com.goshine.ptadmui.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.goshine.ptadmui.core.consts.Constant;
import com.goshine.ptadmui.core.model.vo.ContextVo;
import com.goshine.ptadmui.core.utils.Md5Utils;
import com.goshine.ptadmui.sys.entity.Log;
import com.goshine.ptadmui.sys.service.LogConfigService;
import com.goshine.ptadmui.sys.service.LogService;
/**
 * 日志切面
 * @author goshine
 */
@Aspect
@Component
public class LogAspect{
    private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LogService logService;
	@Autowired
	private LogConfigService logConfigService;
	/**
	 * 设置切面
	 */
    @Pointcut("execution(public * com.goshine.ptadmui.*.controller..*.*(..))")
    public void aspect(){}
    
    @AfterReturning(pointcut = "aspect()&&@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void doAfterReturning(JoinPoint joinPoint) throws Throwable{
    	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    	HttpServletResponse response =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		String requestUrl=request.getRequestURI();
		String requestIp=request.getRemoteAddr();
		HttpSession session =request.getSession();
		ContextVo context=null;
		try{
			context=(ContextVo) session.getAttribute("context");
		}catch(Exception e){
			//response.sendRedirect(request.getContextPath()+"/login");
		}
		// 记录下请求内容
        logger.debug("URL : "+requestUrl);
		// 根据sys_log_config配置表记录判断是否记录日志
        try{
        	if(context!=null&&(Constant.configMap==null||Constant.configMap.isEmpty())){//为空时，则重新加载
        		Constant.configMap=logConfigService.queryConfigMap(context,null);
        	}
            //记录日志
			if(context!=null&&Constant.configMap!=null&&!Constant.configMap.isEmpty()){
				for (String url:Constant.configMap.keySet()){
					if(requestUrl.indexOf(url)>-1){
						Log logInfo=new Log();
						logInfo.setUrl(url);
						logInfo.setType(Constant.configMap.get(url));
						logInfo.setOperateIp(requestIp);
						logInfo.setParams(getParamStr(request));
						// 记录操作用户
						logService.insert(context,logInfo);
						break;
					}
				}
			}
        }catch(Exception e){
        	logger.debug("doAfterReturning error:"+e.getMessage());
        }
    }
    /**
     * 获取参数
     * @param request
     * @return
     */
    private String getParamStr(HttpServletRequest request){
    	String str = "[";
		String pName = null;
		String pValue = null;
		Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()){
			pName = e.nextElement().toString();
			if (pName.equals("password")) {
				pValue=Md5Utils.encrypt(request.getParameter(pName));
			}else{
				pValue=request.getParameter(pName);
			}
			str+=pName+"="+pValue+" ";
		}
		str+="]";
		return str;
    }
}