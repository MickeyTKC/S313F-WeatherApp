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
        Log.d("onViewCreated","Testing");
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}