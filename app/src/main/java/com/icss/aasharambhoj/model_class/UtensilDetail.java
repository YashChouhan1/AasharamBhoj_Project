package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UtensilDetail {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("chef_id")
    @Expose
    public Integer chefId;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("category_id")
    @Expose
    public Integer categoryId;
    @SerializedName("utensil_id")
    @Expose
    public Integer utensilId;
    @SerializedName("quantity")
    @Expose
    public Integer quantity;
    @SerializedName("created_date")
    @Expose
    public String createdDate;
    @SerializedName("modified_date")
    @Expose
    public String modifiedDate;
    @SerializedName("ut_name")
    @Expose
    public String utName;
    @SerializedName("ut_name_hin")
    @Expose
    public String utNameHin;
}
