package com.goshine.ptadmui.sys.controller;

import com.goshine.ptadmui.sys.service.RoleService;
import com.goshine.ptadmui.sys.service.impl.RoleServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.goshine.ptadmui.core.base.BaseController;
import com.goshine.ptadmui.core.enums.Enums;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.core.model.Tree;
import com.goshine.ptadmui.core.model.TreeType;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Organization;
import com.goshine.ptadmui.sys.service.OrganizationService;
import com.goshine.ptadmui.sys.service.UserService;
import com.goshine.ptadmui.sys.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 组织机构Controller
 *
 * @author goshine
 */
@Controller
@RequestMapping("/org")
@Api("组织机构Controller")
public class OrganizationController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private OrganizationService orgService;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @ApiOperation(value = "新增或更新组织机构", notes = "保存组织机构，新增时ID为空", produces = "application/json")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<Organization> saveOrganization(HttpServletRequest request, @RequestBody Organization model) {
        try {
            Context context = this.getContext();
            if (model == null) {
                return Response.error("参数异常！");
            }
            int result = 0;
            if (StringHandler.isNullOrEmpty(model.getId())) {
                result = orgService.insert(context, model);
            } else {
                result = orgService.update(context, model);
            }
            return result > 0 ? Response.success(model, "保存成功！") : Response.error("保存失败！");
        } catch (AdmuiException e) {
            return Response.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除组织机构", notes = "删除组织机构，id为组织机构ID", produces = "application/json")
    @ApiImplicitParam(name = "id", value = "机构ID", paramType = "path", required = true, dataType = "String")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response<String> deleteOrganization(HttpServletRequest request, @PathVariable("id") String id) {
        try {
            if (StringHandler.isNullOrEmpty(id)) {
                return Response.error("请先选择删除项！");
            }
            Context context = this.getContext();
            List<String> idList = new ArrayList<String>();
            idList.add(id);
            int result = orgService.deleteByIds(context, idList);
            return result > 0 ? Response.success(null, "删除成功！") : Response.error("删除失败！");
        } catch (AdmuiException e) {
            return Response.error(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id查询组织机构信息", notes = "查询组织机构表中某个机构的信息", produces = "application/json")
    @ApiImplicitParam(name = "id", value = "机构ID", paramType = "path", required = true, dataType = "String")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Organization> queryOrganizationById(HttpServletRequest request, @PathVariable("id") String id) {
        try {
            if (StringHandler.isNullOrEmpty(id)) {
                return Response.error("参数异常！");
            }
            Organization info = orgService.queryById(this.getContext(), id);
            return info != null ? Response.success(info) : Response.error("未找到对应记录！");
        } catch (AdmuiException e) {
            return Response.error(e.getMessage());
        }
    }

    @ApiOperation(value = "分页查询组织机构信息列表", notes = "分页查询组织机构信息列表", produces = "application/json")
    @ApiImplicitParam(name = "page", value = "分页对象", paramType = "body", required = true, dataType = "Page")
    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    @ResponseBody
    public Response<Page<Organization>> queryOrganizationListByPage(HttpServletRequest request, Page<Organization> page) {
        try {
            Context context = this.getContext();
            page = orgService.queryListByPage(context, page);
            return Response.success(page);
        } catch (AdmuiException e) {
            logger.debug("OrganizationController queryOrganzationListByPage:分页查询组织机构信息异常！" + e.getMessage());
            return Response.error("获取失败");
        }

    }

    @ApiOperation(value = "查询组织机构树", notes = "查询组织机构树，structureType为OU时，叶子节点带用户信息", produces = "application/json")
    @ApiImplicitParam(name = "structureType", value = "类型，当为OU时代表叶子节点带用户信息，ONLY代表只有组织机构", required = false, dataType = "String")
    @RequestMapping(value = "/trees", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Tree>> queryOrganizationTree(HttpServletRequest request, String structureType,String roleId) {
        Context context = this.getContext();
        List<String> userIdList = roleService.queryUserIdListByRoleId(roleId);
        List<Tree> treeList = queryTreeList(context, structureType, "0", null,userIdList);
        return Response.success(treeList);
    }

    /**
     * 分页查询部门下（包括子部门）所有的用户信息列表
     * @param request
     * @param page
     * @return
     */
    @RequestMapping(value = "/orgEmp", method = RequestMethod.GET)
    @ResponseBody
    public Response<Page<UserVo>> queryEmpByOrgId(HttpServletRequest request,Page<UserVo> page) {
        Context context = this.getContext();
        page = orgService.queryListByOrgID(context,page);
//        Organization organization = new Organization();
//        organization.setId("15398492599754232159");
//        List<Organization> orgList=new ArrayList<>();
//        orgList.add(organization);
//        List<Tree> treeList = queryTreeList(context, "ONLY", "15398492599754232159",orgList);
//        List<String> idList = treeList.stream().map(Tree::getId).collect(Collectors.toList());
//        Stream<List<Tree>> listStream = treeList.stream().map(Tree::getChildren);
//        Map<String, Object> stringObjectMap = page.getData();
//        stringObjectMap.put("orgIds", idList);
//        page.setData(stringObjectMap);


        return Response.success(page);


    }

    /**
     * 迭代获取树
     *
     * @param parentId
     * @return
     */
    private List<Tree> queryTreeList(Context context, String structureType, String parentId, List<Organization> orgList,List<String> userIdList) {
        List<Tree> treeList = new ArrayList<Tree>();
        try {
            //根据父类ID查询子类菜单
            Organization queryModel = new Organization();
            if (parentId == null || parentId == "0") {
                queryModel.setParentId("0");
                orgList = orgService.queryListByCond(context, queryModel);
            }
            for (Organization model : orgList) {
                Tree tree = new Tree();
                if ("OU".equals(structureType)) {
                    TreeType type = new TreeType();
                    type.setId(model.getId());
                    tree.setType(Enums.NodeType.Company.getIndex());
                    tree.setLi_attr(type);
                    tree.setValue(type.getId());

                } else {
                    tree.setId(model.getId());
                    tree.setValue(tree.getId());

                }
                tree.setValue(tree.getId());
                tree.setExpand(true);
                tree.setTitle(model.getOrgName());
                //根据父类ID查询子机构列表
                queryModel.setParentId(model.getId());
                List<Organization> childOrgList = orgService.queryListByCond(context, queryModel);
                if (childOrgList != null && !childOrgList.isEmpty()) {
                    tree.setChildren(this.queryTreeList(context, structureType, model.getId(), childOrgList,userIdList));
                } else {//当机构末级节点没有子级的时候直接设置末级节点为人员
                    if ("OU".equals(structureType)) {
                        tree.setChildren(this.queryUserTreeNodeList(context, structureType, model.getId(),userIdList));
                    }
                }
                treeList.add(tree);
            }
            if ("OU".equals(structureType)) {//添加同级节点下的人员到树集合
                List<Tree> userNodeList = this.queryUserTreeNodeList(context, structureType, parentId,userIdList);
                treeList.addAll(userNodeList);
            }
        } catch (Exception e) {
            logger.debug("获取机构树异常！" + e.getMessage());
        }
        return treeList;
    }

    /**
     * 迭代获取机构下的人员节点
     *
     * @param
     * @return
     */
    private List<Tree> queryUserTreeNodeList(Context context, String structureType, String orgId,List<String> userIdList) throws AdmuiException {
        List<Tree> treeList = new ArrayList<Tree>();
        UserVo userModel = new UserVo();
        List<String> orgIdList = new ArrayList<String>();
        orgIdList.add(orgId);
        userModel.setOrgIds(orgIdList);
        List<UserVo> userList = userService.queryListByCond(context, userModel);
        if (userList != null && !userList.isEmpty()) {
            for (UserVo user : userList) {
                Tree childTree = new Tree();
                if ("OU".equals(structureType)) {
                    TreeType type = new TreeType();
                    type.setId(user.getId());
                    childTree.setType(Enums.NodeType.Person.getIndex());
                    childTree.setLi_attr(type);
                } else {
                    childTree.setId(user.getId());
                }
                childTree.setTitle(user.getName());
                //判断角色下是否包含用户，是返回True
                childTree.setChecked(userIdList.contains(user.getId()));
                treeList.add(childTree);
            }
        }
        return treeList;
    }
}