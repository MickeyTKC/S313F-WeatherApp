package com.example.myapplication;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity {
    public final static String LANG = "lang";
    public final static String LANG_INDEX = "langIndex";
    public final static String TEMP_TYPE = "tempType";
    public final static String THEME = "theme";
    private String[] langs = {"en","zh","jp"};
    private int langIndex = 0;
    Spinner spinLang;
    RadioButton radioC,radioF;
    RadioButton radioLight,radioDark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.menu_setting);
        // Find View
        spinLang = findViewById(R.id.spinner_language);
        radioC = findViewById(R.id.radio_celsius);
        radioF = findViewById(R.id.radio_fahrenheit);
        radioLight = findViewById(R.id.radio_light);
        radioDark = findViewById(R.id.radio_dark);
        // Pref
        SharedPreferences pref = this.getSharedPreferences("Setting",MODE_PRIVATE);
        langIndex = pref.getInt(LANG_INDEX,0);
        spinLang.setSelection(langIndex);
        if(pref.getString(TEMP_TYPE,"C").equals("F")){
            radioF.setChecked(true);
        }else{
            radioC.setChecked(true);
        }
        if(pref.getString(THEME,"L").equals("D")){
            radioDark.setChecked(true);
        }else{
            radioLight.setChecked(true);
        }
        spinLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                langIndex = position;
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }
    private void setAppLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }
    private void upDateSetting(){
        String lang = langs[langIndex];
        setAppLocale(lang);
        SharedPreferences pref = this.getSharedPreferences("Setting",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //language
        editor.putString(LANG,lang);
        editor.putInt(LANG_INDEX,langIndex);
        //temp type
        if(radioC.isChecked()){
            editor.putString(TEMP_TYPE,"C");
        }else if (radioF.isChecked()){
            editor.putString(TEMP_TYPE,"F");
        }
        //theme
        if(radioDark.isChecked()){
            editor.putString(THEME,"D");
        }else if (radioLight.isChecked()){
            editor.putString(THEME,"L");
        }
        if(radioC.isChecked()){

        }else if (radioF.isChecked()){

        }
        editor.apply();
        langIndex = pref.getInt(LANG_INDEX,0);
        Log.d("Setting",""+langIndex);
    }
    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                upDateSetting();
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}