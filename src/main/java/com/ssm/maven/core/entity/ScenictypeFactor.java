package com.ssm.maven.core.entity;

import org.springframework.stereotype.Service;

@Service
public class ScenictypeFactor {
    private Integer id;

    private Integer scenictype_id;

    private String scenictype_name;

    private String scenictype_value;

    private Integer del_flag;

    @Override
    public String toString() {
        return "ScenictypeFactor{" +
                "id=" + id +
                ", scenictype_id=" + scenictype_id +
                ", scenictype_name='" + scenictype_name + '\'' +
                ", scenictype_value='" + scenictype_value + '\'' +
                ", del_flag=" + del_flag +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScenictype_id() {
        return scenictype_id;
    }

    public void setScenictype_id(Integer scenictype_id) {
        this.scenictype_id = scenictype_id;
    }

    public String getScenictype_name() {
        return scenictype_name;
    }

    public void setScenictype_name(String scenictype_name) {
        this.scenictype_name = scenictype_name;
    }

    public String getScenictype_value() {
        return scenictype_value;
    }

    public void setScenictype_value(String scenictype_value) {
        this.scenictype_value = scenictype_value;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }
}