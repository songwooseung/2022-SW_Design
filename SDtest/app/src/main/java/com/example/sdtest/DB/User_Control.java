package com.example.sdtest.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.Menu.Menu;
import com.example.sdtest.Nutrient.EatenMenu;

import java.util.ArrayList;

public class User_Control {

    User_DAM user_dam;
    SQLiteDatabase sqlite;
    private ArrayList<Ingredient> update_own;
    //Eaten으로 바꾸기
    private ArrayList<EatenMenu> update_eatenmenu;

    public User_Control(User_DAM dam) {
        this.user_dam = dam;
        this.update_eatenmenu = new ArrayList<>();
        this.update_own = new ArrayList<>();
    }

    public void add_db_eaten(EatenMenu menu1)  //Eaten으로 바꾸기
    {
        update_eatenmenu.add(update_eatenmenu.size(), menu1);
    }

    public void add_db_own_ingredient(Ingredient ingredient1) {
        update_own.add(update_own.size(), ingredient1);
    }

    public void update_db() {
        insert_EatenMenus();
        insert_own_ingredients();
    }

    public void insert_own_ingredient(Ingredient i1) {
        sqlite = user_dam.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(user_dam.INGREDIENT_NAME, i1.getName());
        values.put(user_dam.CARBOHYDRATE, i1.getCarbohydrate());
        values.put(user_dam.PROTEIN, i1.getProtein());
        values.put(user_dam.SODIUM, i1.getSodium());
        values.put(user_dam.SUGARS, i1.getSugars());
        values.put(user_dam.FAT, i1.getFat());
        values.put(user_dam.PURCHASELINK, i1.getPurchaseLink());
        values.put(user_dam.AMOUNT, i1.getAmount());


        sqlite.insert(user_dam.TABLE_OWN_INGREDIENT, null, values);

    }


    private void insert_own_ingredients() {
        ArrayList<Ingredient> ingredientArrayList = this.update_own;
        sqlite = user_dam.getWritableDatabase();
        for (Ingredient i1 : ingredientArrayList) {
            ContentValues values = new ContentValues();
            values.put(user_dam.INGREDIENT_NAME, i1.getName());
            values.put(user_dam.CARBOHYDRATE, i1.getCarbohydrate());
            values.put(user_dam.PROTEIN, i1.getProtein());
            values.put(user_dam.SODIUM, i1.getSodium());
            values.put(user_dam.SUGARS, i1.getSugars());
            values.put(user_dam.FAT, i1.getFat());
            values.put(user_dam.PURCHASELINK, i1.getPurchaseLink());
            values.put(user_dam.AMOUNT, i1.getAmount());

            sqlite.insert(user_dam.TABLE_OWN_INGREDIENT, null, values);

        }
    }


    public void insert_EatenMenu(Menu menu, Ingredient[] ingredientslist) //Menu->EatenMenu바꾸기
    {
        sqlite = user_dam.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(user_dam.MENU_NAME, menu.getName());
        contentValues.put(user_dam.MENU_VIEWS, menu.getViews());
        String contents_cat = new String("");
        for (int i = 0; i < menu.getRecipe().getContents().size(); i++) {
            if (i == 0) {
                contents_cat = contents_cat + menu.getRecipe().getContents().get(i);
            } else {
                contents_cat = contents_cat + "<endl>" + menu.getRecipe().getContents().get(i);
            }

        }
        contentValues.put(user_dam.MENU_CONTENTS, contents_cat);
        contentValues.put(user_dam.MENU_INGREDIENT_RATIO, menu.getRecipe().getIngredientRatio());
        //EatenMenu 추가
        contentValues.put(user_dam.MENU_EATEN_DATE, menu.getViews()); // ->getEatenDate()로 바꾸기
        for (int i = 0; i < ingredientslist.length; i++) {
            ContentValues ingredient_Values = new ContentValues();
            ingredient_Values.put(user_dam.INGREDIENT_NAME, ingredientslist[i].getName());
            ingredient_Values.put(user_dam.CARBOHYDRATE, ingredientslist[i].getCarbohydrate());
            ingredient_Values.put(user_dam.PROTEIN, ingredientslist[i].getProtein());
            ingredient_Values.put(user_dam.FAT, ingredientslist[i].getFat());
            ingredient_Values.put(user_dam.SODIUM, ingredientslist[i].getSodium());
            ingredient_Values.put(user_dam.SUGARS, ingredientslist[i].getSugars());
            ingredient_Values.put(user_dam.AMOUNT, ingredientslist[i].getAmount());
            ingredient_Values.put(user_dam.UNIT, ingredientslist[i].getUnit());
            ingredient_Values.put(user_dam.PURCHASELINK, ingredientslist[i].getPurchaseLink() + ingredientslist[i].getName());
            sqlite.insert(user_dam.TABlE_MENU_INGREDIENT, null, ingredient_Values);


            contentValues.put(user_dam.MENU_INGREDIENT, ingredientslist[i].getName());
            contentValues.put(user_dam.MENU_INGREDIENT_COUNT, ingredientslist[i].getAmount());
            //foreign key를 받는 menu_ingredient -> ingredient table의 primarykey = ingredient_name
            sqlite.insert(user_dam.TABLE_MENU, null, contentValues);

        }


    }

    private void insert_EatenMenus() {
        ArrayList<EatenMenu> menuArrayList = this.update_eatenmenu;
        sqlite = user_dam.getWritableDatabase();
        for (Menu menu : menuArrayList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(user_dam.MENU_NAME, menu.getName());
            contentValues.put(user_dam.MENU_VIEWS, menu.getViews());
            String contents_cat = new String("");
            for (int i = 0; i < menu.getRecipe().getContents().size(); i++) {
                if (i == 0) {
                    contents_cat = contents_cat + menu.getRecipe().getContents().get(i);
                } else {
                    contents_cat = contents_cat + "<endl>" + menu.getRecipe().getContents().get(i);
                }

            }
            contentValues.put(user_dam.MENU_CONTENTS, contents_cat);
            contentValues.put(user_dam.MENU_INGREDIENT_RATIO, menu.getRecipe().getIngredientRatio());
            //EatenMenu 추가
            contentValues.put(user_dam.MENU_EATEN_DATE, menu.getViews()); // ->getEatenDate()로 바꾸기
            Ingredient[] ingredientslist = menu.getRecipe().getNeededIngredients().toArray(new Ingredient[menu.getRecipe().getNeededIngredients().size()]);
            for (int i = 0; i < ingredientslist.length; i++) {
                ContentValues ingredient_Values = new ContentValues();
                ingredient_Values.put(user_dam.INGREDIENT_NAME, ingredientslist[i].getName());
                ingredient_Values.put(user_dam.CARBOHYDRATE, ingredientslist[i].getCarbohydrate());
                ingredient_Values.put(user_dam.PROTEIN, ingredientslist[i].getProtein());
                ingredient_Values.put(user_dam.FAT, ingredientslist[i].getFat());
                ingredient_Values.put(user_dam.SODIUM, ingredientslist[i].getSodium());
                ingredient_Values.put(user_dam.SUGARS, ingredientslist[i].getSugars());
                ingredient_Values.put(user_dam.AMOUNT, ingredientslist[i].getAmount());
                ingredient_Values.put(user_dam.UNIT, ingredientslist[i].getUnit());
                ingredient_Values.put(user_dam.PURCHASELINK, ingredientslist[i].getPurchaseLink() + ingredientslist[i].getName());
                sqlite.insert(user_dam.TABlE_MENU_INGREDIENT, null, ingredient_Values);


                contentValues.put(user_dam.MENU_INGREDIENT, ingredientslist[i].getName());
                contentValues.put(user_dam.MENU_INGREDIENT_COUNT, ingredientslist[i].getAmount());
                //foreign key를 받는 menu_ingredient -> ingredient table의 primarykey = ingredient_name
                sqlite.insert(user_dam.TABLE_MENU, null, contentValues);

            }
        }
    }

    public ArrayList<Ingredient> getdb_own_ingredient() {
        ArrayList<Ingredient> ownlist = new ArrayList<>();
        sqlite = user_dam.getReadableDatabase();


        String query_menu = "SELECT * FROM " + user_dam.TABLE_OWN_INGREDIENT;
        Cursor c_own = sqlite.rawQuery(query_menu, null);

        while (c_own.moveToNext()) {
            Ingredient ing1 = new Ingredient(
                    c_own.getString(c_own.getColumnIndexOrThrow(user_dam.INGREDIENT_NAME)),
                    c_own.getDouble(c_own.getColumnIndexOrThrow(user_dam.CARBOHYDRATE)),
                    c_own.getDouble(c_own.getColumnIndexOrThrow(user_dam.PROTEIN)),
                    c_own.getDouble(c_own.getColumnIndexOrThrow(user_dam.FAT)),
                    c_own.getDouble(c_own.getColumnIndexOrThrow(user_dam.SODIUM)),
                    c_own.getDouble(c_own.getColumnIndexOrThrow(user_dam.SUGARS)),
                    c_own.getDouble(c_own.getColumnIndexOrThrow(user_dam.AMOUNT)),
                    c_own.getString(c_own.getColumnIndexOrThrow(user_dam.UNIT))
            );

            ownlist.add(ownlist.size(), ing1);

        }
        return ownlist;
    }

    //EatenMenu로 바꾸기
    public ArrayList<EatenMenu> getdb_EatenMenu() {
        sqlite = user_dam.getReadableDatabase();

        String query_menu = "SELECT * FROM TABLE_MENU JOIN TABLE_MENU_INGREDIENT" +
                " ON TABLE_MENU.MENU_INGREDIENT = TABLE_MENU_INGREDIENT.INGREDIENT_NAME" +
                " AND TABLE_MENU.MENU_INGREDIENT_COUNT = TABLE_MENU_INGREDIENT.AMOUNT";
        //INGREDIENT
        Cursor c_menu = sqlite.rawQuery(query_menu, null);
        //SELECT * FROM TABLE_MENU
        //JOIN TABLE_INGREDIENT
        //ON TABLE_MENU.MENU_INGREDIENT = TABLE_INGREDIENT.INGREDIENT_NAME
        // AND TABLE_MENU.MENU_INGREDIENT_COUNT = TABLE_INGREDIENT.COUNT;

        String[] columnName = {user_dam.MENU_NAME, user_dam.MENU_VIEWS, user_dam.MENU_CONTENTS, user_dam.MENU_INGREDIENT_RATIO, user_dam.INGREDIENT_NAME};

        ArrayList<EatenMenu> menulist = new ArrayList<>();
        while (c_menu.moveToNext()) {
            if (menulist.size() == 0 ||

                    menulist.get(menulist.size() - 1).getName().equals(c_menu.getString(c_menu.getColumnIndexOrThrow(columnName[0]))) == false)
            //가장 최근에 추가한 것(menulist)과 커서(c_menu)가 같으면 같은 메뉴안에 있는 재료들임 -> 이런 경우는 재료들만 추가함
            {
                //메뉴 객체 생성
                EatenMenu menu1 = new EatenMenu();
                menu1.setName(c_menu.getString(c_menu.getColumnIndexOrThrow(columnName[0])));
                /*code for 조회수 db->object with c_menu.getInt(c_menu.getColumnIndexOrThrow(menu_dam.MENU_VIEWS));*/
                String[] splitcontents = c_menu.getString(c_menu.getColumnIndexOrThrow(columnName[2])).split("<endl>");
                for (String splited : splitcontents) {
                    menu1.getRecipe().addContents(splited);
                }
                /*code for ratio db -> object with  c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.MENU_INGREDIENT_RATIO));*/

                Ingredient ing1 = new Ingredient(
                        c_menu.getString(c_menu.getColumnIndexOrThrow(user_dam.INGREDIENT_NAME)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.CARBOHYDRATE)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.PROTEIN)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.FAT)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.SODIUM)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.SUGARS)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.AMOUNT)),
                        c_menu.getString(c_menu.getColumnIndexOrThrow(user_dam.UNIT))
                );
                //메뉴 객체에 필요한 재료 추가
                menu1.getRecipe().addNeededIngredients(ing1);


                //메뉴리스트에 메뉴 추가
                menulist.add(menulist.size(), menu1);

            } else {
                Ingredient ing1 = new Ingredient(
                        c_menu.getString(c_menu.getColumnIndexOrThrow(user_dam.INGREDIENT_NAME)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.CARBOHYDRATE)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.PROTEIN)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.FAT)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.SODIUM)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.SUGARS)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(user_dam.AMOUNT)),
                        c_menu.getString(c_menu.getColumnIndexOrThrow(user_dam.UNIT))
                );
                //마지막 메뉴에 필요한 재료만 추가
                menulist.get(menulist.size() - 1).getRecipe().addNeededIngredients(ing1);
                /*Menu lastmenu = menulist.get(menulist.size());
                lastmenu.getRecipe().addNeededIngredient(ing1);
                menulist.set(menulist.size()-1,lastmenu);*/

            }
        }
        return menulist;

    }

    public void db_close() {
        if (sqlite != null) {
            sqlite.close();
        }
        if (user_dam != null) {
            user_dam.close();
        }
    }


}