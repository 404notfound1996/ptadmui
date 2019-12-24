package com.goshine.ptadmui.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.goshine.ptadmui.core.consts.Constant;
import com.goshine.ptadmui.core.model.vo.ContextVo;
import com.goshine.ptadmui.sys.entity.DisplaySetting;
import com.goshine.ptadmui.sys.service.DisplaySettingService;
/**
 * 显示切面
 * @author goshine
 */
@Aspect
@Component
public class DisplayAspect{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DisplaySettingService displaySettingService;
	/**
	 * 设置切面
	 */
    @Pointcut("execution(public * com.goshine.ptadmui.*.controller..*.*(..))")
    public void aspect(){}
    /**
     * <p>前置通知，只拦截所有带了RequestMapping的方法</p>
     *
     * @param joinPoint  切入点对象，可以无该参数
     */
    @Before("aspect() && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void before(JoinPoint joinPoint){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		HttpSession session = request.getSession(false);
		try{
			DisplaySetting display=null;
			ContextVo context=null;
			try{
				context=(ContextVo)session.getAttribute("context");
			}catch(Exception e){
				//response.sendRedirect(request.getContextPath()+"/login");
				return;
			}
			if(context!=null){
				display=displaySettingService.queryDisplaySettingByCurrentUser(context);
				if(display==null){
					display=displaySettingService.queryGlobal();
				}
			}else{
				display=displaySettingService.queryGlobal();//当登录失效，则查全局变量
			}
			request.getSession().setAttribute("display",display);
		}catch(Exception e){
			logger.debug(">>>display aspect error:"+e.getMessage());
		}
		request.setAttribute("layout",Constant.SYSTEM_LAYOUT_PATH);
	}
}

