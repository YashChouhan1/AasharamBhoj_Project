package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMenuDetailsModel {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("menu_name")
    @Expose
    public String menuName;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("category_id")
    @Expose
    public Integer categoryId;
    @SerializedName("dish_id")
    @Expose
    public Integer dishId;
    @SerializedName("quantity")
    @Expose
    public Integer quantity;
    @SerializedName("serving_type")
    @Expose
    public String servingType;
    @SerializedName("created_date")
    @Expose
    public String createdDate;
    @SerializedName("modified_date")
    @Expose
    public Object modifiedDate;
    @SerializedName("cate_name")
    @Expose
    public String cateName;
    @SerializedName("cate_name_hin")
    @Expose
    public String cateNameHin;
    @SerializedName("dish_name")
    @Expose
    public String dishName;
    @SerializedName("dish_name_hin")
    @Expose
    public String dishNameHin;
}
