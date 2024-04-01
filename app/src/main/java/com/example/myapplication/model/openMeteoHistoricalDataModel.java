package com.example.myapplication.model;

public class openMeteoHistoricalDataModel {
    private double latitude;
    private double longitude;
    private int past_days;
    // 2021-12-31 ->YYYY-MM-DD
    private String start_date;
    private String end_date;

    private boolean isPastPaysMode;

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public double getPast_days() {
        return past_days;
    }

    public openMeteoHistoricalDataModel(double longitude, double latitude, int past_days) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.past_days = past_days;
        this.isPastPaysMode=true;
    }

    public openMeteoHistoricalDataModel(double longitude, double latitude, String start_date, String end_date) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.start_date = start_date;
        this.end_date = end_date;
        this.isPastPaysMode=false;
    }

    public String url(){
        if(this.isPastPaysMode)
            return "https://api.open-meteo.com/v1/forecast?latitude="+this.latitude+"&longitude="+this.longitude+"&past_days="+this.past_days+"&hourly=temperature_2m";
        else
            return "https://archive-api.open-meteo.com/v1/era5?latitude="+this.latitude+"&longitude="+this.longitude+"&start_date="+this.start_date+"&end_date="+this.end_date+"&hourly=temperature_2m";
    }
}
