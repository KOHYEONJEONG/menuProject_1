package com.menu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public class MenuDBVO {
    Date ymd;
    String foodNm;
    String ingredientsNm;
    String mealNm;

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
