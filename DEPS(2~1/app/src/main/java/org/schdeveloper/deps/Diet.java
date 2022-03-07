package org.schdeveloper.deps;

import java.util.ArrayList;

public class Diet {
    //식단번호
    String number;
    //식단이름
    String name;
    //선호도
    int like;
    //선호식단의 이름을 SQL로 검색해서 속한 음식들을 저장한다.
    ArrayList<Food> foods = new ArrayList<>();
    //식단에 대한 총칼로리, 탄수화물, 단백질, 지방
    Nutrient allNuts;

    public Diet() {
        this.name = "-";
        this.like = 0;
    }

    public Diet(String number, String name) {
        this.number = number;
        this.name = name;
        this.like = 0;
    }

    public Diet(String number, int like) {
        this.number = number;
        this.name = "";
        this.like = like;
    }

    public Diet(Diet diet) {
        this.name = diet.getName();
        this.like = diet.getLike();
        this.foods = diet.getFoods();
        this.allNuts = diet.getAllNuts();
    }

    public Diet(Diet diet, int like) {
        this.name = diet.getName();
        this.like = like;
        this.foods = diet.getFoods();
        this.allNuts = diet.getAllNuts();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Nutrient getAllNuts() {
        return allNuts;
    }

    public void setAllNuts(Nutrient allNuts) {
        this.allNuts = allNuts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
}
