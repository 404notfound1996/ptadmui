package com.goshine.ptadmui.sys.vo.menu;

import com.goshine.ptadmui.sys.entity.Operation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 导航栏菜单
 * @author litao
 * @date 2019-11-12
 */
@ApiModel(value = "navigationBar",description = "导航栏菜单")
@Data
public class NavigationBar {
    @ApiModelProperty(value = "菜单ID")
    private String menuId;
    @ApiModelProperty(value = "父菜单ID")
    private String parentId;
    @ApiModelProperty(value = "路径")
    private String path;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "侧栏收缩状态")
    private boolean hideSider;
    @ApiModelProperty(value = "模块名称")
    private String name;
    @ApiModelProperty(value = "子菜单")
    private List<NavigationBar> children;
    private String layer;
}
