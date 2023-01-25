package com.example.weathercast.dto;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("baseDate")
    String baseDate;
    @SerializedName("baseTime")
    String baseTime;
    @SerializedName("category")
    String category;
    @SerializedName("fcstDate")
    String fcstDate;
    @SerializedName("fcstTime")
    String fcstTime;
    @SerializedName("fcstValue")
    String fcstValue;
    @SerializedName("nx")
    String nx;
    @SerializedName("ny")
    String ny;

    public String getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(String baseDate) {
        this.baseDate = baseDate;
    }

    public String getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(String baseTime) {
        this.baseTime = baseTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFcstDate() {
        return fcstDate;
    }

    public void setFcstDate(String fcstDate) {
        this.fcstDate = fcstDate;
    }

    public String getFcstTime() {
        return fcstTime;
    }

    public void setFcstTime(String fcstTime) {
        this.fcstTime = fcstTime;
    }

    public String getFcstValue() {
        return fcstValue;
    }

    public void setFcstValue(String fcstValue) {
        this.fcstValue = fcstValue;
    }

    public String getNx() {
        return nx;
    }

    public void setNx(String nx) {
        this.nx = nx;
    }

    public String getNy() {
        return ny;
    }

    public void setNy(String ny) {
        this.ny = ny;
    }
}
