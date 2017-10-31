package com.ssm.maven.core.entity;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Park {
    private Integer id;

    private String parkingName;

    private Integer scenic_id;

    private String creator;

    private String updator;

    private Integer money;

    private Date create_time;

    private Date update_time;

    private Integer del_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public Integer getscenic_id() {
        return scenic_id;
    }

    public void setscenic_id(Integer scenic_id) {
        this.scenic_id = scenic_id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Date getcreate_time() {
        return create_time;
    }

    public void setcreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getupdate_time() {
        return update_time;
    }

    public void setupdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getdel_flag() {
        return del_flag;
    }

    public void setdel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }
}