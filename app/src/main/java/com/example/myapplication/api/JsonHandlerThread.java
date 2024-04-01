package com.example.myapplication.api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.PreparedStatement;

import com.example.myapplication.model.*;

public class JsonHandlerThread extends Thread {
    // Constance
    private static final String TAG = "JsonHandlerThread";
    public static final String OPEN_WEATHER_BASE = "https://api.openweathermap.org/data/2.5/";
    public static final String OPEN_WEATHER_CURRENT = "weather?";
    public static final String OPEN_WEATHER_FORECAST = "forecast/?";
    public static final String OPEN_WEATHER_KEY = "&appid=0afc41116086771ceea4c08d88916501";
    public static final String OPEN_METEO_BASE = "https://api.open-meteo.com/v1/";

    public static final String OPEN_METEO_HISTORICAL = "forecast?";
    // URL to get contacts JSON file
    private String jsonUrl = "";
    private JSONObject res;

    public JsonHandlerThread(String url){
        this.jsonUrl = url;
    }


    // send request to the url, no need to be changed
    public void setLocValue(double lat, double lon){
        jsonUrl = "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=0afc41116086771ceea4c08d88916501";
    }

    public static String makeRequest(String jsonUrl) {
        String response = null;
        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = inputStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    // download of JSON file from the url to the app
    private static String inputStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            }
        }
        return sb.toString();
    }

    public void run() {
        // "contactStr" variable store the json file content
        String contactStr = makeRequest(jsonUrl);
        Log.e(TAG, "Response from url: " + contactStr);
        if (contactStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(contactStr);
                this.res = jsonObj;
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }
    }

    public JSONObject getResult(){
        return this.res;
    }
}