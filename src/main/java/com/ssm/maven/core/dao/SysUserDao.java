package com.ssm.maven.core.dao;

import com.ssm.maven.core.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao {
    /**
     * 登录
     *
     * @param user
     * @return
     */
    public SysUser login(SysUser user);

    /**
     * 用户信息查询
     */
    public SysUser selectUserInfo(String account);

    /**
     * 用户信息修改
     */
    public void updateUserInfo(SysUser SysUser);
}
