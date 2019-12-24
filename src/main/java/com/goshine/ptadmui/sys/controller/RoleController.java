package com.goshine.ptadmui.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goshine.ptadmui.sys.vo.user.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.consts.Constant;
import com.goshine.ptadmui.core.enums.Enums;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.model.Tree;
import com.goshine.ptadmui.core.model.TreeType;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.service.RoleGroupService;
import com.goshine.ptadmui.sys.service.RoleService;
import com.goshine.ptadmui.sys.service.UserService;
import com.goshine.ptadmui.sys.vo.AuthAssignVo;
import com.goshine.ptadmui.sys.vo.RoleGroupVo;
import com.goshine.ptadmui.sys.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/role")
@Api(value = "/role", description = "角色Controller")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleGroupService roleGroupService;

    @ApiOperation(value = "新增或更新角色", notes = "新增或更新角色，新增时ID为空", produces = "application/json")
    @ApiImplicitParam(name = "role", value = "角色对象", paramType = "body", required = true, dataType = "Role")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<Role> saveRole(HttpServletRequest request, @RequestBody Role model) {
        try {
            if (model == null) {
                return Response.error("参数异常！");
            }
            int result = -1;
            Context context = this.getContext();
            model.setRoleType(Enums.RoleType.FunctionRole.getIndex());
            if (!StringHandler.isNullOrEmpty(model.getId())) {
                result = roleService.update(context, model);
            } else {
                result = roleService.insert(context, model);
            }
            return result > 0 ? Response.success(model, "保存成功！") : Response.error("保存失败！");
        } catch (AdmuiException ae) {
            logger.debug(">>> 保存角色信息异常，error:" + ae.getMessage(), ae);
            return Response.error(ae.getMessage());
        } catch (Exception e) {
            logger.debug(">>> 保存角色信息异常，error:" + e.getMessage(), e);
            return Response.error("保存角色信息异常！");
        }
    }

    @ApiOperation(value = "删除角色", notes = "删除角色", produces = "application/json")
    @ApiImplicitParam(name = "id", value = "角色ID", paramType = "path", required = true, dataType = "String")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response<Role> deleteRole(HttpServletRequest request, @PathVariable("id") String id) {
        try {
            if (StringHandler.isNullOrEmpty(id)) {
                return Response.error("请先选择删除项！");
            }
            Context context = this.getContext();
            List<String> idList = new ArrayList<String>();
            idList.add(id);
            int result = roleService.deleteByIds(context, idList);
            return result > 0 ? Response.success(null, "删除成功！") : Response.error("删除失败！");
        } catch (AdmuiException ae) {
            logger.debug("删除角色信息异常,error:" + ae.getMessage(), ae);
            if ("warning".equals(ae.getMessage())) {
                return Response.warn("该角色下尚存在登录用户，不允许删除，请移除关系后再进行删除操作！");
            }
            return Response.error(ae.getMessage());
        } catch (Exception e) {
            logger.debug("删除角色信息异常,error:" + e.getMessage(), e);
            return Response.error("删除角色信息异常！");
        }
    }

    @ApiOperation(value = "根据id查询角色信息", notes = "查询角色表中某个角色信息", produces = "application/json")
    @ApiImplicitParam(name = "id", value = "角色ID", paramType = "path", required = true, dataType = "String")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Role> queryRoleById(HttpServletRequest request, @PathVariable("id") String id) {
        try {
            Context context = this.getContext();
            Role model = roleService.queryById(context, id);
            return Response.success(model, "查询成功！");
        } catch (AdmuiException ae) {
            logger.debug("获取角色信息异常" + ae.getMessage(), ae);
            return Response.error(ae.getMessage());
        }
    }

    @ApiOperation(value = "角色分配用户", notes = "给某角色批量分配用户", produces = "application/json")
    @RequestMapping(value = "/assignuser", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> assignUser(HttpServletRequest request, @RequestBody AuthAssignVo model) {
        try {
            if (model == null || StringHandler.isNullOrEmpty(model.getMasterId()) || model.getItemIds() == null || model.getItemIds().isEmpty()) {
                return Response.error("参数异常！");
            }
            Context context = this.getContext();
            roleService.assignUser(context, model);
            return Response.success(null, "分配成功！");
        } catch (AdmuiException ae) {
            logger.debug(">>> 角色分配用户异常，error:" + ae.getMessage());
            return Response.error(ae.getMessage());
        } catch (Exception e) {
            logger.debug(">>> 角色分配用户异常，error:" + e.getMessage());
            return Response.error("角色分配用户异常！");
        }
    }

    @ApiOperation(value = "删除角色用户关系", notes = "批量移除某角色下的用户关系", produces = "application/json")
    @RequestMapping(value = "/userrole/{roleId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response<String> deleteUserRole(HttpServletRequest request, @RequestBody UserRoleVo userRoleVo) {
        try {
            if (StringHandler.isNullOrEmpty(userRoleVo.getId()) || userRoleVo.getUserIds() == null || userRoleVo.getUserIds().size() <= 0) {
                return Response.error("参数异常！");
            }
            int result = roleService.deleteUserRole(userRoleVo.getId(), userRoleVo.getUserIds());
            return result > 0 ? Response.success(null, "移除成功！") : Response.error("移除失败！");
        } catch (AdmuiException ae) {
            logger.debug(">>> deleteUserRole error:" + ae.getMessage(), ae);
            return Response.error(ae.getMessage());
        } catch (Exception e) {
            logger.debug(">>> deleteUserRole error:" + e.getMessage(), e);
            return Response.error("角色移除用户异常！");
        }
    }

    @ApiOperation(value = "查询角色树", notes = "查询角色树", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "structureType", value = "类型，带RU代表叶子节点带用户信息", required = false, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "名称，角色组或角色的名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "roleId", value = "角色ID,用来判断", required = false, dataType = "String")
    })
    @RequestMapping(value = "/trees", method = RequestMethod.GET)
    @ResponseBody
    public Response<Map<String, Object>> queryRoleTree(HttpServletRequest request, String structureType, String name,String roleId) {
        Context context = this.getContext();
        List<String> list = new ArrayList<String>();
        try{
            List<String> userIdList = roleService.queryUserIdListByRoleId(roleId);
            Map<String,Object>  roleTrees =roleService.queryRoleTrees(structureType,name,context,list,userIdList);
            return Response.success(roleTrees);

        } catch (AdmuiException e) {
            logger.debug("RoleController queryRoleTree error:" + e.getMessage(), e);
            return Response.error("获取角色树异常！");

        }
    }

    /**
     * 角色分配用户中查询已分配用户列表
     * 根据角色ID查询关联的用户列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "根据角色ID查询用户列表", notes = "查询某角色下所有的用户信息", produces = "application/json")
    @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "path", required = true, dataType = "String")
    @RequestMapping(value = "/{roleId}/users", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<UserVo>> queryUserListByRoleId(HttpServletRequest request, @PathVariable("roleId") String id) {
        try {
            List<UserVo> userList = userService.queryUserListByRoleId(this.getContext(), id);
            if (userList == null) {
                userList = new ArrayList<UserVo>();
            }
            return Response.success(userList, String.valueOf(userList.size()));
        } catch (Exception e) {
            logger.error("获取角色对应用户失败！", e);
            return Response.error("获取角色用户失败！");
        }
    }
}