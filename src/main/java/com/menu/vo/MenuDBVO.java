package com.menu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public class MenuDBVO {
    Date ymd;//년월일
    String foodNm;//음식명
    String ingredientsNm;//재료명
    String mealNm;//조식,중식,석식,간식

    public Date getYmd() {
        return ymd;
    }

    public void setYmd(Date ymd) {
        this.ymd = ymd;
    }

    public String getFoodNm() {
        return foodNm;
    }

    public void setFoodNm(String foodNm) {
        this.foodNm = foodNm;
    }

    public String getIngredientsNm() {
        return ingredientsNm;
    }

    public void setIngredientsNm(String ingredientsNm) {
        this.ingredientsNm = ingredientsNm;
    }

    public String getMealNm() {
        return mealNm;
    }

    public void setMealNm(String mealNm) {
        this.mealNm = mealNm;
    }
}
