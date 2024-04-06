package com.example.myapplication.ui.home;

import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.model.CurrentWeather;
import com.example.myapplication.model.ForecastWeather;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private static String tag = "HomeFragment";
    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        // Setup Binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // Declare TextView
        final TextView textCurrentDate = binding.textCurrentDate;
        final TextView textCurrentAddress = binding.textCurrentAddress;
        final TextView textCurrentDescription = binding.textCurrentDescription;
        final TextView textCurrentTemp = binding.textCurrentTemp;
        final TextView textCurrentTempMin = binding.textCurrentTempMin;
        final TextView textCurrentTempMax = binding.textCurrentTempMax;
        final TextView textCurrentWind = binding.textCurrentWind;
        final TextView textCurrentVisibility = binding.textCurrentVisibility;
        final ImageView imageCurrentIcon = binding.imageCurrentIcon;
        final TextView textForecastTime1 = binding.textForecastTime1;
        final TextView textForecastTime2 = binding.textForecastTime2;
        final TextView textForecastTime3 = binding.textForecastTime3;
        final TextView textForecastTime4 = binding.textForecastTime4;
        final ImageView imageForecast1 = binding.imageForecast1;
        final ImageView imageForecast2 = binding.imageForecast2;
        final ImageView imageForecast3 = binding.imageForecast3;
        final ImageView imageForecast4 = binding.imageForecast4;
        final TextView textForecastTemp1 = binding.textForecastTemp1;
        final TextView textForecastTemp2 = binding.textForecastTemp2;
        final TextView textForecastTemp3 = binding.textForecastTemp3;
        final TextView textForecastTemp4 = binding.textForecastTemp4;

        // SetText to TextView
        homeViewModel.getCurrentDate().observe(getViewLifecycleOwner(), text->{
            Date now = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            textCurrentDate.setText(df.format(now));
        });
        homeViewModel.getCurrentAddress().observe(getViewLifecycleOwner(), text->{
            textCurrentAddress.setText(MainActivity.country);
        });
        homeViewModel.getCurrentDescription().observe(getViewLifecycleOwner(), text->{
            textCurrentDescription.setText(CurrentWeather.data.get(CurrentWeather.DESCRIPTION));
        });
        homeViewModel.getCurrentTemp().observe(getViewLifecycleOwner(), text->{
            textCurrentTemp.setText(CurrentWeather.data.get(CurrentWeather.TEMP));
        });
        homeViewModel.getCurrentTempMin().observe(getViewLifecycleOwner(), text->{
            textCurrentTempMin.setText(
                    CurrentWeather.data.get(CurrentWeather.TEMP_MIN));
        });
        homeViewModel.getCurrentTempMax().observe(getViewLifecycleOwner(), text->{
            textCurrentTempMax.setText(
                    CurrentWeather.data.get(CurrentWeather.TEMP_MAX));
        });
        homeViewModel.getCurrentWind().observe(getViewLifecycleOwner(), text->{
            textCurrentWind.setText(CurrentWeather.data.get(CurrentWeather.WIND));
        });
        homeViewModel.getCurrentVisibility().observe(getViewLifecycleOwner(), text->{
            textCurrentVisibility.setText(CurrentWeather.data.get(CurrentWeather.VISIBILITY));
        });
        homeViewModel.getImageResource().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer resourceId) {
                imageCurrentIcon.setImageResource(CurrentWeather.getIconSource());
            }
        });
        homeViewModel.getForecastTime1().observe(getViewLifecycleOwner(), text->{
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 1);
            Date date = c.getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM", Locale.getDefault());
            textForecastTime1.setText(df.format(date));
        });
        homeViewModel.getForecastTime2().observe(getViewLifecycleOwner(), text->{
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 2);
            Date date = c.getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM", Locale.getDefault());
            textForecastTime2.setText(df.format(date));
        });
        homeViewModel.getForecastTime3().observe(getViewLifecycleOwner(), text->{
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 3);
            Date date = c.getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM", Locale.getDefault());
            textForecastTime3.setText(df.format(date));
        });
        homeViewModel.getForecastTime4().observe(getViewLifecycleOwner(), text->{
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 4);
            Date date = c.getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM", Locale.getDefault());
            textForecastTime4.setText(df.format(date));
        });
        homeViewModel.getForecastTemp1().observe(getViewLifecycleOwner(), text->{
            textForecastTemp1.setText(ForecastWeather.data.isEmpty() ? "Loading" : ForecastWeather.data.get(8).get(ForecastWeather.TEMP));
        });
        homeViewModel.getForecastTemp2().observe(getViewLifecycleOwner(), text->{
            textForecastTemp2.setText(ForecastWeather.data.isEmpty() ? "Loading" : ForecastWeather.data.get(16).get(ForecastWeather.TEMP));
        });
        homeViewModel.getForecastTemp3().observe(getViewLifecycleOwner(), text->{
            textForecastTemp3.setText(ForecastWeather.data.isEmpty() ? "Loading" : ForecastWeather.data.get(24).get(ForecastWeather.TEMP));
        });
        homeViewModel.getForecastTemp4().observe(getViewLifecycleOwner(), text->{
            textForecastTemp4.setText(ForecastWeather.data.isEmpty() ? "Loading" : ForecastWeather.data.get(32).get(ForecastWeather.TEMP));
        });
        homeViewModel.getForecastImg1().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer resourceId) {
                imageForecast1.setImageResource(ForecastWeather.getIconSource(8));
            }
        });
        homeViewModel.getForecastImg2().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer resourceId) {
                imageForecast2.setImageResource(ForecastWeather.getIconSource(16));
            }
        });
        homeViewModel.getForecastImg3().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer resourceId) {
                imageForecast3.setImageResource(ForecastWeather.getIconSource(24));
            }
        });
        homeViewModel.getForecastImg4().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer resourceId) {
                imageForecast4.setImageResource(ForecastWeather.getIconSource(32));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}