package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngrModelMainData {
    @SerializedName("response")
    @Expose
    public Boolean response;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("ingredient_list")
    @Expose
    public List<IngredientModelData> ingredientList = null;
    @SerializedName("utensil_list")
    @Expose
    public List<UtensilModelData> utensilList = null;


}
