package com.goshine.ptadmui.sys.controller;


import com.goshine.ptadmui.common.utils.network.IpUtils;
import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.generator.Generator;
import com.goshine.ptadmui.core.generator.PngVCGenerator;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.model.vo.ContextVo;
import com.goshine.ptadmui.core.utils.Md5Utils;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.security.config.AccessConfig;
import com.goshine.ptadmui.security.utils.JwtUtil;
import com.goshine.ptadmui.sys.entity.Menu;
import com.goshine.ptadmui.sys.entity.Operation;
import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.service.BlackListService;
import com.goshine.ptadmui.sys.service.MenuService;
import com.goshine.ptadmui.sys.service.OperationService;
import com.goshine.ptadmui.sys.service.UserService;
import com.goshine.ptadmui.sys.vo.context.ContentContext;
import com.goshine.ptadmui.sys.vo.user.*;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 登录相关Controller
 * @author goshine
 */
@Controller
public class LoginController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private BlackListService blackListService;
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private OperationService operationService;
	@Autowired
	private AccessConfig accessConfig;
	/**
     * 获取验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/captcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception{
    	Integer height = 80;
        Integer width = 240;
        Integer count = 4;
        Font font = new Font("Arial", Font.ITALIC | Font.BOLD, 56); // 字体
        
        Generator generator = new PngVCGenerator(width, height, count, font);
        OutputStream stream = null;
		try {
			stream =httpServletResponse.getOutputStream();
			generator.write2out(stream);
	        httpServletResponse.setHeader("Cache-Control", "no-store");
	        httpServletResponse.setHeader("Pragma", "no-cache");
	        httpServletResponse.setDateHeader("Expires", 0);
	        httpServletResponse.setContentType("image/jpeg");
	        ServletOutputStream responseOutputStream=httpServletResponse.getOutputStream();
	        responseOutputStream.flush();
	        responseOutputStream.close();
	        String verifyCode = generator.text();
	        httpServletRequest.getSession().setAttribute("verifyCode",verifyCode);
		} catch (IOException e) {
			logger.error("获取登录验证码失败！", e);
		}
    }

	@ApiOperation(value="用户登录", notes="用户登录",produces = "application/json",httpMethod = "POST")
	@RequestMapping(value="/private/api/login", method = RequestMethod.POST)
	@ResponseBody
	public Response<ContentContext> login(HttpServletRequest request, @RequestBody LoginFormUser loginUser){
		try {
			if(blackListService.queryByIp(this.getLoginIp())!=null) {
				return Response.error("IP地址被封禁");
			}

			//系统生成的验证码
//			String sysVerifyCode=(String)request.getSession().getAttribute("verifyCode");
//			if(StringHandler.isNullOrEmpty(sysVerifyCode)||StringHandler.isNullOrEmpty(loginUser.getVerifyCode())||!sysVerifyCode.equalsIgnoreCase(loginUser.getVerifyCode())){
//				return Response.error("验证码错误！");
//			}
			//密码加密比对
			String password= Md5Utils.encrypt(StringHandler.empty(loginUser.getPassword()));
			HttpSession session=request.getSession();
			ContextVo context=userService.loginValidate(loginUser.getUserName(), IpUtils.getRemoteAddr(request),password);
			session.setAttribute("context",context);
			String webToken= JwtUtil.sign(loginUser.getUserName(),accessConfig.getTokenSecretKey());
			ContentContext contentContext=new ContentContext();
			Identity identity=new Identity();
			identity.setExecutiveMode(context.getExecutiveMode());
			identity.setUserId(context.getUserId());
			identity.setName(context.getName());
			identity.setUserName(context.getUserName());
			identity.setUserId(context.getUserId());
			identity.setUserType(context.getUserType());
			contentContext.setUser(identity);
			LoginSession loginSession=new LoginSession();
			loginSession.setLastLoginIp(context.getLastLoginIp());
			loginSession.setLastLoginTime(context.getLastLoginTime());
			loginSession.setLoginCount(context.getLoginCount());
			loginSession.setToken(webToken);
//			loginSession.setIP(IpUtils.getRemoteAddr(request));
//			loginSession.setAddress(Util.getIpAddressTwice("116.231.88.229"));
			contentContext.setLoginSession(loginSession);
			List<Menu> menus=menuService.queryMenuListByContext(context);
			contentContext.setFrontMenu(menuService.convertFrontMenu(menus));
			List<Role> roles=context.getRoles();
			contentContext.setUserRoles(new ArrayList<>());
			convertRole(roles,contentContext.getUserRoles());
			contentContext.setPermissions(new HashMap<>(32));
			List<Operation> operations=operationService.queryOperationListByContext(context);
			if(!Objects.isNull(operations)){
				for(Operation operation:operations){
					if(StringUtils.isBlank(operation.getMenuId())) {continue;}
					if(Objects.isNull(contentContext.getPermissions().get(operation.getMenuId()))){
						contentContext.getPermissions().put(operation.getMenuId(),new ArrayList<>());
					}
					contentContext.getPermissions().get(operation.getMenuId()).add(operation);
				}
			}
			return Response.success(contentContext);
		}catch (UnknownAccountException e){
			return Response.error("账号输入有误！");
		}catch (IncorrectCredentialsException e){
			return Response.error("用户名或者密码错误！");
		}catch (LockedAccountException e){
			return Response.error("账号已被禁用,请联系管理员！");
		}catch(Exception e){
			logger.debug("logout error:",e);
			return Response.error("用户登录异常！");
		}
	}
	/**
	 * 翻转角色信息
	 * @param roles 原始角色对象
	 * @param userRoles 被翻转角色对象
	 */
	private void convertRole(List<Role> roles, List<UserRole> userRoles){
		if(Objects.isNull(roles)){ return;}
		for(Role role:roles){
			UserRole userRole=new UserRole();
			userRole.setRoleName(role.getRoleName());
			userRole.setRoleType(role.getRoleType());
			if(!StringUtils.isBlank(role.getRoleGroupId())){
				UserRoleGroup userRoleGroup=new UserRoleGroup();
				userRoleGroup.setRoleGroupId(role.getRoleGroupId());
				userRole.setUserRoleGroup(userRoleGroup);
			}
			userRoles.add(userRole);
		}
	}
	/**
	 * 退出登录
	 * @return
	 */
	@ApiOperation(value="用户退出", notes="用户退出",produces = "application/json",httpMethod = "POST")
	@RequestMapping("/private/api/logout")
	@ResponseBody
	public Response<String> logOut(){
		try{
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			return Response.success();
		}catch(Exception e){
			logger.debug("logout error:"+e.getMessage(),e);
			return Response.error("用户退出异常！");
		}
	}
}
