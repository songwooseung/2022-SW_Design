package com.example.sdtest.Nutrient;

import com.example.sdtest.Menu.Ingredient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DietManagement {
    private double totalCarbohydrate;
    private double totalProtein;
    private double totalFat;
    private double totalSodium;
    private double totalSugars;
    private String contents;
    // private Customer customer;

    public void caculateNutritionFacts(ArrayList<EatenMenu> eatenMenus) {
        totalCarbohydrate = 0;
        totalFat = 0;
        totalProtein = 0;
        totalSodium = 0;
        totalSugars = 0;
        contents = "";

        Date now = new Date();
        ArrayList<String> more = new ArrayList<>();
        ArrayList<String> below = new ArrayList<>();
        ArrayList<String> menus = new ArrayList<>();

        for (EatenMenu eaten : eatenMenus) {
            if ((now.getTime() - eaten.getDate()) / (24 * 60 * 60 * 1000) < 1) {
                menus.add(eaten.getName());
                for (Ingredient needed : eaten.getRecipe().getNeededIngredients()) {
                    totalCarbohydrate += needed.getCarbohydrate();
                    totalFat += needed.getFat();
                    totalProtein += needed.getProtein();
                    totalSodium += needed.getSodium();
                    totalSugars += needed.getSugars();
                }
            }
        }

        double totalCalorie = totalCarbohydrate * 4 + totalProtein * 4 + totalFat * 9;

        if (totalCalorie >= 2300)
            more.add("칼로리");
        else if (totalCalorie < 2300)
            below.add("칼로리");
        if (totalCarbohydrate >= 130)
            more.add("탄수화물");
        else if (totalCarbohydrate < 130)
            below.add("탄수화물");
        if (totalProtein >= 65)
            more.add("단백질");
        else if (totalCarbohydrate < 65)
            below.add("단백질");
        if (totalFat >= 65)
            more.add("지방");
        else if (totalFat < 65)
            below.add("지방");
        if (totalSodium >= 5)
            more.add("나트륨");
        else if (totalSodium < 5)
            below.add("나트륨");
        if (totalSugars >= 24)
            more.add("당류");
        else if (totalSugars < 24)
            below.add("당류");

        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        sb.append(dateFormat.format(now) + "\n\n");
        if (more.size() > 0) {
            for (String e : more)
                sb.append(e + " ");
            sb.append("은/는 기준치 이상입니다\n");
        }
        if (below.size() > 0) {
            for (String e : below)
                sb.append(e + " ");
            sb.append("은/는 기준치 이하입니다\n\n오늘 하루 동안\n\n");
        }
        for (String e : menus)
            sb.append(e + "\n");
        sb.append("\n요리하였습니다");
        contents = sb.toString();
    }

    public String getContents() {
        return contents;
    }

    public double getTotalCarbohydrate() {
        return totalCarbohydrate;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public double getTotalSodium() {
        return totalSodium;
    }

    public double getTotalSugars() {
        return totalSugars;
    }
}
