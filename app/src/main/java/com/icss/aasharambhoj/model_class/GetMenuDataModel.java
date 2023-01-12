package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMenuDataModel {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("menu_name")
    @Expose
    public String menuName;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("menu_detail")
    @Expose
    public List<GetMenuDetailsModel> menuDetail = null;



}
