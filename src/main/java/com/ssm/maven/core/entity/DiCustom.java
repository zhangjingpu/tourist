package com.ssm.maven.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DiCustom extends DiTable {
    private String code;
    private String scenicname;
    private String address;
    @JSONField(format = "yyyy-MM-dd")
    private Date start_time;
    @JSONField(format = "yyyy-MM-dd")
    private Date end_time;

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

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return super.toString() + "DiCustom{" +
                "code='" + code + '\'' +
                ", scenicname='" + scenicname + '\'' +
                ", address='" + address + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}
