package com.goshine.ptadmui.sys.entity;

import java.io.Serializable;
/**
 * 权限操作关系对象
 * @author goshine
 */
@SuppressWarnings("serial")
public class PermissionOperation implements Serializable{
	/**
	 *权限ID
	 */
    private String permissionid;
    /**
     * 操作ID
     */
    private String operationid;

    public String getPermissionid() {
        return permissionid;
    }
    public void setPermissionid(String permissionid) {
        this.permissionid = permissionid == null ? null : permissionid.trim();
    }

    public String getOperationid() {
        return operationid;
    }
    public void setOperationid(String operationid) {
        this.operationid = operationid == null ? null : operationid.trim();
    }
}