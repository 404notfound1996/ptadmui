package com.goshine.ptadmui.sys.service;

import com.goshine.ptadmui.core.base.BaseService;
import com.goshine.ptadmui.core.exception.AdmuiException;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.core.model.vo.ContextVo;
import com.goshine.ptadmui.sys.entity.User;
import com.goshine.ptadmui.sys.vo.AuthVo;
import com.goshine.ptadmui.sys.vo.UserVo;

import java.util.List;

/**
 * 系统用户Service
 * @author goshine
 */
public interface UserService extends BaseService<User> {
	/**
	 * 根据机构ID批量删除成员
	 * @param context
	 * @param orgId
	 * @param idList
	 * @return
	 * @throws AdmuiException
	 */
	public int deleteByIds(Context context, String type, String orgId, List<String> idList) throws AdmuiException;
	/**
	 * 启用or停用用户
	 * @param context
	 * @param ids
	 * @return
	 * @throws AdmuiException
	 */
	public int startOrStop(Context context, int status, List<String> ids) throws AdmuiException;
    /**
     * 根据id查询
     * @param id
     * @return
     */
   public UserVo queryById(Context context, String id) throws AdmuiException;
   /**
    * 根据用户名查询用户信息
    * @param 用户名
    * @return
    */
   public UserVo queryUserByUserName(String userName);
    /**
     * 条件查询
     * @param record
     * @return
     */
   public List<UserVo> queryListByCond(Context context, UserVo record) throws AdmuiException;
    /**
     * 分页条件查询
     * @param record
     * @return
     */
   public Page<UserVo> queryListByPage(Context context, Page<UserVo> page) throws AdmuiException;
   /**
    * 用户分配角色
    * @param context
    * @param model
    * @return
    * @throws AdmuiException
    */
   public int assignRole(Context context, AuthVo model) throws AdmuiException;
   /**
    * 更新登录信息
    * @param context
    * @param model
    * @return
    * @throws AdmuiException
    */
   public int updateLoginInfo(Context context, User model) throws AdmuiException;
   /**
    * 修改密码
    * @param context
    * @param password 明文密码
    * @return
    * @throws AdmuiException
    */
   public int updatePassword(Context context, String password) throws AdmuiException;
   /**
    * 根据角色ID查询用户列表
    * @param context
    * @param roleId
    * @return
    * @throws AdmuiException
    */
    public List<UserVo> queryUserListByRoleId(Context context, String roleId) throws AdmuiException;
    /**
     * 批量修改用户部门关系
     * @param context
     * @param model
     * @return
     * @throws AdmuiException
     */
    public int updateUserDept(Context context, List<String> orgIds, List<String> userIds) throws AdmuiException;
    /**
     *登录成功后修改用户表中的最后登录时间和登录IP
     * @param context 用户相关信息
     * @param ip 用户登录ip
     */
    public void updateLoginInfo(Context context, String ip);

    /**
     * 登录验证
     * @param userName 登录用户名
     * @param host 服务器
     */
    public ContextVo loginValidate(String userName, String host);
    /**
     * 登录验证
     * @param userName 登录用户名
     * @param host 服务器
     * @param password 输入密码
     */
    public ContextVo loginValidate(String userName, String host, String password);


}
