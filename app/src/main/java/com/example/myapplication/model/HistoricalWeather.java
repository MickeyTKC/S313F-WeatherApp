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
    public static String DESCRIPTION = "description";
    public static String TEMP = "temp";
    public static String TEMP_MAX = "temperature_2m_max";
    public static String TEMP_MIN = "temperature_2m_min";
    public static String WIND = "wind";
    public static String SPEED = "speed";
    public static String VISIBILITY = "visibility";
    // Icon Constance
    private static final String CLEAR_SKY_D = "01d.png";
    private static final String FEW_CLOUDS_D = "02d.png";
    private static final String SCATTERED_CLOUDS_D = "03d.png";
    private static final String BROKEN_CLOUDS_D = "04d.png";
    private static final String SHOWER_RAIN_D = "09d.png";
    private static final String RAIN_D = "10d.png";
    private static final String THUNDERSTORM_D = "11d.png";
    private static final String SNOW_D = "13d.png";
    private static final String MIST_D = "50d.png";
    private static final String CLEAR_SKY_N = "01n.png";
    private static final String FEW_CLOUDS_N = "02n.png";
    private static final String SCATTERED_CLOUDS_N = "03n.png";
    private static final String BROKEN_CLOUDS_N = "04n.png";
    private static final String SHOWER_RAIN_N = "09n.png";
    private static final String RAIN_N = "10n.png";
    private static final String THUNDERSTORM_N = "11n.png";
    private static final String SNOW_N = "13n.png";
    private static final String MIST_N = "50n.png";
    //Data
    public static ArrayList<HashMap<String, String>> data = new ArrayList<>();

    public HistoricalWeather(){}

    public static void setData(JSONObject jsonObj) throws org.json.JSONException {
        // Clear the data hashmap
        data.clear();

        // Fetch the required data from the JSON object
        JSONArray dailyArray = jsonObj.getJSONObject("daily").getJSONArray("time");
        JSONArray tempMaxArray = jsonObj.getJSONObject("daily").getJSONArray("temperature_2m_max");
        JSONArray tempMinArray = jsonObj.getJSONObject("daily").getJSONArray("temperature_2m_min");

        // Get the temperature data for the past 7 days
        for (int i = 7; i > 0; i--) {
            HashMap<String, String> tempData = new HashMap<>();
            String tempMax = tempMaxArray.getDouble(i) + "";
            String tempMin = tempMinArray.getDouble(i) + "";
            String time = dailyArray.getString(i);

            // Add the temperature data to the hashmap
            tempData.put(TIME, time);
            tempData.put(TEMP_MAX, tempMax);
            tempData.put(TEMP_MIN, tempMin);
            data.add(tempData);
        }
        Log.d(tag,data.toString());
    }
}