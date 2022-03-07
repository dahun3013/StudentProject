package org.schdeveloper.deps;

public class Nutrient {
    //3대 영양소와 칼로리
    double kcal;
    double carbo;
    double prote;
    double fat;

    public Nutrient(double kcal, double carbo, double prote, double fat) {
        this.kcal = kcal;
        this.carbo = carbo;
        this.prote = prote;
        this.fat = fat;
    }

    public double getCarbo() {
        return carbo;
    }

    public double getFat() {
        return fat;
    }

    public double getKcal() {
        return kcal;
    }

    public double getProte() {
        return prote;
    }

    public void setCarbo(double carbo) {
        this.carbo = carbo;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public void setProte(double prote) {
        this.prote = prote;
    }
}
