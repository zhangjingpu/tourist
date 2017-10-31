package com.ssm.maven.core.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ParkingCar {
    private Integer id;

    private String license_car;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date enter_time;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date leave_time;

    private Integer car_type;

    private String park_id;

    private Integer paymoney;

    private Integer del_flag;

    private Integer science_id;
    @JSONField(format = "yyyy-MM-dd")
    private Date day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicense_car() {
        return license_car;
    }

    public void setLicense_car(String license_car) {
        this.license_car = license_car;
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

    public Integer getCar_type() {
        return car_type;
    }

    public void setCar_type(Integer car_type) {
        this.car_type = car_type;
    }

    public String getPark_id() {
        return park_id;
    }

    public void setPark_id(String park_id) {
        this.park_id = park_id;
    }

    public Integer getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(Integer paymoney) {
        this.paymoney = paymoney;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }

    public Integer getScience_id() {
        return science_id;
    }

    public void setScience_id(Integer science_id) {
        this.science_id = science_id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "ParkingCar{" +
                "id=" + id +
                ", license_car='" + license_car + '\'' +
                ", enter_time=" + enter_time +
                ", leave_time=" + leave_time +
                ", car_type=" + car_type +
                ", park_id='" + park_id + '\'' +
                ", paymoney=" + paymoney +
                ", del_flag=" + del_flag +
                ", science_id=" + science_id +
                ", day=" + day +
                '}';
    }
}