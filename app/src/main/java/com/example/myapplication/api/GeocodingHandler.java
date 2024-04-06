package com.example.myapplication.api;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;

public class GeocodingHandler {

    private Context context;
    private String locationName;
    private List<Address> resultSet;
    private List<String> stringResultSet;
    private Address bestResultl;


    private Geocoder geocoder;

    public List<String> getStringResultSet() {
        return stringResultSet;
    }

    public Address getBestResultl() {
        return bestResultl;
    }

    public GeocodingHandler(Geocoder geocoder) {
        this.geocoder = geocoder;

    }

    public void performGeocoding(String locationName) {
        int maxResults = 10;
        this.locationName = locationName;

        try {
            resultSet = geocoder.getFromLocationName(locationName, maxResults);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (resultSet != null && !resultSet.isEmpty()) {
            stringResultSet.clear ();
            for (Address address : resultSet) {
                String addressString = address.getAddressLine(0); // 假设您想要获取地址的第一行
                stringResultSet.add(addressString);
            }
            bestResultl = resultSet.get(0);
        } else {
            throw new RuntimeException("No matching geocoding results is found.");
        }
    }
}