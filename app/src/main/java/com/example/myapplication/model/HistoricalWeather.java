package com.example.myapplication.model;

import android.util.Log;

import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoricalWeather {
    private static String tag = "HistoricalWeather";

    public static String TIME = "time";
    public static String TEMP_MAX = "temperature_2m_max";
    public static String TEMP_MIN = "temperature_2m_min";
    public static String WIND = "wind_speed_10m_max";
    public static String RAIN = "rain_sum";
    // Icon Constance
    //Data
    public static ArrayList<HashMap<String, String>> data = new ArrayList<>();

    public HistoricalWeather(){}

    public static void setData(JSONObject jsonObj) throws org.json.JSONException {
        // Clear the data hashmap
        data.clear();

        // Fetch the required data from the JSON object
        JSONArray dailyArray = jsonObj.getJSONObject("daily").getJSONArray("time");
        JSONArray tempMaxArray = jsonObj.getJSONObject("daily").getJSONArray(TEMP_MAX);
        JSONArray tempMinArray = jsonObj.getJSONObject("daily").getJSONArray(TEMP_MIN);
        JSONArray rainArray = jsonObj.getJSONObject("daily").getJSONArray(WIND);
        JSONArray windArray = jsonObj.getJSONObject("daily").getJSONArray(RAIN);

        // Get the temperature data for the past 7 days
        for (int i = 7; i > 0; i--) {
            HashMap<String, String> tempData = new HashMap<>();
            String tempMax = tempMaxArray.getDouble(i) + "";
            String tempMin = tempMinArray.getDouble(i) + "";
            String rain = rainArray.getDouble(i) + "";
            String wind = windArray.getDouble(i) + "";
            String time = dailyArray.getString(i);

            // Add the temperature data to the hashmap
            tempData.put(TIME, time);
            tempData.put(TEMP_MAX, tempMax);
            tempData.put(TEMP_MIN, tempMin);
            tempData.put(WIND, wind);
            tempData.put(RAIN, rain);
            data.add(tempData);
        }
        Log.d(tag,data.toString());
    }
}