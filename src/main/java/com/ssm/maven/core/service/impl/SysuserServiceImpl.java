package com.ssm.maven.core.service.impl;

import com.ssm.maven.core.dao.SysUserDao;
import com.ssm.maven.core.entity.SysUser;
import com.ssm.maven.core.service.SysyuserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sysuserservice")
public class SysuserServiceImpl implements SysyuserService {

    @Resource
    private SysUserDao sysuserDao;

    @Override
    public SysUser login(SysUser user) {
        return sysuserDao.login(user);
    }

    @Override
    public SysUser getUserInfo(String account) {
        return sysuserDao.selectUserInfo(account);
    }

    @Override
    public void updateUserInfo(SysUser sysUser) {
        sysuserDao.updateUserInfo(sysUser);
    }


}
