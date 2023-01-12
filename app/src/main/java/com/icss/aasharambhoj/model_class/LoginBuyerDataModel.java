package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.SerializedName;

public class LoginBuyerDataModel {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("email_verified_at")
    private Object emailVerifiedAt;
    @SerializedName("password")
    private String password;
    @SerializedName("type")
    private String type;
    @SerializedName("status")
    private String status;
    @SerializedName("created_date")
    private String createdDate;
    @SerializedName("modified_date")
    private Object modifiedDate;
}
