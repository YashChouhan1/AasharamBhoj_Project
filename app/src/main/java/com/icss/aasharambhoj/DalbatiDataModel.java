package com.icss.aasharambhoj;

public class DalbatiDataModel {
    String  Ingredint;
    String kg;

    public DalbatiDataModel(String ingredint, String kg) {
        Ingredint = ingredint;
        this.kg = kg;
    }

    public String getIngredint() {
        return Ingredint;
    }

    public void setIngredint(String ingredint) {
        Ingredint = ingredint;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }
}
