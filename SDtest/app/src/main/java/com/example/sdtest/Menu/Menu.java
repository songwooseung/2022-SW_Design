package com.example.sdtest.Menu;

import java.util.Comparator;

public class Menu {
    private String name;
    private int views = 0;
    private Recipe recipe = new Recipe();
    // private MenuList menuList;

    public void addViews() {
        views += 1;
    }

    // set & get
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getViews() {
        return views;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}

class MenuViewsComparator implements Comparator<Menu> {
    public int compare(Menu m1, Menu m2) {
        if (m1.getViews() > m2.getViews()) {
            return -1;
        } else if (m1.getViews() < m2.getViews()) {
            return 1;
        }
        return 0;
    }
}

class MenuRatioComparator implements Comparator<Menu> {
    public int compare(Menu m1, Menu m2) {
        if (m1.getRecipe().getIngredientRatio() > m2.getRecipe().getIngredientRatio()) {
            return -1;
        } else if (m1.getRecipe().getIngredientRatio() < m2.getRecipe().getIngredientRatio()) {
            return 1;
        }
        return 0;
    }
}