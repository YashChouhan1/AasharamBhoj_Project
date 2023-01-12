public class DishDataModel {
    String dish;
    String kg;
    String people;

    public DishDataModel(String dish, String kg, String people) {
        this.dish = dish;
        this.kg = kg;
        this.people = people;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
