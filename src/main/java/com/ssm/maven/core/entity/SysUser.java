package com.ssm.maven.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysUser {
    private Integer id;

    private String account;

    private String username;

    private String password;

    private String role;

    private String phone;

    private Integer sex;

    private String email;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creat_time;

    private String updator;

    private String creator;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date update_time;

    private Integer del_flag;

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", creat_time=" + creat_time +
                ", updator='" + updator + '\'' +
                ", creator='" + creator + '\'' +
                ", update_time=" + update_time +
                ", del_flag=" + del_flag +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }
}