package com.example.myapplication.ui.history;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.CurrentWeather;
import com.example.myapplication.model.HistoricalWeather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ViewHistoryModel extends ViewModel {
    private MutableLiveData<ArrayList<HashMap<String, String>>> historicalList;

    public ViewHistoryModel(){
        historicalList = new MutableLiveData<>();
        historicalList.setValue(HistoricalWeather.data);
    }

    public LiveData<ArrayList<HashMap<String, String>>> getList() {
        return historicalList;
    }
}
