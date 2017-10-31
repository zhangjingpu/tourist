package com.ssm.maven.core.service;

import com.ssm.maven.core.entity.Menu;

import java.util.List;

public interface MenuService {
    //查询父菜单类
    public List<Menu> findFatherMenu(Menu menu) throws Exception;

    //查询子菜单类
    public List<Menu> findChildMenu(Menu menu) throws Exception;
}
