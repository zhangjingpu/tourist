package com.ssm.maven.core.entity;

public class VLCBean {
    private String time_hour;
    private float vlc;
    private int vf;
    private int mq_v;

    public String getTime_hour() {
        return time_hour;
    }

    public void setTime_hour(String time_hour) {
        this.time_hour = time_hour;
    }

    public float getVlc() {
        return vlc;
    }

    public void setVlc(float vlc) {
        this.vlc = vlc;
    }

    public int getVf() {
        return vf;
    }

    public void setVf(int vf) {
        this.vf = vf;
    }

    public int getMq_v() {
        return mq_v;
    }

    public void setMq_v(int mq_v) {
        this.mq_v = mq_v;
    }

    @Override
    public String toString() {
        return "VLCBean{" +
                "time_hour='" + time_hour + '\'' +
                ", vlc=" + vlc +
                ", vf=" + vf +
                ", mq_v=" + mq_v +
                '}';
    }
}
