package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient__1 {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("dish_id")
    @Expose
    public Integer dishId;
    @SerializedName("ingredient_id")
    @Expose
    public Integer ingredientId;
    @SerializedName("quantity")
    @Expose
    public Integer quantity;
    @SerializedName("unit_id")
    @Expose
    public Integer unitId;
    @SerializedName("ingi_name")
    @Expose
    public String ingiName;
    @SerializedName("ingi_name_hin")
    @Expose
    public String ingiNameHin;
    @SerializedName("unit_eng")
    @Expose
    public String unitEng;
    @SerializedName("unit_hin")
    @Expose
    public String unitHin;
    @SerializedName("final_quantity")
    @Expose
    public String finalQuantity;
}
