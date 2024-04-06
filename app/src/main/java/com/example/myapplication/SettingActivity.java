package com.example.myapplication;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Intent;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.api.GeocodingHandler;
import com.example.myapplication.model.MutableValue;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SettingActivity extends AppCompatActivity {
    //data sharing

    public final static String LANG = "lang";
    public final static String LANG_INDEX = "langIndex";
    public final static String UNIT = "unit";
    public final static String THEME = "theme";
    private String[] langs = {"en", "zh", "jp"};
    private int langIndex = 0;
    Spinner spinLang;
    RadioButton radioC, radioF;
    RadioButton radioLight, radioDark;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_setting);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar ();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled (true);
        actionBar.setTitle (R.string.menu_setting);
        // Find View
        spinLang = findViewById (R.id.spinner_language);
        radioC = findViewById (R.id.radio_celsius);
        radioF = findViewById (R.id.radio_fahrenheit);
        radioLight = findViewById (R.id.radio_light);
        radioDark = findViewById (R.id.radio_dark);
        // Pref
        SharedPreferences pref = this.getSharedPreferences ("Setting", MODE_PRIVATE);
        langIndex = pref.getInt (LANG_INDEX, 0);
        spinLang.setSelection (langIndex);
        if (pref.getString (UNIT, "C").equals ("F")) {
            radioF.setChecked (true);
        } else {
            radioC.setChecked (true);
        }
        if (pref.getString (THEME, "L").equals ("D")) {
            radioDark.setChecked (true);
        } else {
            radioLight.setChecked (true);
        }
        spinLang.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                langIndex = position;
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //search location[radio_group]
        RadioGroup radioGroup = findViewById (R.id.radio_group_location);
        AutoCompleteTextView autoCompleteTextView = findViewById (R.id.autoCompleteTextView);
        RadioButton radioButton_my_location = findViewById (R.id.radioButton_my_location);
        RadioButton radioButton_search_location = findViewById (R.id.radioButton_search_location);

        radioButton_my_location.setChecked (true);
        boolean isRadioButton_search_locationChecked = radioButton_search_location.isChecked ();
        if (radioButton_search_location.isChecked ()) {
            // RadioButton 被选中
            autoCompleteTextView.setVisibility (View.VISIBLE);
        } else {
            // RadioButton 未被选中
            autoCompleteTextView.setVisibility (View.GONE);
        }

        SharedPreferences sharedPreferences = getSharedPreferences ("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit ();
        radioGroup.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener () {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText (SettingActivity.this, "RadioGroup changed", Toast.LENGTH_SHORT).show ();
                MutableValue value=new MutableValue ();
                if (checkedId == R.id.radioButton_my_location){
                    autoCompleteTextView.setVisibility (View.GONE);
                    value.setRadioButton_search_locationChecked (false);
                    editor.putBoolean ("isRadioButton_search_locationChecked", value.isRadioButton_search_locationChecked ()); // 将"valueKey"替换为您的键
                    Toast.makeText (SettingActivity.this, "upLoading sharedPreferences="+value.isRadioButton_search_locationChecked (), Toast.LENGTH_SHORT).show ();
                } else if (checkedId == R.id.radioButton_search_location) {
                    autoCompleteTextView.setVisibility (View.VISIBLE);
                    value.setRadioButton_search_locationChecked (true);
                    editor.putBoolean ("isRadioButton_search_locationChecked", value.isRadioButton_search_locationChecked ()); // 将"valueKey"替换为您的键
                    Toast.makeText (SettingActivity.this, "upLoading sharedPreferences="+value.isRadioButton_search_locationChecked (), Toast.LENGTH_SHORT).show ();
                }
                editor.apply ();

            }
        });


        //search location[autoCompleteTextView]
        //GeocodingHandler geocodingHandler = new GeocodingHandler (new Geocoder (this));
        //for dynamic search result ,but will crash
        String[] SearchList = new String[]{
                "Shenzhen, China",
                "Macau, China",
                "Guangzhou, China",
                "Zhuhai, China",
                "Dongguan, China",
                "Foshan, China",
                "Zhongshan, China",
                "Huizhou, China",
                "Jiangmen, China",
                "Zhaoqing, China",
                "Heyuan, China",
                "Shantou, China",
                "Shaoguan, China",
                "Qingyuan, China",
                "Zhanjiang, China",
                "Maoming, China",
                "Yangjiang, China",
                "Chaozhou, China",
                "Jieyang, China",
                "Meizhou, China",
                "Shanwei, China",
                "Yunfu, China",
                "Nansha, China",
                "Heshan, China",
                "Panyu, China",
                "Conghua, China",
                "Taishan, China",
                "Enping, China",
                "Kaiping, China",
                "Heping, China",
                "Lantau Island, Hong Kong",
                "Hong Kong,China",
                "Paris, France",
                "New York City, USA",
                "Tokyo, Japan",
                "London, UK",
                "Rome, Italy",
                "Sydney, Australia",
                "Cairo, Egypt",
                "Rio de Janeiro, Brazil",
                "Moscow, Russia",
                "Cape Town, South Africa",
                "Barcelona, Spain",
                "Dubai, United Arab Emirates",
                "Toronto, Canada",
                "Berlin, Germany",
                "Mumbai, India",
                "Amsterdam, Netherlands",
                "Bangkok, Thailand",
                "Istanbul, Turkey",
                "Seoul, South Korea",
                "Athens, Greece",
                "Nairobi, Kenya",
                "Havana, Cuba",
                "Buenos Aires, Argentina",
                "Dublin, Ireland",
                "Prague, Czech Republic",
                "Stockholm, Sweden",
                "Beijing, China",
                "Mexico City, Mexico",
                "Zurich, Switzerland",
                "Cusco, Peru",
                "Helsinki, Finland",
                "Auckland, New Zealand",
                "Lima, Peru",
                "Cologne, Germany",
                "Brussels, Belgium",
                "Santiago, Chile",
                "Hamburg, Germany",
                "Phuket, Thailand",
                "Florence, Italy",
                "Seville, Spain",
                "Oslo, Norway",
                "Munich, Germany",
                "Krakow, Poland",
                "Casablanca, Morocco",
                "Marrakech, Morocco",
                "Mombasa, Kenya",
                "Victoria Falls, Zimbabwe",
                "Reykjavik, Iceland",
                "Jerusalem, Israel",
                "Singapore",
                "Lisbon, Portugal",
                "Vienna, Austria",
                "Edinburgh, Scotland",
                "Delhi, India",
                "Copenhagen, Denmark",
                "Hanoi, Vietnam",
                "Johannesburg, South Africa",
                "São Paulo, Brazil",
                "Budapest, Hungary",
                "Kuala Lumpur, Malaysia",
                "Kyoto, Japan",
                "Vancouver, Canada",
                "Wellington, New Zealand",
                "Rio de Janeiro, Brazil",
                "Marrakech, Morocco",
                "Sydney, Australia",
                "Barcelona, Spain",
                "Mexico City, Mexico",
                "Tokyo, Japan",
                "Rome, Italy",
                "Berlin, Germany",
                "Paris, France",
                "Prague, Czech Republic",
                "Cape Town, South Africa",
                "Moscow, Russia",
                "Cairo, Egypt",
                "New York City, USA",
                "London, UK",
                "Amsterdam, Netherlands",
                "Bangkok, Thailand",
                "Istanbul, Turkey",
                "Seoul, South Korea",
                "Athens, Greece",
                "Nairobi, Kenya",
                "Buenos Aires, Argentina",
                "Dublin, Ireland",
                "Stockholm, Sweden",
                "Beijing, China",
                "Toronto, Canada",
                "Zurich, Switzerland",
                "Helsinki, Finland",
                "Lima, Peru",
                "Oslo, Norway",
                "Brussels, Belgium",
                "Copenhagen, Denmark",
                "Hanoi, Vietnam",
                "Lisbon, Portugal",
                "Vienna, Austria",
                "Delhi, India",
                "Singapore",
                "Kuala Lumpur, Malaysia",
                "Budapest, Hungary",
                "Vancouver, Canada",
                "Wellington, New Zealand",
                "Jerusalem, Israel",
                "Edinburgh, Scotland",
                "Copenhagen, Denmark",
                "Hanoi, Vietnam",
                "Stockholm, Sweden",
                "Amsterdam, Netherlands",
                "Lisbon, Portugal",
                "Vienna, Austria",
                "Prague, Czech Republic",
                "Dublin, Ireland",
                "Budapest, Hungary",
                "Helsinki, Finland",
                "Brussels, Belgium",
                "Oslo, Norway",
                "Copenhagen, Denmark",
                "Athens, Greece",
                "Berlin, Germany",
                "Buenos Aires, Argentina",
                "Delhi, India",
                "Bangkok, Thailand",
                "Istanbul, Turkey",
                "Cairo, Egypt",
                "Marrakech, Morocco",
                "Nairobi, Kenya",
                "Cape Town, South Africa",
                "Barcelona, Spain",
                "Rome, Italy",
                "Sydney, Australia",
                "Tokyo, Japan",
                "Moscow, Russia",
                "Dubai, United Arab Emirates",

        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,
                android.R.layout.simple_list_item_1, SearchList);
        autoCompleteTextView.setAdapter (adapter);


        //get input (when press enter)
        autoCompleteTextView.setOnEditorActionListener (new TextView.OnEditorActionListener () {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getKeyCode () == KeyEvent.KEYCODE_ENTER) {
                    MutableValue value = new MutableValue ();
                    value.setUserInput (autoCompleteTextView.getText ().toString ());
                    // 使用最终输入的 inputText 执行操作
                    editor.putString ("MutableValue", value.getUserInput ());
                    editor.apply ();// 将"valueKey"替换为您的键
                    Toast.makeText (SettingActivity.this, "upLoading sharedPreferences="+value.getUserInput (), Toast.LENGTH_SHORT).show ();

                    Intent intent = new Intent (SettingActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        //get input (when suggested option is clicked)
        autoCompleteTextView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition (position).toString ();
                // 使用所选的建议选项 selectedItem 执行操作
                editor.putString ("UserInput", selectedItem); // 将"valueKey"替换为您的键
                editor.apply ();
                Toast.makeText (SettingActivity.this, "upLoading sharedPreferences="+selectedItem, Toast.LENGTH_SHORT).show ();

                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


    private void setAppLocale(String languageCode) {
        Locale locale = new Locale (languageCode);
        Locale.setDefault (locale);

        Configuration configuration = getResources ().getConfiguration ();
        configuration.setLocale (locale);
        getResources ().updateConfiguration (configuration, getResources ().getDisplayMetrics ());
    }

    private void upDateSetting() {
        String lang = langs[langIndex];
        setAppLocale (lang);
        SharedPreferences pref = this.getSharedPreferences ("Setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit ();
        //language
        editor.putString (LANG, lang);
        editor.putInt (LANG_INDEX, langIndex);
        //temp type
        if (radioC.isChecked ()) {
            editor.putString (UNIT, "C");
        } else if (radioF.isChecked ()) {
            editor.putString (UNIT, "F");
        }
        //theme
        if (radioDark.isChecked ()) {
            editor.putString (THEME, "D");
        } else if (radioLight.isChecked ()) {
            editor.putString (THEME, "L");
        }
        if (radioC.isChecked ()) {

        } else if (radioF.isChecked ()) {

        }
        editor.apply ();
        langIndex = pref.getInt (LANG_INDEX, 0);
        Log.d ("Setting", "" + langIndex);
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId ()) {
            case android.R.id.home:
                upDateSetting ();
                this.finish ();
                return true;
        }
        return super.onOptionsItemSelected (item);
    }

    public void setvalue(boolean a,boolean value){
         a=value;
    }
}
