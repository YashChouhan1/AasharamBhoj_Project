package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientModelData {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("menu_detail")
    @Expose
    public List<MenuDetail> menuDetail = null;


}
