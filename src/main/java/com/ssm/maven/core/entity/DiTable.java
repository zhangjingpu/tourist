package com.ssm.maven.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DiTable {
    private Integer id;

    private Double celsius;

    private Double fahrenheit;

    private Double relative_humidity;

    private Integer time_hour;
    @JSONField(format = "yyyy-MM-dd")
    private Date time_date;

    private Double di;

    private Integer del_flag;

    private Integer type_weather;

    private Integer scenic_id;

    @Override
    public String toString() {
        return "DiTable{" +
                "id=" + id +
                ", celsius=" + celsius +
                ", fahrenheit=" + fahrenheit +
                ", relative_humidity=" + relative_humidity +
                ", time_hour=" + time_hour +
                ", time_date=" + time_date +
                ", di=" + di +
                ", del_flag=" + del_flag +
                ", type_weather=" + type_weather +
                ", scenic_id=" + scenic_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCelsius() {
        return celsius;
    }

    public void setCelsius(Double celsius) {
        this.celsius = celsius;
    }

    public Double getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(Double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public Double getRelative_humidity() {
        return relative_humidity;
    }

    public void setRelative_humidity(Double relative_humidity) {
        this.relative_humidity = relative_humidity;
    }

    public Integer getTime_hour() {
        return time_hour;
    }

    public void setTime_hour(Integer time_hour) {
        this.time_hour = time_hour;
    }

    public Date getTime_date() {
        return time_date;
    }

    public void setTime_date(Date time_date) {
        this.time_date = time_date;
    }

    public Double getDi() {
        return di;
    }

    public void setDi(Double di) {
        this.di = di;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }

    public Integer getType_weather() {
        return type_weather;
    }

    public void setType_weather(Integer type_weather) {
        this.type_weather = type_weather;
    }

    public Integer getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(Integer scenic_id) {
        this.scenic_id = scenic_id;
    }


}