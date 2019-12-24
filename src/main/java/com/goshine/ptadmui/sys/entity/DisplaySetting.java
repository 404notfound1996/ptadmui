package com.goshine.ptadmui.sys.entity;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="DisplaySetting", description="显示设置Model")
public class DisplaySetting implements Serializable{
	@ApiModelProperty(value = "显示设置ID")
    private String id;
	@ApiModelProperty(value = "导航栏颜色")
    private String navigationColor;
	@ApiModelProperty(value = "通栏显示")
    private String acrossFlag;
	@ApiModelProperty(value = "菜单主题")
    private String menuTheme;
	@ApiModelProperty(value = "菜单显示")
    private String menuDisplay;
	@ApiModelProperty(value = "收起图标和文字")
    private String menuTxtIcon;
	@ApiModelProperty(value = "主题颜色")
    private String themeColor;
	@ApiModelProperty(value = "系统全局设置")
    private int globalFlag;
	@ApiModelProperty(value = "系统默认设置")
    private int defaultFlag;
	@ApiModelProperty(value = "用户ID")
    private String userId;
	@ApiModelProperty(value = "修改时间")
    private Date modifyDate;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNavigationColor() {
        return navigationColor==null?"":navigationColor;
    }
    public void setNavigationColor(String navigationColor) {
        this.navigationColor = navigationColor == null ?null : navigationColor.trim();
    }

    public String getAcrossFlag() {
        return acrossFlag==null?"":acrossFlag;
    }
    public void setAcrossFlag(String acrossFlag) {
        this.acrossFlag = acrossFlag == null ? null : acrossFlag.trim();
    }

    public String getMenuTheme() {
        return menuTheme==null?"":menuTheme;
    }
    public void setMenuTheme(String menuTheme) {
        this.menuTheme = menuTheme == null ? null : menuTheme.trim();
    }

    public String getMenuDisplay() {
        return menuDisplay==null?"":menuDisplay;
    }

    public void setMenuDisplay(String menuDisplay) {
        this.menuDisplay = menuDisplay == null ? null : menuDisplay.trim();
    }
    public String getMenuTxtIcon() {
        return menuTxtIcon==null?"":menuTxtIcon;
    }

    public void setMenuTxtIcon(String menuTxtIcon) {
        this.menuTxtIcon = menuTxtIcon == null ? null : menuTxtIcon.trim();
    }

    public String getThemeColor() {
        return themeColor==null?"":themeColor;
    }
    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor == null ? null : themeColor.trim();
    }

    public int getGlobalFlag() {
        return globalFlag;
    }
    public void setGlobalFlag(int globalFlag) {
        this.globalFlag = globalFlag;
    }

    public int getDefaultFlag() {
        return defaultFlag;
    }
    public void setDefaultFlag(int defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}