package com.goshine.ptadmui.sys.vo.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 前端系统菜单
 * @author litao
 * @date 2019-11-12
 */
@ApiModel(value = "frontMenu",description = "前端系统菜单")
@Data
public class FrontMenu {
    @ApiModelProperty(value = "导航栏")
    private List<NavigationBar> navigationBars;
    @ApiModelProperty(value = "侧边栏")
    private List<Sidebar>	sidebars;
}
