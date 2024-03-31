package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

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

public class MainActivity extends AppCompatActivity implements LocationListener{
    private static String tag = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static double lat;
    public static double lon;
    public static String address;
    public static String country;
    public static JsonHandlerThread th;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Data binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        // Setting button
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // NavBar and Items
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_view_history)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        // Find view by id
        textCurrentDate = findViewById(R.id.textCurrentDate);
        textCurrentAddress = findViewById(R.id.textCurrentAddress);
        textCurrentDescription = findViewById(R.id.textCurrentDescription);
        textCurrentTemp = findViewById(R.id.textCurrentTemp);
        textCurrentTempMin = findViewById(R.id.textCurrentTempMin);
        textCurrentTempMax = findViewById(R.id.textCurrentTempMax);
        textCurrentWind = findViewById(R.id.textCurrentWind);
        textCurrentVisibility = findViewById(R.id.textCurrentVisibility);
        imageCurrentIcon = findViewById(R.id.imageCurrentIcon);
        // Get GPS Permission
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
        getLocation();
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,MainActivity.this);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void setCurrentWeather() {
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        textCurrentDate.setText(df.format(now));
        textCurrentAddress.setText(country);
        textCurrentDescription.setText(CurrentWeather.data.get(CurrentWeather.DESCRIPTION));
        textCurrentTemp.setText(CurrentWeather.data.get(CurrentWeather.TEMP));
        textCurrentTempMin.setText(CurrentWeather.data.get(CurrentWeather.TEMP_MIN));
        textCurrentTempMax.setText(CurrentWeather.data.get(CurrentWeather.TEMP_MAX));
        textCurrentWind.setText(CurrentWeather.data.get(CurrentWeather.WIND));
        textCurrentVisibility.setText(CurrentWeather.data.get(CurrentWeather.VISIBILITY));
        imageCurrentIcon.setImageResource(CurrentWeather.getIconSource());
    }
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            lat = location.getLatitude();
            lon = location.getLongitude();
            address = addresses.get(0).getAddressLine(0);
            country = addresses.get(0).getCountryName();
        }catch (Exception e){
            e.printStackTrace();
        }
        //String url = JsonHandlerThread.OPEN_WEATHER_BASE + JsonHandlerThread.OPEN_WEATHER_CURRENT;
       try{
           String url = JsonHandlerThread.OPEN_WEATHER_BASE + JsonHandlerThread.OPEN_WEATHER_CURRENT;
           url += "lat=" + lat;
           url += "&lon=" + lon;
           url += "&units=metric";
           url += JsonHandlerThread.OPEN_WEATHER_KEY;
           th = new JsonHandlerThread(url);
           th.start();
           th.join();
           CurrentWeather.setData(th.getResult());
           Log.d(tag, CurrentWeather.data.get(CurrentWeather.WEATHER));
           setCurrentWeather();
       } catch (Exception e){
           e.printStackTrace();
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
