package com.goshine.ptadmui.security.interceptor;

import com.goshine.ptadmui.core.model.vo.ContextVo;
import com.goshine.ptadmui.sys.entity.Menu;
import com.goshine.ptadmui.sys.entity.Operation;
import com.goshine.ptadmui.sys.service.BlackListService;
import com.goshine.ptadmui.sys.service.MenuService;
import com.goshine.ptadmui.sys.service.OperationService;
import com.goshine.ptadmui.sys.service.UserService;
import com.goshine.ptadmui.sys.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * session失效拦截处理器
 * @author baisj
 */
public class SessionInterceptor implements HandlerInterceptor{
    private final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Resource
    private UserService userService;
    @Resource
    private MenuService menuService;
	@Autowired
    private OperationService operationService;
	@Autowired
	private BlackListService blackListService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
	    logger.info("---preHandle---");
	    try{
	    	Subject subject = SecurityUtils.getSubject();
	    	Session session=subject.getSession();
	    	ContextVo context=(ContextVo)session.getAttribute("context");
	        //判断用户是通过记住我功能自动登录,此时session失效
	        if(subject.isRemembered()){
				if(context==null){
					String userName=(String)subject.getPrincipal();
					try{
						UserVo user=userService.queryUserByUserName(userName);
						if(user!=null){
							if(blackListService.queryByIp(request.getRemoteHost())!=null) {
			        			//response.sendRedirect(request.getContextPath()+"/errors/noauth");
			        			return false;
			        		}
							ContextVo ctx=new ContextVo();
							ctx.setUserId(user.getId());
					        List<Menu> menuList=menuService.queryMenuListByUserId(ctx);
							if(menuList==null||menuList.size()<=0){//没有菜单权限，则跳转至没有权限页面
								//response.sendRedirect(request.getContextPath()+"/errors/noauth");
								return false;
							}
							//对密码进行加密后验证
			                UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
			                token.setRememberMe(subject.isRemembered());
			                //把当前用户放入session
			                subject.login(token);
					        //查询当前用户的菜单信息到session中
			                context=(ContextVo)session.getAttribute("context");
					        session.setAttribute("menuList",menuList);
					        //查询用户信息返回到前端
							request.setAttribute("userName",context.getUserName());
							request.setAttribute("roleList",user.getRoles());
							try{
								Map<String,String> menuMap=new HashMap<String,String>();
								List<Menu> allMenuList=menuService.queryMenuListByContext(context);
								for(Menu menu:allMenuList){
									menuMap.put(menu.getId(),menu.getUrl());
								}
								Map<String,Map<String, Operation>> operMap=new HashMap<String,Map<String, Operation>>();
								List<Operation> operList=operationService.queryOperationListByContext(context);
								if(operList!=null&&operList.size()>0){
									Map<String, Operation> map=null;
									for(Operation oper:operList){
										String requestUrl=menuMap.get(oper.getMenuId());
										map=operMap.get(requestUrl);
										if(map==null){
											map=new HashMap<String, Operation>();
										}
										map.put(oper.getId(),oper);
										operMap.put(requestUrl,map);
									}
								}
								request.getSession().setAttribute("allOperMap",operMap);
						    }catch(Exception e){}
					   }
	                }catch (Exception e){
		                //自动登录失败,跳转到登录页面
		               // response.sendRedirect(request.getContextPath()+"/login");
		                return false;
	                }
	            }
			}else{
				//未授权则跳转到登录页
				if(!subject.isAuthenticated()){
					//response.sendRedirect(request.getContextPath()+"/login");
				}
				if(context!=null){//当context不为空
					////判断是否被加入黑名单
					if(blackListService.queryByIp(request.getRemoteHost())!=null) {
	        			//response.sendRedirect(request.getContextPath()+"/errors/noauth");
	        			SecurityUtils.getSubject().logout();
	        			return false;
	        		}
					//判断是否有权限
	            	if(context.getMenuMap()==null||context.getMenuMap().isEmpty()){//没有菜单权限，则跳转至没有权限页面
						//response.sendRedirect(request.getContextPath()+"/errors/noauth");
						SecurityUtils.getSubject().logout();
						return false;
					}
	            }
			}
    	}catch(Exception e){
    		logger.debug(request.getRequestURI()+">>>errorMsg:"+e.getMessage());
    	}
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

    }
}