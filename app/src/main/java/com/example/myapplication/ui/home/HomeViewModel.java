package com.example.myapplication.ui.home;

import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.MainActivity;
import com.example.myapplication.model.CurrentWeather;
import com.example.myapplication.model.ForecastWeather;

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
    private final MutableLiveData<String> textForecastTime1;
    private final MutableLiveData<String> textForecastTime2;
    private final MutableLiveData<String> textForecastTime3;
    private final MutableLiveData<String> textForecastTime4;
    private final MutableLiveData<Integer> imageForecast1;
    private final MutableLiveData<Integer> imageForecast2;
    private final MutableLiveData<Integer> imageForecast3;
    private final MutableLiveData<Integer> imageForecast4;
    private final MutableLiveData<String> textForecastTemp1;
    private final MutableLiveData<String> textForecastTemp2;
    private final MutableLiveData<String> textForecastTemp3;
    private final MutableLiveData<String> textForecastTemp4;

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
        textForecastTime1 = new MutableLiveData<>();
        textForecastTime2 = new MutableLiveData<>();
        textForecastTime3 = new MutableLiveData<>();
        textForecastTime4 = new MutableLiveData<>();
        imageForecast1 = new MutableLiveData<>();
        imageForecast2 = new MutableLiveData<>();
        imageForecast3 = new MutableLiveData<>();
        imageForecast4 = new MutableLiveData<>();
        textForecastTemp1 = new MutableLiveData<>();
        textForecastTemp2 = new MutableLiveData<>();
        textForecastTemp3 = new MutableLiveData<>();
        textForecastTemp4 = new MutableLiveData<>();
        Log.d("HomeModel",CurrentWeather.data.toString());
        Calendar c = Calendar.getInstance();
        Date now = c.getTime();
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
        SimpleDateFormat dm = new SimpleDateFormat("dd-MMM", Locale.getDefault());
        c.add(Calendar.DAY_OF_YEAR, 1);
        textForecastTime1.setValue(dm.format(c.getTime()));
        c.add(Calendar.DAY_OF_YEAR, 1);
        textForecastTime2.setValue(dm.format(c.getTime()));
        c.add(Calendar.DAY_OF_YEAR, 1);
        textForecastTime3.setValue(dm.format(c.getTime()));
        c.add(Calendar.DAY_OF_YEAR, 1);
        textForecastTime4.setValue(dm.format(c.getTime()));
        imageForecast1.setValue(ForecastWeather.getIconSource(8));
        imageForecast2.setValue(ForecastWeather.getIconSource(16));
        imageForecast3.setValue(ForecastWeather.getIconSource(24));
        imageForecast4.setValue(ForecastWeather.getIconSource(32));
        //Log.d("HomeModel Forecast",ForecastWeather.data.get(0).toString());
        textForecastTemp1.setValue(ForecastWeather.data.isEmpty() ? "Loading" : ForecastWeather.data.get(8).get(ForecastWeather.TEMP));
        textForecastTemp2.setValue(ForecastWeather.data.isEmpty() ? "Loading" : ForecastWeather.data.get(16).get(ForecastWeather.TEMP));
        textForecastTemp3.setValue(ForecastWeather.data.isEmpty() ? "Loading" : ForecastWeather.data.get(24).get(ForecastWeather.TEMP));
        textForecastTemp4.setValue(ForecastWeather.data.isEmpty() ? "Loading" : ForecastWeather.data.get(32).get(ForecastWeather.TEMP));


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
    public LiveData<String> getForecastTime1() {
        return textForecastTime1;
    }
    public LiveData<String> getForecastTime2() {
        return textForecastTime2;
    }
    public LiveData<String> getForecastTime3() {
        return textForecastTime3;
    }
    public LiveData<String> getForecastTime4() {
        return textForecastTime4;
    }
    public LiveData<String> getForecastTemp1() {
        return textForecastTemp1;
    }
    public LiveData<String> getForecastTemp2() { return textForecastTemp2; }
    public LiveData<String> getForecastTemp3() { return textForecastTemp3; }
    public LiveData<String> getForecastTemp4() { return textForecastTemp4; }
    public LiveData<Integer> getForecastImg1() { return imageForecast1;}
    public LiveData<Integer> getForecastImg2() { return imageForecast2;}
    public LiveData<Integer> getForecastImg3() { return imageForecast3;}
    public LiveData<Integer> getForecastImg4() { return imageForecast4;}
    public void setImageResource(int resourceId) {
        imageCurrentImage.setValue(resourceId);
    }
    public void setForecastImg1(int resourceId) {
        imageForecast1.setValue(resourceId);
    }
    public void setForecastImg2(int resourceId) {
        imageForecast2.setValue(resourceId);
    }
    public void setForecastImg3(int resourceId) {
        imageForecast3.setValue(resourceId);
    }
    public void setForecastImg4(int resourceId) {
        imageForecast4.setValue(resourceId);
    }
}