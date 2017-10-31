package com.ssm.maven.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class Tourist {
    private Integer id;

    private Integer tourist_type;

    @JSONField(format = "yyyy-MM-dd")
    private Date enter_day;

    private Integer science_id;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date enter_time;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date leave_time;

    private Integer del_flag;

    private String tourist_code;

    private Integer time_hour;

    public Integer getTime_hour() {
        return time_hour;
    }

    public void setTime_hour(Integer time_hour) {
        this.time_hour = time_hour;
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "id=" + id +
                ", tourist_type=" + tourist_type +
                ", enter_day=" + enter_day +
                ", science_id=" + science_id +
                ", enter_time=" + enter_time +
                ", leave_time=" + leave_time +
                ", del_flag=" + del_flag +
                ", tourist_code='" + tourist_code + '\'' +
                ", time_hour=" + time_hour +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTourist_type() {
        return tourist_type;
    }

    public void setTourist_type(Integer tourist_type) {
        this.tourist_type = tourist_type;
    }

    public Date getEnter_day() {
        return enter_day;
    }

    public void setEnter_day(Date enter_day) {
        this.enter_day = enter_day;
    }

    public Integer getScience_id() {
        return science_id;
    }

    public void setScience_id(Integer science_id) {
        this.science_id = science_id;
    }

    public Date getEnter_time() {
        return enter_time;
    }

    public void setEnter_time(Date enter_time) {
        this.enter_time = enter_time;
    }

    public Date getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(Date leave_time) {
        this.leave_time = leave_time;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }

    public String getTourist_code() {
        return tourist_code;
    }

    public void setTourist_code(String tourist_code) {
        this.tourist_code = tourist_code;
    }
}