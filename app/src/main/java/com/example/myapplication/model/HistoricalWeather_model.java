package com.example.myapplication.model;

public class HistoricalWeather_model {
    private double lat =0;
    private double lon =0;

    private String start_date;

    private String end_date;

    private String hourly_tem;
    public HistoricalWeather_model(){}

    public HistoricalWeather_model(double lat, double lon, String start_date,
                                   String end_date, String hourly_tem){
        this.lat = lat;
        this.lon = lon;
        this.start_date = start_date;
        this.end_date = end_date;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getHourly_tem() {
        return hourly_tem;
    }

    public void setHourly_tem(String hourly_tem) {
        this.hourly_tem = hourly_tem;
    }
}
