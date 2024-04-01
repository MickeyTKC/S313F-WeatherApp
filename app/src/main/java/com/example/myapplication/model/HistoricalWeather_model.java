package com.example.myapplication.model;

public class HistoricalWeather_model {
    private double lat =0;
    private double lon =0;
    private String hourly_tem;

    public HistoricalWeather_model(){}

    public HistoricalWeather_model(double lat, double lon,
                                   String hourly_tem){
        this.lat = lat;
        this.lon = lon;
        this.hourly_tem = hourly_tem;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getHourly_tem() {
        return hourly_tem;
    }

    public void setHourly_tem(String hourly_tem) {
        this.hourly_tem = hourly_tem;
    }
}
