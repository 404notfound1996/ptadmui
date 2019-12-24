package com.goshine.ptadmui.sys.vo.user;

import lombok.Data;

import java.util.List;

/**
 * 用户启用状态VO
 * @Author: zhangjie
 * @Date: 2019/11/27 16:29
 */
@Data
public class StatusVo {
    private int status;
    private List<String> ids;
}