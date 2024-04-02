package com.example.myapplication.model;

public class HistoricalWeather_model {
    private double lat =0;
    private double lon =0;
    private double min_temp;

    public HistoricalWeather_model(){}

    public HistoricalWeather_model(double lat, double lon,
                                   double min_temp){
        this.lat = lat;
        this.lon = lon;
        this.min_temp = min_temp;
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

    public double getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(double min_temp) {
        this.min_temp = min_temp;
    }
}
