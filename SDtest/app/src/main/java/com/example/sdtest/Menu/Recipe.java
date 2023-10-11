package com.example.sdtest.Menu;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Recipe {
    private ArrayList<String> contents = new ArrayList<String>();
    private ArrayList<Bitmap> pictures = new ArrayList<Bitmap>();
    private ArrayList<Ingredient> neededIngredients = new ArrayList<Ingredient>();
    private double ingredientRatio;
    private int ownedIngredientCnt = 0;
    // private Menu menu;

    public void addContents(String str) {
        this.contents.add(str);
    }
    public void addPhoto(Bitmap picture) {
        this.pictures.add(picture);
    }
    public void addNeededIngredients(Ingredient ingredient) {
        this.neededIngredients.add(ingredient);
    }

    // set & get
    public ArrayList<String> getContents() {
        return contents;
    }

    public ArrayList<Ingredient> getNeededIngredients() {
        return neededIngredients;
    }

    public void setIngredientRatio(ArrayList<Ingredient> ownedIngredients) {
        ownedIngredientCnt = 0;
        for (int i = 0; i < neededIngredients.size(); i++) {
            for (int j = 0; j < ownedIngredients.size(); j++) {
                if (neededIngredients.get(i).getName().equals(ownedIngredients.get(j).getName()))
                    ownedIngredientCnt++;
            }
        }
        this.ingredientRatio = (double)ownedIngredientCnt / this.neededIngredients.size();
    }

    public double getIngredientRatio() {
        return ingredientRatio;
    }

    public int getOwnedIntredientCnt() {
        return ownedIngredientCnt;
    }

    public ArrayList<Bitmap> getPictures() {
        return pictures;
    }
}
