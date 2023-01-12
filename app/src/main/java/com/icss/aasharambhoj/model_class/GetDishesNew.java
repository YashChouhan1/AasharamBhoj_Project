package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDishesNew {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("name_hin")
    @Expose
    public String nameHin;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String cat_id;



}
