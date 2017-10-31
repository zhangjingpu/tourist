package com.ssm.maven.core.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class CarCustom extends ParkingCar {
    private String code;
    private String scenicname;
    private String address;
    private String max_car;
    @JSONField(format = "yyyy-MM-dd")
    private Date start_day;
    @JSONField(format = "yyyy-MM-dd")
    private Date end_day;

    public String getScenicname() {
        return scenicname;
    }

    public void setScenicname(String scenicname) {
        this.scenicname = scenicname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMax_car() {
        return max_car;
    }

    public void setMax_car(String max_car) {
        this.max_car = max_car;
    }

    public Date getStart_day() {
        return start_day;
    }

    public void setStart_day(Date start_day) {
        this.start_day = start_day;
    }

    public Date getEnd_day() {
        return end_day;
    }

    public void setEnd_day(Date end_day) {
        this.end_day = end_day;
    }
}
