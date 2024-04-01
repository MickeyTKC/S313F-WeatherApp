package com.example.myapplication.model;

public class OpenCageGeocodingForwardGeocodingDataModel {
    //request can be in any language
    private String request;
    //Free trial usage limits
    //Free trial accounts have a hard limit of 2,500 requests per day for testing purposes.
    //Our definition of "day" is based on the UTC timezone. Daily counts reset at 24:00 UTC. See current UTC time.
    //Free trial accounts are limited to one request per second.
    //If you exceed that rate you may see a response. 429 - too many requests
    private final String key = "5251fcaeb2184291895822f6a2b65e34";

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public OpenCageGeocodingForwardGeocodingDataModel(String request) {
        this.request = request;
    }

    public String url(){
        return "https://api.opencagedata.com/geocode/v1/json?q="+this.request+"&key="+this.key;
    }
}
