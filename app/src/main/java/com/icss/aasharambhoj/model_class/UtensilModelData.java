package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UtensilModelData {
    @SerializedName("category_id")
    @Expose
    public Integer categoryId;
    @SerializedName("cate_name")
    @Expose
    public String cateName;
    @SerializedName("cate_name_hin")
    @Expose
    public String cateNameHin;
    @SerializedName("utensil_detail")
    @Expose
    public List<UtensilDetail> utensilDetail = null;


}
