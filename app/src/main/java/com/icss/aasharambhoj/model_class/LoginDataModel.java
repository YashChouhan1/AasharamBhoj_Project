package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDataModel {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("email_verified_at")
    @Expose
    public Object emailVerifiedAt;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("type")
    @Expose
    public String type;
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
