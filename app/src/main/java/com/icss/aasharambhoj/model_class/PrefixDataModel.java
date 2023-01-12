package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrefixDataModel {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("menu_name")
    @Expose
    public String menuName;
    @SerializedName("category_id")
    @Expose
    public Integer categoryId;
    @SerializedName("dish_ids")
    @Expose
    public String dishIds;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("name_hin")
    @Expose
    public String nameHin;
    @SerializedName("dishes")
    @Expose
    public List<PrefixDishModel> dishes = null;
}
