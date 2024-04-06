package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.api.*;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.example.myapplication.model.*;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements LocationListener {
    //data sharing
    boolean isRadioButton_search_locationChecked = false;
    private static String tag = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static double lat;
    public static double lon;
    public static String address;
    public static String country;
    public static String lang;
    public static String unit;

    public static JsonHandlerThread th;

    public static JsonHandlerThread historicalThread;

    public static JsonHandlerThread currentThread;
    public static JsonHandlerThread forecastThread;

    TextView textCurrentDate;
    TextView textCurrentAddress;
    TextView textCurrentDescription;
    TextView textCurrentTemp;
    TextView textCurrentTempMin;
    TextView textCurrentTempMax;
    TextView textCurrentWind;
    TextView textCurrentVisibility;
    ImageView imageCurrentIcon;
    LocationManager locationManager;

    TextView textForecastTime1, textForecastTime2, textForecastTime3, textForecastTime4;
    TextView textForecastTemp1, textForecastTemp2, textForecastTemp3, textForecastTemp4;
    ImageView imageForecast1, imageForecast2, imageForecast3, imageForecast4;

    TextView textMinTemp,textMaxTemp,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        // Get Pref
        SharedPreferences sharedPreferences = this.getSharedPreferences ("Setting", MODE_PRIVATE);
        lang = sharedPreferences.getString (SettingActivity.LANG, "en");
        setAppLocale (lang);
        // Data binding
        binding = ActivityMainBinding.inflate (getLayoutInflater ());
        setContentView (binding.getRoot ());
        if (sharedPreferences.getString (SettingActivity.THEME, "L").equals ("D"))
            AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_YES);
        else {
            AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_NO);
        }
        unit = sharedPreferences.getString(SettingActivity.UNIT, "C");
        setSupportActionBar (binding.appBarMain.toolbar);

        // SettingActivity button
        binding.appBarMain.fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Snackbar.make (view, "Refreshing ...", Snackbar.LENGTH_LONG)
                        .setAction ("Action", null).show ();
                getLocation ();
            }
        });
        // NavBar and Items
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder (
                R.id.nav_home, R.id.nav_forecast, R.id.nav_view_history)
                .setOpenableLayout (drawer)
                .build ();
        NavController navController = Navigation.findNavController (this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController (this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController (navigationView, navController);
        // Find view by id
        textCurrentDate = findViewById (R.id.textCurrentDate);
        textCurrentAddress = findViewById (R.id.textCurrentAddress);
        textCurrentDescription = findViewById (R.id.textCurrentDescription);
        textCurrentTemp = findViewById (R.id.textCurrentTemp);
        textCurrentTempMin = findViewById (R.id.textCurrentTempMin);
        textCurrentTempMax = findViewById (R.id.textCurrentTempMax);
        textCurrentWind = findViewById (R.id.textCurrentWind);
        textCurrentVisibility = findViewById (R.id.textCurrentVisibility);
        imageCurrentIcon = findViewById (R.id.imageCurrentIcon);

        //Forecast
        textForecastTime1 = findViewById (R.id.textForecastTime1);
        textForecastTime2 = findViewById (R.id.textForecastTime2);
        textForecastTime3 = findViewById (R.id.textForecastTime3);
        textForecastTime4 = findViewById (R.id.textForecastTime4);
        textForecastTemp1 = findViewById (R.id.textForecastTemp1);
        textForecastTemp2 = findViewById (R.id.textForecastTemp2);
        textForecastTemp3 = findViewById (R.id.textForecastTemp3);
        textForecastTemp4 = findViewById (R.id.textForecastTemp4);
        imageForecast1 = findViewById (R.id.imageForecast1);
        imageForecast2 = findViewById (R.id.imageForecast2);
        imageForecast3 = findViewById (R.id.imageForecast3);
        imageForecast4 = findViewById (R.id.imageForecast4);

        //History
        textMinTemp = findViewById(R.id.minTemp);
        textMaxTemp = findViewById(R.id.maxTemp);
        time = findViewById(R.id.time);
        // Get GPS Permission
        if (ContextCompat.checkSelfPermission (MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions (MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        getLocation ();
        Toast.makeText (this, "Loading", Toast.LENGTH_SHORT).show ();
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext ().getSystemService (LOCATION_SERVICE);
            locationManager.requestLocationUpdates (LocationManager.GPS_PROVIDER, 5000, 5, MainActivity.this);
        } catch (Exception e) {
            e.printStackTrace ();
        }

    }

    public void setCurrentWeather() {
        Date now = Calendar.getInstance ().getTime ();
        SimpleDateFormat df = new SimpleDateFormat ("dd-MMM-yyyy", Locale.getDefault ());
        textCurrentDate.setText (df.format (now));
        textCurrentAddress.setText (country);
        textCurrentDescription.setText (CurrentWeather.data.get (CurrentWeather.DESCRIPTION));
        textCurrentTemp.setText (CurrentWeather.data.get (CurrentWeather.TEMP));
        textCurrentTempMin.setText (CurrentWeather.data.get (CurrentWeather.TEMP_MIN));
        textCurrentTempMax.setText (CurrentWeather.data.get (CurrentWeather.TEMP_MAX));
        textCurrentWind.setText (CurrentWeather.data.get (CurrentWeather.WIND));
        textCurrentVisibility.setText (CurrentWeather.data.get (CurrentWeather.VISIBILITY));
        imageCurrentIcon.setImageResource (CurrentWeather.getIconSource ());
    }

    public void setForecastWeather() {
        Calendar c = Calendar.getInstance ();
        Date now = c.getTime ();
        SimpleDateFormat dm = new SimpleDateFormat ("dd-MMM", Locale.getDefault ());
        c.add (Calendar.DAY_OF_YEAR, 1);
        textForecastTime1.setText (dm.format (c.getTime ()));
        c.add (Calendar.DAY_OF_YEAR, 1);
        textForecastTime2.setText (dm.format (c.getTime ()));
        c.add (Calendar.DAY_OF_YEAR, 1);
        textForecastTime3.setText (dm.format (c.getTime ()));
        c.add (Calendar.DAY_OF_YEAR, 1);
        textForecastTime4.setText (dm.format (c.getTime ()));
        //imageForecast1.setValue(ForecastWeather.data.get(ForecastWeather.ICON));
        Log.d ("HomeModel Forecast", ForecastWeather.data.get (0).toString ());
        textForecastTemp1.setText (ForecastWeather.data.isEmpty () ? "Loading" : ForecastWeather.data.get (8).get (ForecastWeather.TEMP));
        textForecastTemp2.setText (ForecastWeather.data.isEmpty () ? "Loading" : ForecastWeather.data.get (16).get (ForecastWeather.TEMP));
        textForecastTemp3.setText (ForecastWeather.data.isEmpty () ? "Loading" : ForecastWeather.data.get (24).get (ForecastWeather.TEMP));
        textForecastTemp4.setText (ForecastWeather.data.isEmpty () ? "Loading" : ForecastWeather.data.get (32).get (ForecastWeather.TEMP));
        imageForecast1.setImageResource (ForecastWeather.getIconSource (8));
        imageForecast2.setImageResource (ForecastWeather.getIconSource (16));
        imageForecast3.setImageResource (ForecastWeather.getIconSource (24));
        imageForecast4.setImageResource (ForecastWeather.getIconSource (32));

    }

    public void setHistoricalWeather() {

    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            isRadioButton_search_locationChecked = sharedPreferences.getBoolean ("isRadioButton_search_locationChecked", false);
            Toast.makeText (this, "Loading sharedPreferences"+isRadioButton_search_locationChecked, Toast.LENGTH_SHORT).show ();
            Geocoder geocoder = new Geocoder (MainActivity.this, Locale.getDefault ());
            if (!isRadioButton_search_locationChecked) {
                Toast.makeText (this, "isRadioButton_search_locationChecked=0"+isRadioButton_search_locationChecked, Toast.LENGTH_SHORT).show ();
                List<Address> addresses = geocoder.getFromLocation (location.getLatitude (), location.getLongitude (), 1);
                lat = location.getLatitude ();
                lon = location.getLongitude ();
                address = addresses.get (0).getAddressLine (0);
                country = addresses.get (0).getCountryName ();
            } else {
                Toast.makeText (this, "isRadioButton_search_locationChecked=1"+isRadioButton_search_locationChecked, Toast.LENGTH_SHORT).show ();
                String UserInput = sharedPreferences.getString("UserInput", "Hong Kong");

                List<Address> addresses = geocoder.getFromLocationName (UserInput, 1);
                lat = addresses.get (0).getLatitude ();
                lon = addresses.get (0).getLongitude ();
                address = addresses.get (0).getAddressLine (0);
                country = addresses.get (0).getCountryName ();
                Toast.makeText (this, "loading data="+UserInput, Toast.LENGTH_SHORT).show ();
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        try {
            String utype = "";
            if(unit == "C") utype = "metric";
            else utype = "imperial";

            String currentURL = JsonHandlerThread.OPEN_WEATHER_BASE + JsonHandlerThread.OPEN_WEATHER_CURRENT;
            currentURL += "lat=" + lat;
            currentURL += "&lon=" + lon;
            currentURL += "&units=" + utype;
            currentURL += "&lang=" + lang;
            currentURL += JsonHandlerThread.OPEN_WEATHER_KEY;
            currentThread = new JsonHandlerThread (currentURL);
            currentThread.start ();
            currentThread.join ();
            CurrentWeather.setData (currentThread.getResult ());
            setCurrentWeather ();


            Log.d (tag, CurrentWeather.data.get (CurrentWeather.WEATHER));
            String forecastURL = JsonHandlerThread.OPEN_WEATHER_BASE + JsonHandlerThread.OPEN_WEATHER_FORECAST;
            forecastURL += "lat=" + lat;
            forecastURL += "&lon=" + lon;
            forecastURL += "&units=" + utype;
            forecastURL += "&lang=" + lang;
            forecastURL += JsonHandlerThread.OPEN_WEATHER_KEY;
            forecastThread = new JsonHandlerThread (forecastURL);
            forecastThread.start ();
            forecastThread.join ();
            ForecastWeather.setData (forecastThread.getResult ());
            Log.d (tag, ForecastWeather.data.toString ());
            setForecastWeather ();


            //Historical api setup
            String historical_url = JsonHandlerThread.OPEN_METEO_BASE + JsonHandlerThread.OPEN_METEO_HISTORICAL;
            historical_url += "latitude=" + lat;
            historical_url += "&longitude=" + lon;
            historical_url += "&daily=temperature_2m_max";
            historical_url += ",temperature_2m_min";
            historical_url += ",rain_sum";
            historical_url += ",wind_speed_10m_max";
            historical_url += "&past_days=7";
            if(unit != "C") historical_url += "&temperature_unit=fahrenheit";
            //String tryUrl = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&daily=temperature_2m_max,temperature_2m_min&past_days=7";

            historicalThread = new JsonHandlerThread (historical_url);
            historicalThread.start ();
            historicalThread.join ();
            HistoricalWeather.setData (historicalThread.getResult ());
            Log.d (tag, HistoricalWeather.data.toString ());
            setHistoricalWeather ();


        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate (R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController (this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp (navController, mAppBarConfiguration)
                || super.onSupportNavigateUp ();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == 0) {
            //Toast.makeText(this, "After Setting", Toast.LENGTH_SHORT).show();
            recreate ();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ();
        if (id == R.id.action_settings) {
            Intent setting = new Intent (this, SettingActivity.class);
            startActivityForResult (setting, 0);
        }
        return super.onOptionsItemSelected (item);
    }

    private void setAppLocale(String languageCode) {
        Locale locale = new Locale (languageCode);
        Locale.setDefault (locale);

        Configuration configuration = getResources ().getConfiguration ();
        configuration.setLocale (locale);
        getResources ().updateConfiguration (configuration, getResources ().getDisplayMetrics ());
    }

}
