package com.icss.aasharambhoj;

public class RajBhogDataModel {
    String  Ingredint;
    String kg;

    public RajBhogDataModel(String ingredint, String kg) {
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
