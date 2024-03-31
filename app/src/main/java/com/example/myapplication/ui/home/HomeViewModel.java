package com.example.myapplication.ui.home;

import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.MainActivity;
import com.example.myapplication.model.CurrentWeather;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> textCurrentDate;
    private final MutableLiveData<String> textCurrentAddress;
    private final MutableLiveData<String> textCurrentDescription;
    private final MutableLiveData<String> textCurrentTemp;
    private final MutableLiveData<String> textCurrentTempMin;
    private final MutableLiveData<String> textCurrentTempMax;
    private final MutableLiveData<String> textCurrentWind;
    private final MutableLiveData<String> textCurrentVisibility;
    private final MutableLiveData<Integer> imageCurrentImage;

    public HomeViewModel() {
        textCurrentDate = new MutableLiveData<>();
        textCurrentAddress = new MutableLiveData<>();
        textCurrentDescription = new MutableLiveData<>();
        textCurrentTemp = new MutableLiveData<>();
        textCurrentTempMin = new MutableLiveData<>();
        textCurrentTempMax = new MutableLiveData<>();
        textCurrentWind = new MutableLiveData<>();
        textCurrentVisibility = new MutableLiveData<>();
        imageCurrentImage = new MutableLiveData<>();
        mText = new MutableLiveData<>();
        Log.d("HomeModel",CurrentWeather.data.toString());
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        textCurrentDate.setValue(df.format(now));
        textCurrentAddress.setValue(MainActivity.country);
        textCurrentDescription.setValue(CurrentWeather.data.get(CurrentWeather.DESCRIPTION));
        textCurrentTemp.setValue(CurrentWeather.data.get(CurrentWeather.TEMP));
        textCurrentTempMin.setValue(CurrentWeather.data.get(CurrentWeather.TEMP_MIN));
        textCurrentTempMax.setValue(CurrentWeather.data.get(CurrentWeather.TEMP_MAX));
        textCurrentWind.setValue(CurrentWeather.data.get(CurrentWeather.WIND));
        textCurrentVisibility.setValue(CurrentWeather.data.get(CurrentWeather.VISIBILITY));
        imageCurrentImage.setValue(CurrentWeather.getIconSource());
    }

    public LiveData<String> getCurrentDate() {
        return textCurrentDate;
    }
    public LiveData<String> getCurrentAddress() {
        return textCurrentAddress;
    }
    public LiveData<String> getCurrentDescription() { return textCurrentDescription; }
    public LiveData<String> getCurrentTemp() { return textCurrentTemp; }
    public LiveData<String> getCurrentTempMin() { return textCurrentTempMin; }
    public LiveData<String> getCurrentTempMax() { return textCurrentTempMax; }
    public LiveData<String> getCurrentWind() {
        return textCurrentWind;
    }
    public LiveData<String> getCurrentVisibility() { return textCurrentVisibility; }
    public LiveData<Integer> getImageResource() { return imageCurrentImage; }
    public void setImageResource(int resourceId) {
        imageCurrentImage.setValue(resourceId);
    }
}