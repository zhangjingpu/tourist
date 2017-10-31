package com.ssm.maven.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScenicspotCustom extends Scenicspot {

    @JSONField(format = "yyyy-MM-dd")
    private Date enter_day;

    public Date getEnter_day() {
        return enter_day;
    }

    public void setEnter_day(Date enter_day) {
        this.enter_day = enter_day;
    }

    public Date getEnd_day() {
        return end_day;
    }

    public void setEnd_day(Date end_day) {
        this.end_day = end_day;
    }

    @JSONField(format = "yyyy-MM-dd")

    private Date end_day;

    private String scenictype_name;

    public String getScenictype_name() {
        return scenictype_name;
    }

    public void setScenictype_name(String scenictype_name) {
        this.scenictype_name = scenictype_name;
    }

    @Override
    public String toString() {
        return super.toString() + "enter_day" + this.enter_day + "end_day" + this.end_day;
    }
}
