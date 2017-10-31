package com.ssm.maven.core.entity;

import org.springframework.stereotype.Service;

@Service
public class Menu {
    private Integer id;

    private String title;

    private String icon;

    private String url;

    private String spread;

    private Integer leavel;

    private Integer parent_id;

    private Integer del_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public Integer getLeavel() {
        return leavel;
    }

    public void setLeavel(Integer leavel) {
        this.leavel = leavel;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }
}