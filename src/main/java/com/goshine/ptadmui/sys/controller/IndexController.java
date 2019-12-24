package com.goshine.ptadmui.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.goshine.ptadmui.core.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.enums.Enums;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.sys.entity.BlackList;
import com.goshine.ptadmui.sys.entity.DisplaySetting;
import com.goshine.ptadmui.sys.entity.LogConfig;
import com.goshine.ptadmui.sys.entity.Menu;
import com.goshine.ptadmui.sys.entity.Operation;
import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.service.BlackListService;
import com.goshine.ptadmui.sys.service.DisplaySettingService;
import com.goshine.ptadmui.sys.service.LogConfigService;
import com.goshine.ptadmui.sys.service.LogService;
import com.goshine.ptadmui.sys.service.MenuService;
import com.goshine.ptadmui.sys.service.MessageService;
import com.goshine.ptadmui.sys.service.OperationService;
import com.goshine.ptadmui.sys.service.RoleService;
import com.goshine.ptadmui.sys.vo.MessageVo;
import com.goshine.ptadmui.core.model.KeyValue;

@Controller
public class IndexController extends BaseController{
	@Autowired
	private MessageService messageService;
	@Autowired
	private LogService logService;
	@Autowired
    private MenuService menuService;
	@Autowired
    private OperationService operationService;
	@Autowired
	private DisplaySettingService displaySettingService;
	@Resource
    private LogConfigService logConfigService;
	@Resource
    private BlackListService blackListService;
	@Resource
    private RoleService roleService;

	/**
	 * 系统信息
	 * 菜单管理
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/private/api/system/menu",method = RequestMethod.POST)
	@ResponseBody
	public Response<Map<String,Operation>> MenuMagement(HttpServletRequest request){

		String path = (String)getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Map<String,Map<String,Operation>> operMap=(Map<String,Map<String,Operation>>)request.getSession().getAttribute("allOperMap");
		return Response.success((operMap!=null&&operMap.get(path)!=null)?operMap.get(path):new HashMap<String,Operation>(),"成功！！");
	}



	/**组织机构
	 * 返回Json
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/private/api/system/organization",method = RequestMethod.POST)
	public Response<Map<String, Object>> OrganizationMagement(HttpServletRequest request){
		Map<String,Object > jsonmap = new HashMap<>();
		try{
			//查询角色列表
			List<Role> roleList=roleService.queryListByCond(this.getContext(),new Role());
			List<KeyValue> list=new ArrayList<KeyValue>();
			if(roleList!=null&&roleList.size()>0){
				KeyValue kv=null;
				for(Role role:roleList){
					kv=new KeyValue();
					kv.setId(role.getId());
					kv.setText(role.getRoleName());
					list.add(kv);
				}
			}
			jsonmap.put("roleList",list);
			//查询启禁用列表
			List<KeyValue> statusList=new ArrayList<KeyValue>();
			Map<String,String> map=Enums.getEnumListByEnumName("DataState");
			for (Map.Entry<String,String> entry:map.entrySet()){
				KeyValue kv=new KeyValue();
				kv.setId(entry.getKey());
				kv.setText(entry.getValue());
				statusList.add(kv);
			}
			jsonmap.put("statusList",statusList);
		} catch (AdmuiException e) {
			logger.debug("toListPage153 queryRoleList error:"+e.getMessage());
			Response.error("参数错误");
		}
		String path = (String)getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Map<String,Map<String,Operation>> operMap=(Map<String,Map<String,Operation>>)request.getSession().getAttribute("allOperMap");
		jsonmap.put("operMap",(operMap!=null&&operMap.get(path)!=null)?operMap.get(path):new HashMap<String,Operation>());

		return Response.success(jsonmap);
	}

	/**角色管理
	 * 返回json
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/private/api/system/role",method = RequestMethod.POST)
	public Response<Map<String,Operation>> RoleManagement(HttpServletRequest request){
		String path = (String)getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Map<String,Map<String,Operation>> operMap=(Map<String,Map<String,Operation>>)request.getSession().getAttribute("allOperMap");
		return Response.success((operMap!=null&&operMap.get(path)!=null)?operMap.get(path):new HashMap<String,Operation>());
	}

	/**权限管理
	 * 返回jason
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/private/api/system/permission",method = RequestMethod.POST)
	public Response<Map<String,Operation>> PermissionMagement( HttpServletRequest request){
		String path = (String)getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Map<String,Map<String,Operation>> operMap=(Map<String,Map<String,Operation>>)request.getSession().getAttribute("allOperMap");
		request.setAttribute("operMap",(operMap!=null&&operMap.get(path)!=null)?operMap.get(path):new HashMap<String,Operation>());
		return Response.success((operMap!=null&&operMap.get(path)!=null)?operMap.get(path):new HashMap<String,Operation>());
	}


	/**日志信息
	 * 返回json格式
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/private/api/system/log",method = RequestMethod.POST)
	public Response<Map<String,Object>> QueryLog(HttpServletRequest request){
		//查询所有日志类型
		Map<String,Object > jsonmap = new HashMap<>();
		List<String> logTypeList=logService.queryLogTypeList();
		jsonmap.put("logTypeList",logTypeList);

		//查询所有记录了日志的用户信息列表
		List<KeyValue> userList=logService.queryUserList();
		jsonmap.put("userList",userList);
		String path = (String)getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Map<String,Map<String,Operation>> operMap=(Map<String,Map<String,Operation>>)request.getSession().getAttribute("allOperMap");
		jsonmap.put("operMap",(operMap!=null&&operMap.get(path)!=null)?operMap.get(path):new HashMap<String,Operation>());
		return Response.success(jsonmap);
	}


	/**黑名单
	 * 返回json
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/private/api/system/blacklist",method = RequestMethod.GET)
	public Response<Map<String,Object>> Blacklist(HttpServletRequest request){
		Map<String,Object > jsonmap = new HashMap<>();
		Context context=this.getContext();
		List<BlackList> list=blackListService.queryListByCond(context,new BlackList());
		if(list==null){
			list=new ArrayList<BlackList>();
		}
		jsonmap.put("blackList",list);
		String path = (String)getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Map<String,Map<String,Operation>> operMap=(Map<String,Map<String,Operation>>)request.getSession().getAttribute("allOperMap");
		jsonmap.put("operMap",(operMap!=null&&operMap.get(path)!=null)?operMap.get(path):new HashMap<String,Operation>());
		return Response.success(jsonmap);
	}

	/**系统设置
	 * 显示设置
	 * 返回json
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/private/api/system/settings/display",method = RequestMethod.POST)
	public Response<Map<String,Object>> DisplaySetting(HttpServletRequest request){
		Map<String,Object > jsonmap = new HashMap<>();
		DisplaySetting display=displaySettingService.queryGlobal();
		if(display==null){
			display=displaySettingService.queryDefault();//设置全局查默认显示值
			display.setId(null);
		}
		jsonmap.put("display",display);
		//刷新时替换session中的样式
		request.getSession().setAttribute("display",display);
		String path = (String)getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Map<String,Map<String,Operation>> operMap=(Map<String,Map<String,Operation>>)request.getSession().getAttribute("allOperMap");
		jsonmap.put("operMap",(operMap!=null&&operMap.get(path)!=null)?operMap.get(path):new HashMap<String,Operation>());
		return Response.success(jsonmap);
	}


	/**日志设置
	 * 返回json
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/private/api/system/settings/log",method = RequestMethod.POST)
	public Response<Map<String,Object>> LogSetting(HttpServletRequest request){
		Map<String,Object > jsonmap = new HashMap<>();
		Context context=this.getContext();
		List<LogConfig> list=logConfigService.queryListByCond(context,new LogConfig());
		if(list==null){
			list=new ArrayList<LogConfig>();
		}
		jsonmap.put("logConfigList",list);
		String path = (String)getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Map<String,Map<String,Operation>> operMap=(Map<String,Map<String,Operation>>)request.getSession().getAttribute("allOperMap");
		jsonmap.put("operMap",(operMap!=null&&operMap.get(path)!=null)?operMap.get(path):new HashMap<String,Operation>());
		return Response.success(jsonmap);
	}
	/**我的账户
	 * 账户信息
	 * 返回json
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/private/api/system/account",method = RequestMethod.POST)
	public Response<Map<String,Object>> MyAccount(HttpServletRequest request){
		Map<String,Object > jsonmap = new HashMap<>();
		try{
			Context context=this.getContext();
			//计算消息数
			MessageVo msgInfo=messageService.queryCountByUserId(context);
			jsonmap.put("msgCount",msgInfo==null?0:msgInfo.getTotalCount());
			//计算日志数
			long logCount=logService.queryCountByUserId(context);
			jsonmap.put("logCount",logCount<=1000?logCount:logCount/1000+"K+");
			//显示设置
			DisplaySetting display=displaySettingService.queryDisplaySettingByCurrentUser(this.getContext());
			if(display==null){
				display=displaySettingService.queryGlobal();
			}
			jsonmap.put("display",display);
			jsonmap.put("userId",context.getUserId());
		}catch(Exception e){
			e.printStackTrace();
			logger.debug(">>>HomeController toListPage:redirect error:"+e.getMessage());
			Response.error(e.getMessage());
		}
		String path = (String)getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Map<String,Map<String,Operation>> operMap=(Map<String,Map<String,Operation>>)request.getSession().getAttribute("allOperMap");
		jsonmap.put("operMap",(operMap!=null&&operMap.get(path)!=null)?operMap.get(path):new HashMap<String,Operation>());
		return Response.success(jsonmap);
	}
}