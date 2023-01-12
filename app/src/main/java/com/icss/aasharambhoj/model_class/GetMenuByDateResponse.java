package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMenuByDateResponse {
    @SerializedName("response")
    @Expose
    public Boolean response;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<GetMenuByDate> data = null;
}
