package com.icss.aasharambhoj;

public class IngredintDataModel {
    int image;
    String food_name;
    String kg;
    String price;
    String add;

    public IngredintDataModel(int image, String food_name, String kg, String price, String add) {
        this.image = image;
        this.food_name = food_name;
        this.kg = kg;
        this.price = price;
        this.add = add;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }
}
