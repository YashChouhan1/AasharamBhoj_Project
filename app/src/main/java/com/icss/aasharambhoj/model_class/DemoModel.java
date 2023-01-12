package com.icss.aasharambhoj.model_class;

import java.util.List;

public class DemoModel {
    private String id ;

    public String getId() {
        return id;
    }

    public List<String> getData() {
        return data;
    }

    public DemoModel(String id, List<String> data) {
        this.id = id;
        this.data = data;
    }

    private List<String> data;
}
