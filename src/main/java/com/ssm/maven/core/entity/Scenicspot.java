package com.ssm.maven.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Scenicspot {
    private Integer id;

    private Integer scenictype;

    private String scenicname;

    private String address;

    private String telephone;

    private Integer max_people;

    private Integer max_car;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date update_time;

    private Integer creator;

    private Integer status;

    private Integer updator;

    private Integer del_flag;

    private String code;

    private double max_di;

    @Override
    public String toString() {
        return "Scenicspot{" +
                "id=" + id +
                ", scenictype=" + scenictype +
                ", scenicname='" + scenicname + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", max_people=" + max_people +
                ", max_car=" + max_car +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", creator=" + creator +
                ", status=" + status +
                ", updator=" + updator +
                ", del_flag=" + del_flag +
                ", code='" + code + '\'' +
                ", max_di=" + max_di +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScenictype() {
        return scenictype;
    }

    public void setScenictype(Integer scenictype) {
        this.scenictype = scenictype;
    }

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getMax_people() {
        return max_people;
    }

    public void setMax_people(Integer max_people) {
        this.max_people = max_people;
    }

    public Integer getMax_car() {
        return max_car;
    }

    public void setMax_car(Integer max_car) {
        this.max_car = max_car;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getMax_di() {
        return max_di;
    }

    public void setMax_di(double max_di) {
        this.max_di = max_di;
    }
}