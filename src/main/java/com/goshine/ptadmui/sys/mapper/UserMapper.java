package com.goshine.ptadmui.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goshine.ptadmui.core.base.BaseMapper;
import com.goshine.ptadmui.core.model.Page;
import com.goshine.ptadmui.sys.entity.User;
import com.goshine.ptadmui.sys.vo.UserVo;
/**
 * 用户Mapper
 * @author goshine
 */
@Mapper
public interface UserMapper extends BaseMapper<User>{
    /**
     * 根据条件启停用
     * @param id
     * @return
     */
   int startOrStopByParams(Map<String,Object> params);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserVo queryById(String id);
    /**
     * 根据机构ID查询该机构下的用户ID集合
     * @param record
     * @return
     */
    List<String> queryUserIdListByOrgId(@Param("orgId") String orgId);
    /**
     * 根据角色ID查询该机构下的用户ID集合
     * @param record
     * @return
     */
    List<String> queryUserIdListByRoleId(@Param("roleId") String roleId);
    /**
     * 条件查询
     * @param record
     * @return
     */
    List<UserVo> queryListByCond(UserVo model);
    /**
     * 分页条件查询
     * @param record
     * @return
     */
    List<UserVo> queryListByPage(Page<UserVo> page);
    /**
     * 校验用户名唯一性
     * @param model
     * @return
     */
    List<User> checkUserInfo(User model);
    /**
     * 根据用户名查询用户记录
     * @param userName
     * @return
     */
    List<UserVo> queryByUserName(String userName);
    /**
     * 修改密码
     * @param params
     */
    int updatePasswordByParams(Map<String,Object> params);
    /**
     * 根据角色ID查询角色下的用户信息
     * @param roleId
     * @return
     */
	List<UserVo> queryUserListByRoleId(String roleId);
	/**
	 * 更新登录信息
	 * @param model
	 * @return
	 */
	int updateLoginInfo(User model);
}
