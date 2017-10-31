package com.ssm.maven.core.entity;

public class HLCTable {

    private int mq_h;

    private int hf;

    private float hlc;

    private int time_hour;

    public HLCTable(int mq_h, int hf) {
        this.mq_h = mq_h;
        this.hf = hf;
    }

    @Override
    public String toString() {
        return "HLCTable{" +
                "mq_h=" + mq_h +
                ", hf=" + hf +
                ", hlc=" + hlc +
                ", time_hour=" + time_hour +
                '}';
    }

    public int getMq_h() {
        return mq_h;
    }

    public void setMq_h(int mq_h) {
        this.mq_h = mq_h;
    }

    public int getHf() {
        return hf;
    }

    public void setHf(int hf) {
        this.hf = hf;
    }

    public float getHlc() {
        return hlc;
    }

    public void setHlc(float hlc) {
        this.hlc = hlc;
    }

    public int getTime_hour() {
        return time_hour;
    }

    public void setTime_hour(int time_hour) {
        this.time_hour = time_hour;
    }
}
