package com.example.sdtest.Menu;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String name;
    private double carbohydrate;
    private double protein;
    private double fat;
    private double sodium;
    private double sugars;
    private double amount;
    private String unit;
    private String purchaseLink = "https://www.coupang.com/np/search?q=";
    // private ArrayList<Recipe> recipes;
    // private ArrayList<Customer> customers;

    // constructor & set & get
    public Ingredient(String name, double carb, double protein, double fat, double sodium, double sugars, double amount, String unit) {
        this.name = name;
        this.carbohydrate = carb;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.sugars = sugars;
        this.amount = amount;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public String getPurchaseLink() {
        return purchaseLink;
    }
}
