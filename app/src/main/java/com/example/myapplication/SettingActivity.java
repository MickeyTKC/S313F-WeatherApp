package com.example.myapplication;

import static com.google.android.material.internal.ContextUtils.getActivity;

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

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SettingActivity extends AppCompatActivity {
    //data sharing

    public final static String LANG = "lang";
    public final static String LANG_INDEX = "langIndex";
    public final static String TEMP_TYPE = "tempType";
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
        if (pref.getString (TEMP_TYPE, "C").equals ("F")) {
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
                if (checkedId == R.id.radioButton_my_location){
                    autoCompleteTextView.setVisibility (View.GONE);
                    setvalue (isRadioButton_search_locationChecked,false);
                    editor.putBoolean ("isRadioButton_search_locationChecked", isRadioButton_search_locationChecked); // 将"valueKey"替换为您的键
                    Toast.makeText (SettingActivity.this, "upLoading sharedPreferences="+isRadioButton_search_locationChecked, Toast.LENGTH_SHORT).show ();
                } else if (checkedId == R.id.radioButton_search_location) {
                    autoCompleteTextView.setVisibility (View.VISIBLE);
                    setvalue (isRadioButton_search_locationChecked,true);
                    editor.putBoolean ("isRadioButton_search_locationChecked", isRadioButton_search_locationChecked); // 将"valueKey"替换为您的键
                    Toast.makeText (SettingActivity.this, "upLoading sharedPreferences="+isRadioButton_search_locationChecked, Toast.LENGTH_SHORT).show ();
                }
                editor.apply ();

            }
        });


        //search location[autoCompleteTextView]
        //GeocodingHandler geocodingHandler = new GeocodingHandler (new Geocoder (this));
        //for dynamic search result ,but will crash
        String[] SearchList = new String[]{
                "Shenzhen", "Macau", "Guangzhou", "Zhuhai", "Dongguan", "Foshan", "Zhongshan",
                "Huizhou", "Jiangmen", "Zhaoqing", "Heyuan", "Shantou", "Shaoguan", "Qingyuan",
                "Zhanjiang", "Maoming", "Yangjiang", "Chaozhou", "Jieyang", "Meizhou", "Shanwei",
                "Yunfu", "Nansha", "Heshan", "Panyu", "Conghua", "Taishan", "Enping", "Kaiping",
                "Heping", "Lantau Island", "Hong Kong"

        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,
                android.R.layout.simple_list_item_1, SearchList);
        autoCompleteTextView.setAdapter (adapter);

        autoCompleteTextView.addTextChangedListener (new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 在文本改變之前調用此方法


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 在文本正在改變時調用此方法
                // 在文本改變後調用此方法
                //String searchText = s.toString ();
                //geocodingHandler.performGeocoding (searchText);
                //SearchList[0] = null;
                //for (int i = 0; i <= geocodingHandler.getStringResultSet ().size()-1; i++ ) {
                //    SearchList[i]=geocodingHandler.getStringResultSet().get (i);
                //}
                //for dynamic search result ,but will crash
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        //get input (when press enter)
        autoCompleteTextView.setOnEditorActionListener (new TextView.OnEditorActionListener () {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getKeyCode () == KeyEvent.KEYCODE_ENTER) {
                    String inputText = autoCompleteTextView.getText ().toString ();
                    // 使用最终输入的 inputText 执行操作
                    editor.putString ("UserInput", inputText); // 将"valueKey"替换为您的键
                    Toast.makeText (SettingActivity.this, "upLoading sharedPreferences="+inputText, Toast.LENGTH_SHORT).show ();
                    editor.apply ();
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
                Toast.makeText (SettingActivity.this, "upLoading sharedPreferences="+selectedItem, Toast.LENGTH_SHORT).show ();
                editor.apply ();
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
            editor.putString (TEMP_TYPE, "C");
        } else if (radioF.isChecked ()) {
            editor.putString (TEMP_TYPE, "F");
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
