package com.goshine.ptadmui.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.goshine.ptadmui.core.model.Tree;
import com.goshine.ptadmui.core.model.TreeType;
import com.goshine.ptadmui.sys.service.RoleGroupService;
import com.goshine.ptadmui.sys.service.UserService;
import com.goshine.ptadmui.sys.vo.RoleGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.base.BaseServiceImpl;
import com.goshine.ptadmui.core.consts.Constant;
import com.goshine.ptadmui.core.enums.Enums;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.utils.IDCreater;
import com.goshine.ptadmui.core.utils.StringHandler;
import com.goshine.ptadmui.sys.entity.Role;
import com.goshine.ptadmui.sys.entity.RoleData;
import com.goshine.ptadmui.sys.entity.RolePermission;
import com.goshine.ptadmui.sys.mapper.RoleDataMapper;
import com.goshine.ptadmui.sys.mapper.RoleMapper;
import com.goshine.ptadmui.sys.mapper.RolePermissionMapper;
import com.goshine.ptadmui.sys.mapper.UserMapper;
import com.goshine.ptadmui.sys.mapper.UserRoleMapper;
import com.goshine.ptadmui.sys.service.RoleService;
import com.goshine.ptadmui.sys.vo.AuthAssignVo;
import com.goshine.ptadmui.sys.vo.AuthVo;
import com.goshine.ptadmui.sys.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 角色业务实现类
 *
 * @author goshine
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private RoleDataMapper roleDataMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleGroupService roleGroupService;

    @Override
    protected BaseMapper<Role> getMapper() {
        return roleMapper;
    }


    /**
     * 新增角色
     *
     * @param model
     * @return
     */
    @Override
    public int insert(Context context, Role model) throws AdmuiException {
        if (model == null) {
            throw new AdmuiException("参数异常！");
        }
        //字段合法性校验
        boolean isSuccess = this.validateModel(model);
        if (isSuccess) {
            model.setId(IDCreater.genetateKey());
            model.setRoleType(Enums.RoleType.FunctionRole.getIndex());//统一暂存为功能角色
            model.setCreateUser(context.getUserId());
            model.setCreateDate(context.getCurrentDateTime());
            model.setModifyUser(context.getUserId());
            model.setModifyDate(context.getCurrentDateTime());
            int result = roleMapper.insert(model);
            if (result > 0) {
                //保存角色权限关系
                if (model.getPermissionIds() != null && !model.getPermissionIds().isEmpty()) {
                    AuthVo auth = new AuthVo();
                    auth.setMasterId(model.getId());
                    auth.setItemIds(model.getPermissionIds());
                    this.assignPermission(context, auth);
                }
                //保存角色数据关系
                if (model.getDataIds() != null && !model.getDataIds().isEmpty()) {
                    AuthVo auth = new AuthVo();
                    auth.setMasterId(model.getId());
                    auth.setItemIds(model.getDataIds());
                    auth.setItemType("ORG");
                    this.assignData(context, auth);
                }
            }
            return result;
        }
        return -1;
    }

    /**
     * 更新角色
     *
     * @param model
     * @return
     */
    @Override
    public int update(Context context, Role model) throws AdmuiException {
        if (model == null || StringHandler.isNullOrEmpty(model.getId())) {
            throw new AdmuiException("参数异常！");
        }
        //字段合法性校验
        boolean isSuccess = this.validateModel(model);
        if (isSuccess) {
            model.setModifyUser(context.getUserId());
            model.setModifyDate(context.getCurrentDateTime());
            int result = roleMapper.update(model);
            if (result > 0) {
                //假如是超级管理员所属角色，则权限必须包含默认超级管理员的权限，否则自动加入
                if (Constant.ADMIN__ROLE_CODE.equals(model.getId())) {
                    if (!model.getPermissionIds().contains(Constant.ADMIN__PERMISSION_CODE)) {
                        model.getPermissionIds().add(Constant.ADMIN__PERMISSION_CODE);//假如超级管理员权限不存在超级管理员权限，则默认加入
                    }
                }
                //保存新的数据角色和数据关系
                AuthVo auth = new AuthVo();
                auth.setMasterId(model.getId());
                auth.setItemIds(model.getDataIds());
                auth.setItemType("ORG");
                this.assignData(context, auth);
                //保存新的角色权限关系
                auth = new AuthVo();
                auth.setMasterId(model.getId());
                auth.setItemIds(model.getPermissionIds());
                this.assignPermission(context, auth);
            }
            return result;
        }
        return -1;
    }

    /**
     * 根据ID批量删除角色
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public int deleteByIds(Context context, List<String> idList) throws AdmuiException {
        if (idList == null || idList.isEmpty()) {
            throw new AdmuiException("请先选择删除项！");
        }
        for (String roleId : idList) {
            List<UserVo> userList = userMapper.queryUserListByRoleId(roleId);
            if (userList != null && !userList.isEmpty()) {
                throw new AdmuiException("warning");
            }
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", idList);
        params.put("roleIds", idList);
        params.put("modifyUser", context.getUserId());
        params.put("modifyDate", context.getCurrentDateTime());
        //删除角色人员关系
        userRoleMapper.batchDeleteByParams(params);
        //删除角色权限关系
        rolePermissionMapper.batchDeleteByParams(params);
        //删除角色数据关系
        roleDataMapper.batchDeleteByParams(params);
        return roleMapper.deleteByParams(params);
    }

    /**
     * 根据ID查询组织机构信息
     *
     * @param id
     * @return
     */
    @Override
    public Role queryById(Context context, String id) throws AdmuiException {
        if (StringHandler.isNullOrEmpty(id)) {
            throw new AdmuiException("参数异常！");
        }
        Role model = roleMapper.queryById(id);
        if (model != null) {
            List<String> dataIdList = roleDataMapper.queryDataIdListByRoleId(model.getId());
            model.setDataIds(dataIdList != null ? dataIdList : new ArrayList<String>());
			List<String> permissionIdList = rolePermissionMapper.queryByRoleId(model.getId());
			model.setPermissionIds(permissionIdList);
			Map<String,Object>  roleTrees= queryRoleTrees("","",context,model.getDataIds(),null);
			model.setRoleTrees(roleTrees);
        }


        return model;
    }

    /**
     * 根据条件查询组织机构信息列表
     *
     * @param model
     * @return
     */
    @Override
    public List<Role> queryListByCond(Context context, Role model) {
        if (model == null) {
            model = new Role();
        }
        List<Role> roleList = roleMapper.queryListByCond(model);
        if (roleList != null && roleList.size() > 0) {
            for (Role role : roleList) {
                List<String> dataIdList = roleDataMapper.queryDataIdListByRoleId(role.getId());
                role.setDataIds(dataIdList != null ? dataIdList : new ArrayList<String>());
            }

        }
        return roleList;
    }

    /**
     * 分页查询组织机构信息列表
     *
     * @param page
     * @return
     */
    @Override
    public Page<Role> queryListByPage(Context context, Page<Role> page) throws AdmuiException {
        PageHelper.startPage(page.getPageIndex(), page.getPageSize(), " sys_role.role_name desc");
        List<Role> pageList = roleMapper.queryListByPage(page);
        if (pageList != null && pageList.size() > 0) {
            for (Role role : pageList) {
                List<String> dataIdList = roleDataMapper.queryDataIdListByRoleId(role.getId());
                role.setDataIds(dataIdList != null ? dataIdList : new ArrayList<String>());
            }
        }
        PageInfo<Role> pageInfo = new PageInfo<Role>(pageList);
        return page.generatePage(page, pageInfo);
    }

    /**
     * 对象校验
     *
     * @param model
     * @throws //BizException
     */
    private boolean validateModel(Role model) throws AdmuiException {
        //合法性校验
        if (StringHandler.isNullOrEmpty(model.getId()) && StringHandler.isNullOrEmpty(model.getRoleGroupId())) {
            throw new AdmuiException("所属角色组不能为空！");
        }
        if (StringHandler.isNullOrEmpty(model.getRoleName())) {
            throw new AdmuiException("角色名称不能为空！");
        }
        if (StringHandler.empty(model.getRoleName()).length() > 40) {
            throw new AdmuiException("角色名称长度不能超过40字符！");
        }
        return true;
    }

    /**
     * 根据用户ID查询用户所属角色集合
     */
    @Override
    public List<Role> queryRoleListByUserId(int roleType, String userId) {
        if (StringHandler.isNullOrEmpty(userId)) {
            return new ArrayList<Role>();
        }
        return roleMapper.queryRoleListByUserId(roleType, userId);
    }

    /**
     * 角色分配用户
     */
    @Override
    @Transactional
    public int assignUser(Context context, AuthAssignVo model) throws AdmuiException {
        if (model == null || StringHandler.isNullOrEmpty(model.getMasterId()) || model.getItemIds() == null || model.getItemIds().isEmpty()) {
            throw new AdmuiException("参数异常！");
        }
        int result = 0;
        //保存之前先删除原数据
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", model.getMasterId());
        result += userRoleMapper.deleteByParams(params);
        List<String> userIdList = new ArrayList<String>();
        for (String userId : model.getItemIds()) {//去空
            if (!StringHandler.isNullOrEmpty(userId)) {
                userIdList.add(userId);
            }
        }
        if (model.getDeptIds() != null && !model.getDeptIds().isEmpty()) {//假如部门ID集合不为空，则查询该部门下所有成员到人员集合中
            for (String deptId : model.getDeptIds()) {
                List<String> userList = userMapper.queryUserIdListByOrgId(deptId);
                userIdList.addAll(userList);
            }
        }
        if (model.getRoleIds() != null && !model.getRoleIds().isEmpty()) {//假如角色ID集合不为空，则查询该角色下所有成员到人员集合中
            for (String roleId : model.getRoleIds()) {
                List<String> userList = userMapper.queryUserIdListByRoleId(roleId);
                userIdList.addAll(userList);
            }
        }
        //去除重复之后保存角色和用户关系
        if (userIdList != null && userIdList.size() > 0) {
            result += userRoleMapper.batchInsertWithUser(model.getMasterId(), new ArrayList<String>(new HashSet<String>(userIdList)));
        }
        return result;
    }

    /**
     * 角色授权
     *
     * @param //masterId 角色ID
     * @param //itemIds  权限ID集合
     * @return
     */
    @Override
    public int assignPermission(Context context, AuthVo model) throws AdmuiException {
        if (model == null || StringHandler.isNullOrEmpty(model.getMasterId())) {
            throw new AdmuiException("参数异常！");
        }
        //保存之前先删除原数据
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", model.getMasterId());
        int result = rolePermissionMapper.deleteByParams(params);
        List<RolePermission> rpList = new ArrayList<RolePermission>();
        RolePermission rp = null;
        if (model.getItemIds() != null && model.getItemIds().size() > 0) {
            for (String permissionId : model.getItemIds()) {
                rp = new RolePermission();
                rp.setId(IDCreater.genetateKey());
                rp.setRoleId(model.getMasterId());
                rp.setPermissionId(permissionId);
                rpList.add(rp);
            }
        }
        if (rpList.size() > 0) {
            result = rolePermissionMapper.batchInsert(rpList);
        }
        return result;
    }

    /**
     * 解除角色用户关系
     */
    @Override
    public int deleteUserRole(String roleId, List<String> userIds) throws AdmuiException {
        int result = 0;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        for (String userId : userIds) {
            //默认角色
            if (Constant.ADMIN__ROLE_CODE.equals(roleId) && Constant.SUPER__ADMIN_CODE.equals(userId)) {
                throw new AdmuiException("超级管理员和角色关系不允许解除！");
            }
            params.put("userId", userId);
            result += userRoleMapper.deleteByParams(params);
        }
        return result;
    }

    @Override
    public Map<String, Object> queryRoleTrees(String structureType, String name, Context context,List<String> dateIds,List<String> userIdList) throws AdmuiException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<Tree> treeList = new ArrayList<Tree>();//放置角色组树对象
        long total = 0;

        List<RoleGroupVo> roleGroupList = roleGroupService.queryAll(context, name);
        if (roleGroupList != null && roleGroupList.size() > 0) {
            total += roleGroupList.size();//加角色组总数量
            Tree groupTree = null;
            for (RoleGroupVo vo : roleGroupList) {
                groupTree = new Tree();
                groupTree.setExpand(true);
                groupTree.setValue(groupTree.getId());
                if (Constant.DEFAULT_ROLEGROUP_CODE.equals(vo.getId())) {//假如为默认,存放D
                    groupTree.setType(Enums.NodeType.DefaultRoleGroup.getIndex());
                } else {
                    groupTree.setType(Enums.NodeType.RoleGroup.getIndex());
                }
                if ("RU".equals(structureType)) {
                    TreeType type = new TreeType();
                    type.setId(vo.getId());
                    groupTree.setLi_attr(type);
                } else {
                    groupTree.setId(vo.getId());
                }
                groupTree.setSelected(dateIds.contains(groupTree.getId()));//前端要求字段
                groupTree.setTitle(vo.getGroupName());
                Role queryRole = new Role();
                queryRole.setRoleGroupId(vo.getId());
                queryRole.setRoleName(name);
                List<Role> roleList = queryListByCond(context, queryRole);
                List<Tree> roleTreeList = new ArrayList<Tree>();//放置角色树对象
                if (roleList != null && roleList.size() > 0) {
                    total += roleList.size();//加各个角色组下角色的数量
                    Tree roleTree = null;
                    for (Role role : roleList) {
                        roleTree = new Tree();
                        roleTree.setExpand(true);
                        roleTree.setTitle(role.getRoleName());
                        if ("RU".equals(structureType)) {
                            TreeType type = new TreeType();
                            type.setId(role.getId());
                            roleTree.setType(Enums.NodeType.Role.getIndex());
                            roleTree.setLi_attr(type);
                            roleTree.setValue(roleTree.getId());
                            //
                            roleTree.setChecked(dateIds.contains(groupTree.getId()));
                            List<Tree> userTreeList = new ArrayList<Tree>();//放置用户树对象
                            List<UserVo> userList = userService.queryUserListByRoleId(context, role.getId());
                            if (userList != null && userList.size() > 0) {
                                Tree userTree = null;
                                for (UserVo user : userList) {
                                    userTree = new Tree();
                                    userTree.setTitle(user.getName());
                                    userTree.setExpand(true);
                                    userTree.setChecked(userIdList.contains(user.getId()));
                                    TreeType userTreeType = new TreeType();
                                    userTreeType.setId(user.getId());
                                    //

                                    userTree.setValue(userTree.getId());
                                    userTree.setType(Enums.NodeType.Person.getIndex());
                                    userTree.setLi_attr(userTreeType);
                                    userTreeList.add(userTree);
                                }
                            }
                            roleTree.setChildren(userTreeList);
                            roleTree.setExpand(true);
                            roleTree.setValue(role.getId());
                        } else {
                            roleTree.setId(role.getId());
                            roleTree.setValue(role.getId());
                            roleTree.setDataIds(role.getDataIds());//放入数据权限数据集合
                        }
                        roleTree.setChecked(dateIds.contains(groupTree.getId()));
                        roleTreeList.add(roleTree);
                    }
                }
                groupTree.setChildren(roleTreeList);
                groupTree.setExpand(true);
                groupTree.setValue(groupTree.getId());
                treeList.add(groupTree);
            }
        }

        returnMap.put("roleTrees", treeList);
        returnMap.put("total", total);

        return returnMap;
    }
    /**************************************************************************************************
     * 角色数据相关方法
     **************************************************************************************************/
    /**
     * 角色数据授权
     *
     * @param //masterId 角色ID
     * @param //itemIds  数据ID集合
     * @return
     */
    private int assignData(Context context, AuthVo model) throws AdmuiException {
        if (model == null || StringHandler.isNullOrEmpty(model.getMasterId())) {
            throw new AdmuiException("参数异常！");
        }
        //保存之前先删除原数据
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", model.getMasterId());
        int result = roleDataMapper.deleteByParams(params);
        List<RoleData> rpList = new ArrayList<RoleData>();
        RoleData rd = null;
        if (model.getItemIds() != null && model.getItemIds().size() > 0) {
            for (String dataId : model.getItemIds()) {
                rd = new RoleData();
                rd.setId(IDCreater.genetateKey());
                rd.setRoleId(model.getMasterId());
                rd.setDataId(dataId);
                rd.setDataType(model.getItemType());
                rpList.add(rd);
            }
        }
        if (rpList.size() > 0) {
            result = roleDataMapper.batchInsert(rpList);
        }
        return result;
    }

    /**
     * 根据角色ID查询用户ID集合
     * @param roleId
     * @return
     */
    public List<String> queryUserIdListByRoleId(String roleId){
        List<String> userId = roleMapper.queryUserIdListByRoleId(roleId);
        return userId;
    }

}
