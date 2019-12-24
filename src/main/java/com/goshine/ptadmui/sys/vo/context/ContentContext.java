package com.goshine.ptadmui.sys.vo.context;

import com.goshine.ptadmui.sys.entity.Operation;
import com.goshine.ptadmui.sys.vo.menu.FrontMenu;
import com.goshine.ptadmui.sys.vo.user.Identity;
import com.goshine.ptadmui.sys.vo.user.LoginSession;
import com.goshine.ptadmui.sys.vo.user.UserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 上下文对象
 * @author litao
 * @date 2019-11-12
 */
@ApiModel(value = "ContentContext",description = "上下文对象")
@Data
public class ContentContext {
    @ApiModelProperty(value = "用户登录信息")
    private Identity user;
    @ApiModelProperty(value = "用户登录信息")
    private LoginSession loginSession;
    @ApiModelProperty(value = "用户菜单信息")
    private FrontMenu frontMenu;
    @ApiModelProperty(value = "用户角色信息")
    private List<UserRole> userRoles;
    @ApiModelProperty(value = "当前用户具有的操作权限集合 key menuid")
    private Map<String,List<Operation>> permissions;
}
