package com.example.myapplication.ui.forecast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.ForecastWeather;

import java.util.*;

public class ForecastViewModel  extends ViewModel {
    private MutableLiveData<ArrayList<HashMap<String, String>>> forecastList;

    public ForecastViewModel() {
        forecastList = new MutableLiveData<>();
        forecastList.setValue(ForecastWeather.data);
    }

    public LiveData<ArrayList<HashMap<String, String>>> getList() {
        return forecastList;
    }
}
