package com.goshine.ptadmui.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.goshine.ptadmui.sys.vo.user.StatusVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.utils.Md5Utils;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.User;
import com.goshine.ptadmui.sys.service.MessageService;
import com.goshine.ptadmui.sys.service.UserService;
import com.goshine.ptadmui.sys.vo.AuthVo;
import com.goshine.ptadmui.sys.vo.ManyToMany;
import com.goshine.ptadmui.sys.vo.OneToMany;
import com.goshine.ptadmui.sys.vo.PasswordVo;
import com.goshine.ptadmui.sys.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 用户Controller
 * @author goshine
 */
@Controller
@RequestMapping(value="/user")
@Api("用户Controller")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	
	@ApiOperation(value="用户注册", notes="新增用户",produces = "application/json")
	@RequestMapping(method= RequestMethod.POST)
	@ResponseBody
	public Response<User> saveUser(HttpServletRequest request,@RequestBody User model){
		try{
		   if(model==null){
			   return Response.error("参数异常！");
		   }
		   int result=-1;
		   Context context=this.getContext();
		   if(!StringHandler.isNullOrEmpty(model.getId())){
			   result=userService.update(context,model);
			   //消息推送
			   if(result>0){
				   messageService.sendMsg(context,model.getId(),"用户信息变更","您的用户信息已发生变更，如有疑问请与管理员联系！");
			   }
		   }else{
			   result=userService.insert(context,model);
		   }
		   return result>0?Response.success(model,"保存成功！"):Response.error("保存失败！");
		}catch(AdmuiException ae){
			logger.debug(">>> 保存用户信息异常，error:"+ae.getMessage());
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug(">>> 保存用户信息异常，error:"+e.getMessage());
			return Response.error("保存用户信息异常！");
		}
	}
	
	@ApiOperation(value="删除用户或者删除用户机构关系", notes="删除用户或者删除用户机构关系",produces = "application/json")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name = "type", value = "删除类型，U：删除用户，R：删除关系", paramType="path", required=true, dataType = "String"),
    	 @ApiImplicitParam(name = "model", value = "关系对象，其中masterId为机构ID，itemIds为用户ID集合",required=true, dataType = "OneToMany")
    })
	@RequestMapping(value="{type}",method= RequestMethod.DELETE)
	@ResponseBody
	public Response<User> deleteUser(HttpServletRequest request,@PathVariable("type") String type,@RequestBody OneToMany model){
		try{
			if(model==null){
			   return Response.error("参数异常！");
		   }
			if("R".equals(type)&&StringHandler.isNullOrEmpty(model.getMasterId())){
				return Response.error("参数MasterId异常！");
			}
		   if(model.getItemIds()==null||model.getItemIds().isEmpty()){
			   return Response.error("请先选择删除项！");
		   }
		   Context context=this.getContext();
		   int result=userService.deleteByIds(context,type,model.getMasterId(),model.getItemIds());
		   return result>0?Response.success(null,"删除成功！"):Response.error("删除失败！");
		}catch(AdmuiException ae){
			logger.debug("delete user info error:"+ae.getMessage(),ae);
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug("delete user info error:"+e.getMessage(),e);
			return Response.error("删除用户信息异常！");
		}
	}
	
	@ApiOperation(value="启用或停用用户", notes="启用或停用用户",produces = "application/json")
	@ApiImplicitParam(name = "status", value = "停启用状态，0：启用，1：停用", paramType="path", required=true, dataType = "String")
	@RequestMapping(value="{status}",method= RequestMethod.POST)
	@ResponseBody
	public Response<User> startOrStopUser(HttpServletRequest request, @RequestBody StatusVo statusVo){
		try{
		   if(statusVo.getIds()==null||statusVo.getIds().isEmpty()){
			   return Response.error("请先选择操作项！");
		   }
		   Context context=this.getContext();
		   int result=userService.startOrStop(context,statusVo.getStatus(),statusVo.getIds());
		   return result>0?Response.success(null,statusVo.getStatus()==0?"启用成功！":"禁用成功！"):Response.error(statusVo.getStatus()==0?"启用失败！":"禁用失败！");
		}catch(AdmuiException ae){
			logger.debug("停启用异常，error"+ae.getMessage(),ae);
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug("停启用异常，error"+e.getMessage(),e);
			return Response.error(statusVo.getStatus()==0?"启用用户异常！":"禁用用户异常！");
		}
	}
	
	/**
	 * 根据ID获取用户详情
	 * paramType 有五个可选值:path,query,body,header,form
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id查询用户信息", notes = "查询用户表中某个用户信息",produces = "application/json")
    @ApiImplicitParam(name = "id", value = "用户ID", paramType="path", required=true, dataType = "String")
	@RequestMapping(value = "{id}",method=RequestMethod.GET)
	@ResponseBody
    public Response<UserVo> queryUserById(HttpServletRequest request,@PathVariable("id") String id){
        try{
        	Context context=this.getContext();
        	UserVo model=userService.queryById(context,id);
        	return Response.success(model,"查询成功！");
        }catch(AdmuiException ae){
        	logger.debug("获取用户信息异常"+ae.getMessage(),ae);
        	return Response.error(ae.getMessage());
        }
    }
	
	@ApiOperation(value = "分页查询用户信息列表", notes = "分页查询用户信息列表",produces = "application/json")
    @ApiImplicitParam(name = "page", value = "分页对象", paramType="body", required=true, dataType = "Page")
	@RequestMapping(value = "/users",method=RequestMethod.GET)
	@ResponseBody
    public Response<Page<UserVo>> queryUserListByPage(HttpServletRequest request,Page<UserVo> page){
        try{
        	Context context=this.getContext();
        	page=userService.queryListByPage(context,page);
			return Response.success(page);
        }catch(AdmuiException ae){
        	logger.debug("获取用户信息异常"+ae.getMessage(),ae);
        	Response.error("获取失败");
        }
        return Response.success(page);
    }
	
	@ApiOperation(value="用户添加角色", notes="给某用户批量添加角色",produces = "application/json")
	@RequestMapping(value="/assignrole",method= RequestMethod.POST)
	@ResponseBody
	public Response<String> assignRole(HttpServletRequest request,@RequestBody AuthVo model){
		try{
		   if(model==null||StringHandler.isNullOrEmpty(model.getMasterId())||model.getItemIds()==null||model.getItemIds().isEmpty()){
			   return Response.error("参数异常！");
		   }
		   Context context=this.getContext();
		   int result=userService.assignRole(context,model);
		   return result>0?Response.success(null,"添加成功！"):Response.error("添加失败！");
		}catch(AdmuiException ae){
			logger.debug(">>> 用户添加角色异常，error:"+ae.getMessage());
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug(">>> 用户添加角色异常，error:"+e.getMessage());
			return Response.error("用户添加角色异常！");
		}
	}
	
	@ApiOperation(value="校验原密码是否正确", notes="校验原用户密码是否正确",produces = "application/json")
	@RequestMapping(value="/checkpassword",method= RequestMethod.POST)
	@ResponseBody
	public Response<String> checkPassword(HttpServletRequest request,@RequestBody PasswordVo model){
		try{
		   if(model==null){
			   return Response.error("参数异常！");
		   }
		   if(StringHandler.isNullOrEmpty(model.getOldPassword())){
			   return Response.error("旧密码不能为空！");
		   }
		   Context context=this.getContext();
		   UserVo user=userService.queryById(context,context.getUserId());
		   String oldPassword=Md5Utils.encrypt(model.getOldPassword());
		   if(user!=null&&!oldPassword.equals(user.getPassword())){
			   return Response.error("原密码输入有误！");
		   }
		   return Response.success(null,"原密码输入无误！");
		}catch(AdmuiException ae){
			logger.debug(">>> 校验密码异常，error:"+ae.getMessage());
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug(">>> 校验密码异常，error:"+e.getMessage());
			return Response.error("校验密码异常！");
		}
	}
	
	@ApiOperation(value="批量修改用户部门", notes="批量修改用户部门",produces = "application/json")
    @ApiImplicitParam(name = "model", value = "关系对象，其中masterIds为机构ID集合，itemIds为用户ID集合",required=true, dataType = "ManyToMany")
	@RequestMapping(value="/updateuserdept",method= RequestMethod.POST)
	@ResponseBody
	public Response<User> updateUserDept(HttpServletRequest request,@RequestBody ManyToMany model){
		try{
			if(model==null){
			   return Response.error("参数异常！");
		   }
		   Context context=this.getContext();
			//MasterIds部门ID集合 ItemIds用户ID集合
		   userService.updateUserDept(context,model.getMasterIds(),model.getItemIds());
		   return Response.success(null,"修改成功！");
		}catch(AdmuiException ae){
			return Response.error(ae.getMessage());
		}catch(Exception e){
			return Response.error("修改用户部门信息异常！");
		}
	}
	
	@ApiOperation(value="重置密码", notes="重置用户密码",produces = "application/json")
	@RequestMapping(value="/changepassword",method= RequestMethod.POST)
	@ResponseBody
	public Response<String> changePassword(HttpServletRequest request,@RequestBody PasswordVo model){
		try{
		   if(model==null){
			   return Response.error("参数异常！");
		   }
		   if(StringHandler.isNullOrEmpty(model.getOldPassword())){
			   return Response.error("旧密码不能为空！");
		   }
		   Context context=this.getContext();
		   UserVo user=userService.queryById(context,context.getUserId());
		   String oldPassword=Md5Utils.encrypt(model.getOldPassword());
		   if(user!=null&&!oldPassword.equals(user.getPassword())){
			   return Response.error("旧密码输入不正确！");
		   }
		   if(StringHandler.isNullOrEmpty(model.getNewPassword())){
			   return Response.error("新密码不能为空！");
		   }
		   if(model.getNewPassword().length()<6){
			   return Response.error("新密码不能少于6个字符！");
		   }
		   if(model.getOldPassword().equals(model.getNewPassword())){
			   return Response.error("新旧密码不能一致！");
		   }
		   if(!model.getNewPassword().equals(model.getRepPassword())){
			   return Response.error("密码输入不一致！");
		   }
		   int result=userService.updatePassword(context,model.getNewPassword());
		   return result>0?Response.success(null,"重置密码成功！"):Response.error("重置密码失败！");
		}catch(AdmuiException ae){
			logger.debug(">>> 重置密码异常，error:"+ae.getMessage());
			return Response.error(ae.getMessage());
		}catch(Exception e){
			logger.debug(">>> 重置密码异常，error:"+e.getMessage());
			return Response.error("重置密码异常！");
		}
	}
}
