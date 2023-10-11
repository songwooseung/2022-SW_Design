package com.example.sdtest.HomeUI;

import com.example.sdtest.Menu.Ingredient;

import java.util.ArrayList;

public class IngredientList {
    private static IngredientList instance = null;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    private IngredientList() {
    }

    public static IngredientList getInstance() {
        if (instance == null) {
            instance = new IngredientList();
            instance.ingredients.add(new Ingredient("가지", 4.4, 1.1, 0, 0, 2.3, 0, "개"));
            instance.ingredients.add(new Ingredient("간장", 0.5, 0.06, 0, 0.05, 0.02, 0, "ml"));
            instance.ingredients.add(new Ingredient("갈치", 0, 18.73, 7.01, 0.09, 0, 0, "마리"));
            instance.ingredients.add(new Ingredient("감자", 14.77, 1.56, 0.09, 0.03, 1.03, 0, "개"));
            instance.ingredients.add(new Ingredient("계란", 0.38, 6.29, 4.97, 0.07, 0.38, 0, "개"));
            instance.ingredients.add(new Ingredient("고등어", 0, 19.32, 9.36, 0.08, 0, 0, "마리"));
            instance.ingredients.add(new Ingredient("고추가루", 0.55, 0.12, 0.17, 0.01, 0.07, 0, "g"));
            instance.ingredients.add(new Ingredient("고추장", 0.5, 0.04, 0.02, 0.02, 0.24, 0, "g"));
            instance.ingredients.add(new Ingredient("국간장", 0.07, 0.08, 0, 0.07, 0.02, 0, "ml"));
            instance.ingredients.add(new Ingredient("굴비", 0, 12.95, 1.81, 0.04, 0, 0, "마리"));
            instance.ingredients.add(new Ingredient("굴소스", 0.39, 0, 0, 0.05, 0.38, 0, "g"));
            instance.ingredients.add(new Ingredient("김", 0.13, 0.15, 0, 0.01, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("김치", 0.04, 0.02, 0, 0.07, 0.02, 0, "g"));
            instance.ingredients.add(new Ingredient("깻잎", 0.07, 0.05, 0, 0, 0, 0, "장"));
            instance.ingredients.add(new Ingredient("낙지", 1.44, 9.75, 0.68, 0.15, 0, 0, "마리"));
            instance.ingredients.add(new Ingredient("다진마늘", 0.33, 0.06, 0, 0, 0.01, 0, "g"));
            instance.ingredients.add(new Ingredient("닭", 13.41, 71.92, 21.64, 1.55, 1.94, 0, "마리"));
            instance.ingredients.add(new Ingredient("당근", 9.58, 0.93, 0.24, 0.07, 4.54, 0, "개"));
            instance.ingredients.add(new Ingredient("대파", 0.07, 0.02, 0, 0, 0, 0, "대"));
            instance.ingredients.add(new Ingredient("돼지고기", 0.01, 0.17, 0.28, 0.01, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("된장", 0.26, 0.11, 0.06, 0.04, 0.06, 0, "g"));
            instance.ingredients.add(new Ingredient("두부", 0.03, 0.08, 0.04, 0, 0.01, 0, "g"));
            instance.ingredients.add(new Ingredient("마늘", 0.99, 0.19, 0.02, 0, 0.03, 0, "쪽"));
            instance.ingredients.add(new Ingredient("마요네즈", 0.24, 0.01, 0.33, 0.01, 0.06, 0, "g"));
            instance.ingredients.add(new Ingredient("머스타드", 0.27, 0.01, 0.19, 0.01, 0.22, 0, "g"));
            instance.ingredients.add(new Ingredient("멸치", 0.02, 0.56, 0.05, 0.02, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("멸치액젓", 0.06, 0.07, 0, 0.08, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("문어", 2.2, 14.91, 1.04, 0.23, 0, 0, "마리"));
            instance.ingredients.add(new Ingredient("미역", 0.09, 0.03, 0, 0, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("밀가루", 0.76, 0.1, 0, 0, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("바나나", 26.95, 1.29, 0.39, 0, 14.43, 0, "개"));
            instance.ingredients.add(new Ingredient("바질", 0.04, 0.03, 0, 0, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("발사믹식초", 0.13, 0, 0, 0, 0.13, 0, "ml"));
            instance.ingredients.add(new Ingredient("방울토마토", 0.67, 0.15, 0.03, 0, 0.45, 0, "개"));
            instance.ingredients.add(new Ingredient("배추", 18.31, 12.6, 1.68, 0.54, 9.91, 0, "포기"));
            instance.ingredients.add(new Ingredient("버섯", 0.03, 0.03, 0, 0, 0.02, 0, "g"));
            instance.ingredients.add(new Ingredient("버터", 0, 0, 0.8, 0, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("복숭아", 9.35, 0.89, 0.24, 0, 8.22, 0, "개"));
            instance.ingredients.add(new Ingredient("브로콜리", 6.64, 2.82, 0.37, 0.03, 1.7, 0, "개"));
            instance.ingredients.add(new Ingredient("사과", 19.06, 0.36, 0.23, 0, 14.34, 0, "개"));
            instance.ingredients.add(new Ingredient("상추", 0.03, 0, 0, 0, 0.02, 0, "g"));
            instance.ingredients.add(new Ingredient("새우", 0.05, 1.22, 0.1, 0, 0, 0, "마리"));
            instance.ingredients.add(new Ingredient("설탕", 1, 0, 0, 0, 1, 0, "g"));
            instance.ingredients.add(new Ingredient("소고기", 0, 0.2, 0.14, 0, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("소금", 0, 0, 0, 0.38, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("수박", 308.8, 24.95, 6.14, 0.04, 253.58, 0, "통"));
            instance.ingredients.add(new Ingredient("시금치", 0.04, 0.03, 0, 0, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("시나몬가루", 0.78, 0.04, 0.06, 0, 0.02, 0, "g"));
            instance.ingredients.add(new Ingredient("식용유", 0, 0, 1, 0, 0, 0, "ml"));
            instance.ingredients.add(new Ingredient("식초", 0, 0, 0, 0, 0, 0, "ml"));
            instance.ingredients.add(new Ingredient("쌀", 0.31, 0.03, 0, 0, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("쌀가루", 0.81, 0.06, 0.01, 0, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("애호박", 6.57, 2.37, 0.35, 0.02, 3.39, 0, "개"));
            instance.ingredients.add(new Ingredient("양파", 11.12, 1.01, 0.09, 0, 4.71, 0, "개"));
            instance.ingredients.add(new Ingredient("오리고기", 0, 0.18, 0.05, 0, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("오이", 4.34, 1.19, 0.32, 0, 2.77, 0, "개"));
            instance.ingredients.add(new Ingredient("오징어", 1.76, 8.88, 0.79, 0.03, 0, 0, "마리"));
            instance.ingredients.add(new Ingredient("올리브오일", 0, 0, 0.93, 0, 0, 0, "ml"));
            instance.ingredients.add(new Ingredient("전복", 3.16, 9, 0.4, 0.16, 0, 0, "마리"));
            instance.ingredients.add(new Ingredient("진간장", 0.06, 0.08, 0, 0, 0.02, 0, "ml"));
            instance.ingredients.add(new Ingredient("참기름", 0, 0, 0.1, 0, 0, 0, "ml"));
            instance.ingredients.add(new Ingredient("참외", 11, 1.57, 0.29, 0, 9.08, 0, "개"));
            instance.ingredients.add(new Ingredient("참치캔", 0, 19, 15, 0.41, 0, 0, "개"));
            instance.ingredients.add(new Ingredient("카레가루", 0.58, 0.12, 0.13, 0, 0.27, 0, "g"));
            instance.ingredients.add(new Ingredient("케찹", 0.29, 0.01, 0, 0, 0.21, 0, "g"));
            instance.ingredients.add(new Ingredient("콩나물", 0.47, 0.36, 0, 0, 0.02, 0, "g"));
            instance.ingredients.add(new Ingredient("키위", 9.1, 0.7, 0.6, 0, 9, 0, "개"));
            instance.ingredients.add(new Ingredient("파마산치즈", 0.05, 0.27, 0.36, 0, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("파스타면", 0.73, 0.14, 0.01, 0, 0.03, 0, "g"));
            instance.ingredients.add(new Ingredient("파슬리", 0.06, 0.03, 0, 0, 0, 0, "g"));
            instance.ingredients.add(new Ingredient("파프리카", 3.43, 0.64, 0.13, 0, 1.78, 0, "개"));
            instance.ingredients.add(new Ingredient("호박", 0.07, 0.01, 0, 0, 0.01, 0, "g"));
            instance.ingredients.add(new Ingredient("후추", 0.65, 0.11, 0.03, 0, 0, 0, "g"));
        }
        return instance;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredients;
    }
}
