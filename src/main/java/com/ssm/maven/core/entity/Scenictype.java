package com.ssm.maven.core.entity;

import org.springframework.stereotype.Service;

@Service
public class Scenictype {
    private Integer id;


    private String scenictype_name;

    private Integer del_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Scenictype{" +
                "id=" + id +
                ", scenictype_name='" + scenictype_name + '\'' +
                ", del_flag=" + del_flag +
                '}';
    }

    public String getScenictype_name() {
        return scenictype_name;
    }

    public void setScenictype_name(String scenictype_name) {
        this.scenictype_name = scenictype_name;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }
}