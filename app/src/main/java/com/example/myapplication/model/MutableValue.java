package com.example.myapplication.model;

public class MutableValue {
    private boolean isRadioButton_search_locationChecked;
    private String UserInput;

    public String getUserInput() {
        return UserInput;
    }

    public void setUserInput(String userInput) {
        UserInput = userInput;
    }

    public boolean isRadioButton_search_locationChecked() {
        return isRadioButton_search_locationChecked;
    }

    public void setRadioButton_search_locationChecked(boolean radioButton_search_locationChecked) {
        isRadioButton_search_locationChecked = radioButton_search_locationChecked;
    }
}
