package com.menu.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRecipe {//순서1
    String foodName;//음식명
    String ingredientsName;//재료명

    public MenuRecipe(String foodName, String ingredientsName) {
        this.foodName = foodName;
        this.ingredientsName = ingredientsName;
    }
}
