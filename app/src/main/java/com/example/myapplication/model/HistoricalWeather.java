package com.example.myapplication.model;

import android.util.Log;

import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class HistoricalWeather {
    private static String tag = "HistoricalWeather";
    public static String WEATHER = "weather_code";
    public static String DAILY = "daily";

    public static String TIME = "time";
    public static String MAIN = "main";
    public static String DESCRIPTION = "description";
    public static String ICON = "icon";
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
    public static HashMap<String, String> data = new HashMap<>();

    public HistoricalWeather(){}

    public static int getIconSource(){
        try {
            switch (data.get(CurrentWeather.ICON)){
                case CLEAR_SKY_D: return R.mipmap.clear_sky_d_foreground;
                case FEW_CLOUDS_D: return R.mipmap.few_clouds_d_foreground;
                case SCATTERED_CLOUDS_D: return R.mipmap.scattered_clouds_d_foreground;
                case BROKEN_CLOUDS_D: return R.mipmap.broken_clouds_d_foreground;
                case SHOWER_RAIN_D: return R.mipmap.shower_rain_d_foreground;
                case RAIN_D: return R.mipmap.rain_d_foreground;
                case THUNDERSTORM_D: return R.mipmap.thunderstorm_d_foreground;
                case SNOW_D: return R.mipmap.snow_d_foreground;
                case MIST_D: return R.mipmap.mist_d_foreground;
                case CLEAR_SKY_N: return R.mipmap.clear_sky_n_foreground;
                case FEW_CLOUDS_N: return R.mipmap.few_clouds_n_foreground;
                case SCATTERED_CLOUDS_N: return R.mipmap.scattered_clouds_n_foreground;
                case BROKEN_CLOUDS_N: return R.mipmap.broken_clouds_n_foreground;
                case SHOWER_RAIN_N: return R.mipmap.rain_n_foreground;
                case RAIN_N: return R.mipmap.rain_n_foreground;
                case THUNDERSTORM_N: return R.mipmap.thunderstorm_n_foreground;
                case SNOW_N: return R.mipmap.snow_n_foreground;
                case MIST_N: return R.mipmap.mist_n_foreground;
                default: return R.mipmap.clear_sky_d_foreground;
            }
        }catch (Exception e){
            Log.d(tag,"Icon Exception");
            return R.mipmap.few_clouds_d_foreground;
        }
    }
    public static void setData(JSONObject jsonObj) throws org.json.JSONException {
        // Clear the data hashmap
        data.clear();

        // Fetch the required data from the JSON object
        JSONArray dailyArray = jsonObj.getJSONObject("daily").getJSONArray("time");
        JSONArray tempMaxArray = jsonObj.getJSONObject("daily").getJSONArray("temperature_2m_max");
        JSONArray tempMinArray = jsonObj.getJSONObject("daily").getJSONArray("temperature_2m_min");

        // Get the temperature data for the past 7 days
        for (int i = 0; i < dailyArray.length(); i++) {
            String tempMax = tempMaxArray.getDouble(i) + "°C";
            String tempMin = tempMinArray.getDouble(i) + "°C";
            String time = dailyArray.getString(i);

            // Add the temperature data to the hashmap
            data.put(TIME + i, time);
            data.put(TEMP_MAX + i, tempMax);
            data.put(TEMP_MIN + i, tempMin);
        }
    }
}
