package com.example.weathercast.dto;

public class Weather_Time {
    String day; // 날짜
    String time; // 시간대
    String temp; // 온도
    String sky; // 날씨
    String pty; // 날씨
    String pop; // 강수확률
    String pcp; // 강수량
    String sno; // 설량

    public Weather_Time(String day, String time, String temp, String sky, String pty, String pop, String pcp, String sno) {
        this.day = day;
        this.time = time;
        this.temp = temp;
        this.sky = sky;
        this.pty = pty;
        this.pop = pop;
        this.pcp = pcp;
        this.sno = sno;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public String getPty() {
        return pty;
    }

    public void setPty(String pty) {
        this.pty = pty;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPcp() {
        return pcp;
    }

    public void setPcp(String pcp) {
        this.pcp = pcp;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }
}
