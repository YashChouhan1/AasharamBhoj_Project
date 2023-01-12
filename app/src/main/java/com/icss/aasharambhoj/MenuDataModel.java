package com.icss.aasharambhoj;

import android.widget.TextView;

public class MenuDataModel {

    String food_name;

    public MenuDataModel(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }
}