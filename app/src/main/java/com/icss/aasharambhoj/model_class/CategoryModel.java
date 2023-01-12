package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("name_hin")
    @Expose
    public String nameHin;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("created_date")
    @Expose
    public String createdDate;
    @SerializedName("modified_date")
    @Expose
    public Object modifiedDate;


}
