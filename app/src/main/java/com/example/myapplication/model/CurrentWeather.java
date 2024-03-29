package com.example.myapplication.model;

import android.util.Log;

import org.json.*;

import java.util.*;

public class CurrentWeather {
    private static String tag = "CurrentWeather";
    //Object keys
    public static String WEATHER = "weather";
    public static String MAIN = "main";
    public static String DESCRIPTION = "description";
    public static String ICON = "icon";
    public static String TEMP = "temp";
    public static String TEMP_MAX = "temp_max";
    public static String TEMP_MIN = "temp_min";
    public static String WIND = "wind";
    public static String SPEED = "speed";
    public static String VISIBILITY = "visibility";
    //Data
    public static HashMap<String, String> data = new HashMap<>();
    public CurrentWeather() {}
    public static void setData(JSONObject jsonObj) throws org.json.JSONException{
        // Convert to Java Data
        JSONObject weather = jsonObj.getJSONArray(WEATHER).getJSONObject(0);
        JSONObject temp = jsonObj.getJSONObject(MAIN);
        JSONObject wind = jsonObj.getJSONObject(WIND);
        String visibility = jsonObj.getString(VISIBILITY);
        // put JSON weather.main
        data.put(WEATHER , weather.getString(MAIN));
        // put JSON weather.description
        data.put(DESCRIPTION , weather.getString(DESCRIPTION));
        // put JSON weather.icon
        data.put(ICON, weather.getString(ICON));
        // put JSON main.temp
        data.put(TEMP, temp.getString(TEMP));
        // put JSON main.temp_max
        data.put(TEMP_MAX, temp.getString(TEMP_MAX));
        // put JSON main.temp_min
        data.put(TEMP_MIN, temp.getString(TEMP_MIN));
        // put JSON wind.speed
        data.put(WIND , wind.getString(SPEED));
        // put JSON visibility
        data.put(VISIBILITY , visibility);
        Log.d(tag,data.toString());
    }
}
