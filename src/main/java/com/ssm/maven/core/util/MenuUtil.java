package com.ssm.maven.core.util;

import com.ssm.maven.core.entity.Menu;
import com.ssm.maven.core.entity.MenuBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
左侧菜单工具类
实现子菜单加入父菜单中
方便菜单Json的实现
 */

public class MenuUtil {

    public static List<MenuBean> getMenuFormat(List<Menu> a1, List<Menu> a2, List<MenuBean> menulist) {

        Map<Integer, MenuBean> fathermenuMap = new HashMap<Integer, MenuBean>();
        for (Menu menu : a1) {
            MenuBean menuBean = new MenuBean(
                    menu.getTitle(), menu.getIcon(), menu.getUrl(), menu.getSpread());
            fathermenuMap.put(menu.getParent_id(), menuBean);
            //System.out.println("父菜单"+menuBean.getTitle()+" "+menu.getParentId());
        }
        /*
        Iterator it = fathermenuMap.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        */

        for (Menu menu : a2) {
            MenuBean menuBean = new MenuBean(
                    menu.getTitle(), menu.getIcon(), menu.getUrl(), menu.getSpread());
            MenuBean fatherMenu = fathermenuMap.get(menu.getParent_id());
            // System.out.println("父菜单"+fatherMenu.getTitle()+"子菜单"+menuBean.getTitle());
            fatherMenu.addChildren(menuBean);
        }

        for (MenuBean menu : fathermenuMap.values()) {
            menulist.add(menu);
        }
        /*
        for (MenuBean a4:menulist)
        {
            System.out.println(a4.getTitle());
        }
        */
        return menulist;
    }

}
