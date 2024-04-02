package com.example.myapplication.ui.history;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.CurrentWeather;
import com.example.myapplication.model.HistoricalWeather;

import java.util.Objects;

public class ViewHistoryModel extends ViewModel {
    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> tempMin;

    public ViewHistoryModel() {
        mText = new MutableLiveData<>();
        tempMin = new MutableLiveData<>();
        mText.setValue("This is view history fragment");
        tempMin.setValue(HistoricalWeather.data.get(HistoricalWeather.TEMP_MIN));
        Log.d("Test temp min", HistoricalWeather.TEMP_MIN);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<String> getTempMin() {
        return tempMin;
    }
}
