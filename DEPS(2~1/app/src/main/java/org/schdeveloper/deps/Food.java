package org.schdeveloper.deps;

public class Food {
    //음식의 이름
    String name;
    //문화권
    String culture;
    //영양소
    Nutrient nuts;

    public Food() {
        this.name = "-";
        this.culture = "한식";
    }

    public Food(String name) {
        this.name = name;
        this.culture = "한식";
    }

    public Food(String name, String culture) {
        this.name = name;
        this.culture = culture;
    }

    public Food(String name, String culture, Nutrient nuts) {
        this.name = name;
        this.culture = culture;
        this.nuts = nuts;
    }

    public Nutrient getNuts() {
        return nuts;
    }

    public void setNuts(Nutrient nuts) {
        this.nuts = nuts;
    }

    public String getCulture() {
        return culture;
    }

    public String getName() {
        return name;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public void setName(String name) {
        this.name = name;
    }
}