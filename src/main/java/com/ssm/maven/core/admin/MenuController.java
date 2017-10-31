package com.ssm.maven.core.admin;

import com.ssm.maven.core.entity.Menu;
import com.ssm.maven.core.entity.MenuBean;
import com.ssm.maven.core.service.MenuService;
import com.ssm.maven.core.util.MenuUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {

    @Resource
    private MenuService menuService;

    @Resource
    private Menu menu;

    @RequestMapping("getMenu")
    public @ResponseBody
    List<MenuBean> getMenu() throws Exception {
        List<MenuBean> menulist = new ArrayList<MenuBean>();
        List<Menu> a1 = new ArrayList<Menu>();
        List<Menu> a2 = new ArrayList<Menu>();
        a1 = menuService.findFatherMenu(menu);
        a2 = menuService.findChildMenu(menu);
        menulist = MenuUtil.getMenuFormat(a1, a2, menulist);
        return menulist;
    }
}
