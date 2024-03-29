package com.example.myapplication.model;

public class CurrentWeather_model{
    private double lat =0;
    private double lon =0;

    public CurrentWeather_model(){}

    public CurrentWeather_model(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
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
}
