package com.example.myapplication.ui.home;

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
    private final MutableLiveData<String> textCurrentTemp;
    private final MutableLiveData<String> textCurrentTempDetail;
    private final MutableLiveData<String> textCurrentWind;
    private final MutableLiveData<String> textCurrentVisibility;

    public HomeViewModel() {
        textCurrentDate = new MutableLiveData<>();
        textCurrentAddress = new MutableLiveData<>();
        textCurrentTemp = new MutableLiveData<>();
        textCurrentTempDetail = new MutableLiveData<>();
        textCurrentWind = new MutableLiveData<>();
        textCurrentVisibility = new MutableLiveData<>();
        mText = new MutableLiveData<>();
        mText.setValue("Loading...");
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        textCurrentDate.setValue(df.format(now));
        textCurrentAddress.setValue(MainActivity.address);
        textCurrentTemp.setValue(CurrentWeather.data.get(CurrentWeather.TEMP));
        textCurrentTempDetail.setValue(
                CurrentWeather.data.get(CurrentWeather.TEMP)+
                        ",MAX:"+CurrentWeather.data.get(CurrentWeather.TEMP_MAX)+
                        ",MIN:"+CurrentWeather.data.get(CurrentWeather.TEMP_MIN)
        );
        textCurrentWind.setValue(CurrentWeather.data.get(CurrentWeather.WIND));
        textCurrentVisibility.setValue(CurrentWeather.data.get(CurrentWeather.VISIBILITY));
    }

    public LiveData<String> getCurrentDate() {
        return textCurrentDate;
    }
    public LiveData<String> getCurrentAddress() {
        return textCurrentAddress;
    }
    public LiveData<String> getCurrentTemp() {
        return textCurrentTemp;
    }
    public LiveData<String> getCurrentTempDetail() {
        return textCurrentTempDetail;
    }
    public LiveData<String> getCurrentWind() {
        return textCurrentWind;
    }
    public LiveData<String> getCurrentVisibility() {
        return textCurrentVisibility;
    }
}