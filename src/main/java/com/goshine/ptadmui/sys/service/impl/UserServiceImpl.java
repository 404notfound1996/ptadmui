package com.goshine.ptadmui.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.base.BaseServiceImpl;
import com.goshine.ptadmui.core.consts.Constant;
import com.goshine.ptadmui.core.enums.Enums;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.model.vo.ContextVo;
import com.goshine.ptadmui.core.utils.DateHandler;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.Md5Utils;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.*;
import com.goshine.ptadmui.sys.mapper.*;
import com.goshine.ptadmui.sys.service.UserService;
import com.goshine.ptadmui.sys.vo.AuthVo;
import com.goshine.ptadmui.sys.vo.UserVo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户业务实现类
 * @author goshine
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private OrgUserMapper orgUserMapper;
	@Autowired
	private LogMapper logMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Override
	protected BaseMapper<User> getMapper(){
		return userMapper;
	}
    /**
     * 新增
     */
	@Override
	public int insert(Context context, User model) throws AdmuiException {
		if(model==null){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	model.setId(IDCreater.genetateKey());
        	if(!StringHandler.isNullOrEmpty(model.getPassword())){
        		model.setPassword(Md5Utils.encrypt(model.getPassword()));
        	}
        	model.setCreateUser(context.getUserId());
        	model.setCreateDate(context.getCurrentDateTime());
        	model.setModifyUser(context.getUserId());
        	model.setModifyDate(context.getCurrentDateTime());
        	int result=userMapper.insert(model);
        	//保存用户机构关系
        	if(result>0&&model.getOrgIds()!=null&&!model.getOrgIds().isEmpty()){
        		orgUserMapper.batchInsertWithOrg(model.getId(),model.getOrgIds());
        	}
        	//保存用户角色关系
        	if(result>0&&model.getRoleIds()!=null&&!model.getRoleIds().isEmpty()){
        		userRoleMapper.batchInsertWithRole(model.getId(),model.getRoleIds());
        	}
        	return result;
        }
        return -1;
	}
    /**
     * 更新
     */
	@Override
	public int update(Context context, User model) throws AdmuiException {
		if(model==null|| StringHandler.isNullOrEmpty(model.getId())){
    		throw new AdmuiException("参数异常！");
    	}
    	//字段合法性校验
    	boolean isSuccess=this.validateModel(model);
        if(isSuccess){
        	if(!StringHandler.isNullOrEmpty(model.getPassword())){
        		model.setPassword(Md5Utils.encrypt(model.getPassword()));
        	}else{
        		model.setPassword(null);
        	}
        	model.setModifyUser(context.getUserId());
        	model.setModifyDate(context.getCurrentDateTime());
        	int result=userMapper.update(model);
        	//保存用户机构关系
        	if(result>0&&model.getOrgIds()!=null&&!model.getOrgIds().isEmpty()){
        		//删除之前用户机构关系
        		Map<String,Object> params=new HashMap<String,Object>();
        		params.put("userId",model.getId());
        		orgUserMapper.deleteByParams(params);
        		//重新新增
        		orgUserMapper.batchInsertWithOrg(model.getId(),model.getOrgIds());
        	}
        	//保存用户角色关系
        	if(result>0&&model.getRoleIds()!=null&&!model.getRoleIds().isEmpty()){
        		//删除之前用户角色关系
        		Map<String,Object> params=new HashMap<String,Object>();
        		params.put("userId",model.getId());
        		userRoleMapper.deleteByParams(params);
        		//重新新增
        		userRoleMapper.batchInsertWithRole(model.getId(),model.getRoleIds());
        	}
        	return result;
        }
        return -1;
	}
	/**
	 * 公用多余方法
     * 批量删除
     */
	@Override
	public int deleteByIds(Context context, List<String> idList) throws AdmuiException {
         return 0;
	}
    /**
     * 根据用戶ID批量删除用戶
     */
	@Override
	public int deleteByIds(Context context, String type, String orgId, List<String> idList) throws AdmuiException {
		if(idList==null||idList.isEmpty()){
    		throw new AdmuiException("请先选择删除项！");
    	}
		int result=0;
		if("R".equals(type)){
			//校验用户是否有多余关系，假如没有，则不允许解除
			for(String userId:idList){
				List<OrgUser> orgList=orgUserMapper.queryOrgListByUserId(userId);
				if(orgList.size()<=1){
					throw new AdmuiException("该用户所属部门唯一，不允许解除关系！");
				}
			}
			Map<String,Object> params=new HashMap<String,Object>();
	    	params.put("orgId",orgId);
			for(String userId:idList){
		    	params.put("userId",userId);
				result+=orgUserMapper.deleteByParams(params);
			}
			return result;
		}else{
			//超级管理员和存在登录日志的用户不允许删除，只能停用和启用
			for(String userId:idList){
				if(Constant.SUPER__ADMIN_CODE.equals(userId)){
					throw new AdmuiException("超级管理员不允许删除！");
				}
				long loginCount=logMapper.queryCountByUserId(userId);
				if(loginCount>0){
					throw new AdmuiException("该账号已使用，不允许删除！");
				}
			}
			Map<String,Object> params=new HashMap<String,Object>();
	    	params.put("ids",idList);
			//params.put("orgId",orgId);
	    	params.put("modifyUser",context.getUserId());
	    	params.put("modifyDate",context.getCurrentDateTime());
	    	return userMapper.deleteByParams(params);	
		}
	}
	/**
	 * 批量停用启用
	 */
	@Override
	public int startOrStop(Context context, int status, List<String> idList) throws AdmuiException {
		if(idList==null||idList.isEmpty()){
    		throw new AdmuiException("请先选择操作项！");
    	}
		//超级管理员和存在登录日志的用户不允许删除，只能停用和启用
		for(String userId:idList){
			if(Constant.SUPER__ADMIN_CODE.equals(userId)&& Enums.DataState.Disable.getIndex()==status){
				throw new AdmuiException("超级管理员不允许禁用！");
			}
		}
		Map<String,Object> params=new HashMap<String,Object>();
    	params.put("ids",idList);
    	params.put("status",status);
    	params.put("modifyUser",context.getUserId());
    	params.put("modifyDate",context.getCurrentDateTime());
    	return userMapper.startOrStopByParams(params);
	}
	/**
	 *根据ID删除记录
	 */
	@Override
	public UserVo queryById(Context context, String id) throws AdmuiException {
		if(StringHandler.isNullOrEmpty(id)){
			throw new AdmuiException("参数异常！");
		}
		UserVo user=userMapper.queryById(id);
		if(user!=null){
			 List<OrgUser> orgList=orgUserMapper.queryOrgListByUserId(user.getId());
			 if(orgList!=null&&orgList.size()>0){
				 List<String> orgIdList=new ArrayList<String>();
				 for(OrgUser org:orgList){
					 orgIdList.add(org.getOrgId());
				 }
				 user.setOrgIds(orgIdList);
			 }
			 user.setOrgs(orgList);
			 List<Role> roleList=roleMapper.queryRoleListByUserId(Enums.RoleType.FunctionRole.getIndex(),user.getId());
			 if(roleList!=null&&roleList.size()>0){
				 for(Role role:roleList){
					 List<String> roleIds=new ArrayList<String>();
					 roleIds.add(role.getId());
					 List<Permission> permissionList=permissionMapper.queryPermissionListByRoleIds(roleIds);
					 role.setPermissions(permissionList);
				 }
			 }
			 user.setRoles(roleList);
		}
		return user;
	}

   /**
    * 根据用户名查询用户信息
    * @param userName 用户名
    * @return
    */
   @Override
	public UserVo queryUserByUserName(String userName){
		 if(StringHandler.isNullOrEmpty(userName)){
			return null;
		 }
		 List<UserVo> userList=userMapper.queryByUserName(userName);
		 if(userList!=null&&userList.size()>0){
			 UserVo user=userList.get(0);
			 List<Role> roleList=roleMapper.queryRoleListByUserId(Enums.RoleType.FunctionRole.getIndex(),user.getId());
			 if(roleList!=null&&roleList.size()>0){
				 for(Role role:roleList){
					 List<String> roleIds=new ArrayList<String>();
					 roleIds.add(role.getId());
					 List<Permission> permissionList=permissionMapper.queryPermissionListByRoleIds(roleIds);
					 role.setPermissions(permissionList);
				 }
			 }
			 user.setRoles(roleList);
			 return user;
		 }
		 return null;
	}
	/**
	 * 根据条件查询用户信息集合
	 */
	@Override
	public List<UserVo> queryListByCond(Context context, UserVo model) throws AdmuiException {
		if(model==null){
    		model=new UserVo();
    	}
    	return userMapper.queryListByCond(model);
	}
	
    /**
     * 分页查询用户信息列表
     */
	@Override
	public Page<UserVo> queryListByPage(Context context, Page<UserVo> page) throws AdmuiException {
		PageHelper.startPage(page.getPageIndex(),page.getPageSize()," sys_user.create_date desc");
		List<UserVo> pageList=userMapper.queryListByPage(page);
		if(pageList!=null&&pageList.size()>0){
			for(UserVo info:pageList){
				info.setSexName(Enums.SexType.getName(info.getSex()));
				List<OrgUser> orgList=orgUserMapper.queryOrgListByUserId(info.getId());
				 if(orgList!=null&&orgList.size()>0){
					 String orgNames="";//逗号分隔的结构名称
					 for(OrgUser org:orgList){
						 if(orgNames.length()>0){
							 orgNames+=",";
						 }
						 orgNames+=org.getOrgName();
					 }
					 info.setOrgNames(StringHandler.isNullOrEmpty(orgNames)?"":orgNames);
				 }
				List<Role> roleList=roleMapper.queryRoleListByUserId(Enums.RoleType.FunctionRole.getIndex(),info.getId());
				if(roleList!=null&&roleList.size()>0){
					String roleNames="";
					for(Role role:roleList){
						if(roleNames.length()>0){
							roleNames+=",";
						}
						roleNames+=role.getRoleName();
					}
					info.setRoleNames(StringHandler.isNullOrEmpty(roleNames)?"":roleNames);
				}
			}
		}
		PageInfo<UserVo> pageInfo = new PageInfo<UserVo>(pageList);
		return page.generatePage(page,pageInfo);
	}
	/**
	 * 用户分配角色
	 */
	@Override
	@Transactional
	public int assignRole(Context context, AuthVo model) throws AdmuiException {
		if(model==null|| StringHandler.isNullOrEmpty(model.getMasterId())||model.getItemIds()==null||model.getItemIds().isEmpty()){
			throw new AdmuiException("参数异常！");
		}
		//保存之前先删除原数据
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId",model.getMasterId());
		int result=userRoleMapper.deleteByParams(params);
		if(result>0){
			result=userRoleMapper.batchInsertWithRole(model.getMasterId(),model.getItemIds());
			if(result<=0){
				throw new AdmuiException("分配失败！");
			}
		}
		return result;
	}
	
	/**
     * 批量修改用户部门关系
     * @param context
     * @param model
     * @return
     * @throws AdmuiException
     */
	@Override
	@Transactional
    public int updateUserDept(Context context, List<String> orgIds, List<String> userIds) throws AdmuiException {
		if(userIds==null||userIds.size()<=0){
			throw new AdmuiException("请先选择要修改部门的成员！");
		}
		int result=0;
		//过滤集合中的空值
		List<String> orgIdList=new ArrayList<String>();
		List<String> userIdList=new ArrayList<String>();
		if(orgIds!=null&&orgIds.size()>0){
			for(String orgId:orgIds){
				if(!StringHandler.isNullOrEmpty(orgId)){
					orgIdList.add(orgId);
				}
			}
		}
		if(userIds!=null&&userIds.size()>0){
			for(String userId:userIds){
				if(!StringHandler.isNullOrEmpty(userId)){
					userIdList.add(userId);
				}
			}
		}
		if(orgIdList==null||orgIdList.size()<=0){
			throw new AdmuiException("部门信息不能为空，请至少选择一个部门！");
		}
		if(userIdList==null||userIdList.size()<=0){
			throw new AdmuiException("请先选择要修改部门的成员！");
		}
    	//删除之前用户机构关系
		Map<String,Object> params=new HashMap<String,Object>();
    	for(String userId:userIdList){
    		params.put("userId",userId);
    		result+=orgUserMapper.deleteByParams(params);
    		//重新新增
    		if(orgIdList.size()>0){
    		    result+=orgUserMapper.batchInsertWithOrg(userId,orgIdList);
    		}
    	}
        return result;
    }

	@Override
	/**
	 *登录成功后修改用户表中的最后登录时间和登录IP
	 */
	public void updateLoginInfo(Context context, String ip){
		try{
			UserVo model=this.queryById(context,context.getUserId());
			if(model!=null){
				model.setPassword(null);
				model.setLastLoginIp(ip);
				model.setLastLoginTime(context.getCurrentDateTime());
				if(model.getLoginCount()==null){
					model.setLoginCount(new Long(0));
				}else{
					model.setLoginCount(model.getLoginCount()+1);
				}
				this.updateLoginInfo(context,model);
			}else{
				System.out.println(">>>updateLoginInfo error:user is null!");
			}
		}catch (AdmuiException e){
			System.out.println(">>>updateLoginInfo error:"+e.getMessage());
		}
	}

	@Override
	public ContextVo loginValidate(String userName, String host) {
		return loginValidate(userName, host, null,false);
	}

	@Override
	public ContextVo loginValidate(String userName, String host, String password) {
		return loginValidate(userName, host, password,true);
	}
	/**
	 * 登录验证
	 * @param userName 登录用户名
	 * @param host 服务器
	 * @param password 输入密码
	 * @param  isCheckPasswd 是否进行密码校验
	 */
	private ContextVo loginValidate(String userName, String host, String password, boolean isCheckPasswd) {
		UserVo user=this.queryUserByUserName(userName);
		if(user==null){
			throw new UnknownAccountException("账号输入有误！");
		}
		if(isCheckPasswd && !password.equals(user.getPassword())){
			throw new IncorrectCredentialsException("用户名密码错误");
		}
		if(Enums.DataState.Enable.getIndex()!=user.getStatus()){
			throw new LockedAccountException("账号已被禁用,请联系管理员！");
		}
		ContextVo context=new ContextVo();
		List<Menu> menus;
		try{
			context.setUserId(user.getId());
			context.setUserName(user.getUserName());
			context.setName(user.getName());
			context.setExecutiveMode(user.getExecutiveMode());
			context.setLoginCount(user.getLoginCount());
			context.setLastLoginTime(user.getLastLoginTime()!=null? DateHandler.formatDate("yyyy-MM-dd HH:mm:ss",user.getLastLoginTime()): DateHandler.getCurrentTimeString());
			context.setLastLoginIp(user.getLastLoginIp());
			context.setRoles(user.getRoles());
			menus= menuMapper.queryMenuListByAuth(context.getUserId(),null);
			context.setMenuMap(this.buildMenuMap(menus));
			//更新用户登录信息
			this.updateLoginInfo(context,host);
		}catch(Exception e){
			throw new AuthenticationException("登录发生未知异常！");
		}
		return context;
	}

	/**
     * 对象校验
     * @param model
     * @throws AdmuiException
     */
    private boolean validateModel(User model) throws AdmuiException {
    	//合法性校验
    	if(StringHandler.isNullOrEmpty(model.getUserName())){
    		throw new AdmuiException("登录账号不能为空！");
    	}
    	if(StringHandler.empty(model.getUserName()).length()>40){
    		throw new AdmuiException("登录账号长度不能超过40字符！");
    	}
    	if(StringHandler.isNullOrEmpty(model.getId())){
    		if(StringHandler.isNullOrEmpty(model.getPassword())){
    			throw new AdmuiException("密码不能为空！");
    		}
        	if(StringHandler.empty(model.getPassword()).length()<6){
        		throw new AdmuiException("密码长度不能少于6字符！");
        	}
    	}
    	if(StringHandler.isNullOrEmpty(model.getName())){
    		throw new AdmuiException("姓名不能为空！");
    	}
    	if(StringHandler.empty(model.getName()).length()>60){
    		throw new AdmuiException("姓名长度不能超过60字符！");
    	}
    	if(StringHandler.isNullOrEmpty(model.getJobNum())){
    		throw new AdmuiException("工号不能为空！");
    	}
    	if(StringHandler.empty(model.getName()).length()>20){
    		throw new AdmuiException("工号长度不能超过20字符！");
    	}
//    	if(model.getOrgIds()==null||model.getOrgIds().isEmpty()){
//    		throw new AdmuiException("所属部门不能为空！");
//    	}
    	List<User> list=userMapper.checkUserInfo(model);
		if(list!=null&&list.size()>0){
			throw new AdmuiException("登录账号已存在，请确认后重新录入！");
		}
    	return true;
    }
    /**
     * 更新登录信息
     * @param context
     * @param model
     * @return
     * @throws AdmuiException
     */
    @Override
    public int updateLoginInfo(Context context, User model) throws AdmuiException {
    	 if(context==null||model==null|| StringHandler.isNullOrEmpty(model.getId())){
    		 throw new AdmuiException("参数异常！");
    	 }
     	 return userMapper.updateLoginInfo(model);
    }
    /**
     * 修改密码
     * @param context
     * @param password 明文密码
     * @return
     * @throws AdmuiException
     */
	@Override
	public int updatePassword(Context context, String password) throws AdmuiException {
		if(context==null|| StringHandler.isNullOrEmpty(password)){
			logger.debug("UserServiceImpl updatePassword error:password is null!");
			throw new AdmuiException("参数异常！");
		}
		password= Md5Utils.encrypt(password);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId",context.getUserId());
		params.put("password",password);
		params.put("modifyUser",context.getUserId());
		params.put("modifyDate",context.getCurrentDateTime());
		return userMapper.updatePasswordByParams(params);
	}
	/**
    * 根据角色ID查询用户列表
    * @param context
    * @param roleId
    * @return
    * @throws AdmuiException
    */
    public List<UserVo> queryUserListByRoleId(Context context, String roleId) throws AdmuiException {
    	if(StringHandler.isNullOrEmpty(roleId)){
    		throw new AdmuiException("参数异常！");
    	}
    	return userMapper.queryUserListByRoleId(roleId);
    }
	/**
	 * 转换菜单为Key为路径的Map集合
	 * @param menuList
	 * @return
	 */
	private Map<String,String> buildMenuMap(List<Menu> menuList) {
		Map<String,String> menuMap = new HashMap<String,String>();
		for(Menu menu : menuList){
			if(!StringHandler.isNullOrEmpty(menu.getUrl())){
				menuMap.put(menu.getUrl(),menu.getLayer());
			}
		}
		return menuMap;
	}
}
