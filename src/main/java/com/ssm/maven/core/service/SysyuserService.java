package com.ssm.maven.core.service;

import com.ssm.maven.core.entity.SysUser;

public interface SysyuserService {
    /**
     * @param user
     * @return
     */
    public SysUser login(SysUser user);

    /**
     * 查找用户信息
     */
    public SysUser getUserInfo(String account);

    /**
     * 用户信息修改
     */
    public void updateUserInfo(SysUser sysUser);
}
