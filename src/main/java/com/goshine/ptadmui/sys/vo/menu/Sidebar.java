package com.goshine.ptadmui.sys.vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 侧边栏
 * @author litao
 * @date 2019-11-12
 */
@ApiModel(value = "Sidebar",description = "侧边栏")
@Data
public class Sidebar {
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
    @ApiModelProperty(value = "头部模块名称")
    private String header;
    private List<Sidebar> children;
}
