package com.icss.aasharambhoj.model_class.remember;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RememberInitRes {
    @SerializedName("response")
    @Expose
    private Boolean response;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<RememberRes> data = null;

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RememberRes> getData() {
        return data;
    }

    public void setData(List<RememberRes> data) {
        this.data = data;
    }
}
