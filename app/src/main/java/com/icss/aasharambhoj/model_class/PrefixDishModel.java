package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrefixDishModel {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("name_hin")
    @Expose
    public String nameHin;
    @SerializedName("serving_type")
    @Expose
    public String servingType;
}

