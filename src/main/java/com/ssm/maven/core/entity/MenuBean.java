package com.ssm.maven.core.entity;

import java.util.ArrayList;
import java.util.List;


public class MenuBean {

    private String title;//左侧菜单标题

    private String icon;//左侧菜单图标

    private String href;//左侧菜单地址

    private String spread;//左侧菜单是否展开

    private List<MenuBean> children;//子菜单

    @Override
    public String toString() {
        return "MenuBean{" +
                "title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", spread='" + spread + '\'' +
                ", children=" + children +
                '}';
    }

    public List<MenuBean> getChildren() {
        return children;
    }

    public void setChildren(List<MenuBean> children) {
        this.children = children;
    }

    public void addChildren(MenuBean bean) {
        this.children.add(bean);
    }

    public MenuBean(String title, String icon, String href, String spread) {
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
        this.children = new ArrayList<MenuBean>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }
}
