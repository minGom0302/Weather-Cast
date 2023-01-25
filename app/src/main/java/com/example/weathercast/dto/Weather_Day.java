package com.example.weathercast.dto;

public class Weather_Day {
    String day;
    String minTemp;
    String maxTemp;
    String minSky;
    String maxSky;
    String minPty;
    String maxPty;

    public Weather_Day(String day, String minTemp, String maxTemp, String minSky, String maxSky, String minPty, String maxPty) {
        this.day = day;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.minSky = minSky;
        this.maxSky = maxSky;
        this.minPty = minPty;
        this.maxPty = maxPty;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinSky() {
        return minSky;
    }

    public void setMinSky(String minSky) {
        this.minSky = minSky;
    }

    public String getMaxSky() {
        return maxSky;
    }

    public void setMaxSky(String maxSky) {
        this.maxSky = maxSky;
    }

    public String getMinPty() {
        return minPty;
    }

    public void setMinPty(String minPty) {
        this.minPty = minPty;
    }

    public String getMaxPty() {
        return maxPty;
    }

    public void setMaxPty(String maxPty) {
        this.maxPty = maxPty;
    }
}
