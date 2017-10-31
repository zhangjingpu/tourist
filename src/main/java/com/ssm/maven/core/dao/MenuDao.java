package com.ssm.maven.core.dao;

import com.ssm.maven.core.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {

    //查询父菜单类
    public List<Menu> findFatherMenu(Menu menu) throws Exception;

    //查询子菜单类
    public List<Menu> findChildMenu(Menu menu) throws Exception;

}
