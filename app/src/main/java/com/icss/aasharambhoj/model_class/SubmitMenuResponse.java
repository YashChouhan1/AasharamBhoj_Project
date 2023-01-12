package com.icss.aasharambhoj.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitMenuResponse {

    @SerializedName("response")
    @Expose
    public Boolean response;
    @SerializedName("message")
    @Expose
    public String message;
}
