package com.example.sdtest.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.Menu.Menu;

import java.util.ArrayList;

public class Menu_Control {

    Menu_DaM menu_dam;
    SQLiteDatabase sqLiteDatabase;
    private ArrayList<Menu> updated_mainMenulist;
    private ArrayList<Menu> updated_sideMenulist;
    private ArrayList<Menu> updated_soupMenulist;

    public Menu_Control(Menu_DaM menu_dam) {
        this.menu_dam = menu_dam;
        this.updated_mainMenulist = new ArrayList<>();
        this.updated_sideMenulist = new ArrayList<>();
        this.updated_soupMenulist = new ArrayList<>();
    }

    public void add_db_mainMenu(Menu menu) {
        updated_mainMenulist.add(updated_mainMenulist.size(), menu);
    }

    public void add_db_soupMenu(Menu menu) {
        updated_soupMenulist.add(updated_soupMenulist.size(), menu);
    }

    public void add_db_sideMenu(Menu menu) {
        updated_sideMenulist.add(updated_sideMenulist.size(), menu);
    }

    public void update_db() {
        insert_MAINs();
        insert_SOUPs();
        insert_SIDEs();
    }

    private void insert_MAINs() {
        for (Menu mainmenu : updated_mainMenulist) {
            sqLiteDatabase = menu_dam.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            ContentValues ingredient_Values = new ContentValues();
            Ingredient[] ingredientslist = mainmenu.getRecipe().getNeededIngredients().toArray(new Ingredient[mainmenu.getRecipe().getNeededIngredients().size()]);
            inserting(mainmenu, ingredientslist, sqLiteDatabase, menu_dam, contentValues, ingredient_Values, menu_dam.TABLE_MAINMENU);
        }
    }

    private void insert_SIDEs() {
        for (Menu sidemenu : updated_sideMenulist) {
            sqLiteDatabase = menu_dam.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            ContentValues ingredient_Values = new ContentValues();
            Ingredient[] ingredientslist = sidemenu.getRecipe().getNeededIngredients().toArray(new Ingredient[sidemenu.getRecipe().getNeededIngredients().size()]);
            inserting(sidemenu, ingredientslist, sqLiteDatabase, menu_dam, contentValues, ingredient_Values, menu_dam.TABLE_SIDEMENU);
        }
    }

    private void insert_SOUPs() {
        for (Menu soupmenu : updated_soupMenulist) {
            sqLiteDatabase = menu_dam.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            ContentValues ingredient_Values = new ContentValues();
            Ingredient[] ingredientslist = soupmenu.getRecipe().getNeededIngredients().toArray(new Ingredient[soupmenu.getRecipe().getNeededIngredients().size()]);
            inserting(soupmenu, ingredientslist, sqLiteDatabase, menu_dam, contentValues, ingredient_Values, menu_dam.TABLE_SOUPMENU);
        }
    }

    private void inserting(Menu menu, Ingredient[] ingredientslist, SQLiteDatabase sqLiteDatabase, Menu_DaM menu_dam, ContentValues contentValues, ContentValues ingredient_values, String Menu_Type) {

        //contentValues.put(menu_dam.MENU_ID,1);

        contentValues.put(menu_dam.MENU_NAME, menu.getName());
        contentValues.put(menu_dam.MENU_VIEWS, menu.getViews());
        String contentscat = new String("");
        for (int i = 0; i < menu.getRecipe().getContents().size(); i++) {
            if (i == 0) {
                contentscat = contentscat + menu.getRecipe().getContents().get(i);
            } else {
                contentscat = contentscat + "<endl>" + menu.getRecipe().getContents().get(i);
            }

        }

        contentValues.put(menu_dam.MENU_CONTENTS, contentscat);
        contentValues.put(menu_dam.MENU_INGREDIENT_RATIO, menu.getRecipe().getIngredientRatio());
        for (int i = 0; i < ingredientslist.length; i++) {
            ContentValues ingredient_Values = new ContentValues();
            ingredient_Values.put(menu_dam.INGREDIENT_NAME, ingredientslist[i].getName());
            ingredient_Values.put(menu_dam.CARBOHYDRATE, ingredientslist[i].getCarbohydrate());
            ingredient_Values.put(menu_dam.PROTEIN, ingredientslist[i].getProtein());
            ingredient_Values.put(menu_dam.FAT, ingredientslist[i].getFat());
            ingredient_Values.put(menu_dam.SODIUM, ingredientslist[i].getSodium());
            ingredient_Values.put(menu_dam.SUGARS, ingredientslist[i].getSugars());
            ingredient_Values.put(menu_dam.AMOUNT, ingredientslist[i].getAmount());
            ingredient_Values.put(menu_dam.UNIT, ingredientslist[i].getUnit());
            ingredient_Values.put(menu_dam.PURCHASELINK, ingredientslist[i].getPurchaseLink() + ingredientslist[i].getName());
            sqLiteDatabase.insert(menu_dam.INGREDIENT, null, ingredient_Values);


            contentValues.put(menu_dam.MENU_INGREDIENT, ingredientslist[i].getName());
            contentValues.put(menu_dam.MENU_INGREDIENT_COUNT, ingredientslist[i].getAmount());
            //foreign key를 받는 menu_ingredient -> ingredient table의 primarykey = ingredient_name
            sqLiteDatabase.insert(Menu_Type, null, contentValues);
        }
    }

    private ArrayList<Menu> selecting(SQLiteDatabase sqLiteDatabase, Menu_DaM menu_daM, String Menu_type) {

        String query_menu = "SELECT * FROM " + Menu_type + " JOIN TABLE_INGREDIENT" +
                " ON " + Menu_type + ".MENU_INGREDIENT = TABLE_INGREDIENT.INGREDIENT_NAME" +
                " AND " + Menu_type + ".MENU_INGREDIENT_COUNT = TABLE_INGREDIENT.AMOUNT";
        //INGREDIENT
        Cursor c_menu = sqLiteDatabase.rawQuery(query_menu, null);
        //SELECT * FROM TABLE_MENU
        //JOIN TABLE_INGREDIENT
        //ON TABLE_MENU.MENU_INGREDIENT = TABLE_INGREDIENT.INGREDIENT_NAME
        // AND TABLE_MENU.MENU_INGREDIENT_COUNT = TABLE_INGREDIENT.COUNT;

        String[] columnName = {menu_dam.MENU_NAME, menu_dam.MENU_VIEWS, menu_dam.MENU_CONTENTS, menu_dam.MENU_INGREDIENT_RATIO, menu_dam.INGREDIENT};

        ArrayList<Menu> menulist = new ArrayList<>();
        while (c_menu.moveToNext()) {
            if (menulist.size() == 0 ||

                    menulist.get(menulist.size() - 1).getName().equals(c_menu.getString(c_menu.getColumnIndexOrThrow(columnName[0]))) == false)
            //가장 최근에 추가한 것(menulist)과 커서(c_menu)가 같으면 같은 메뉴안에 있는 재료들임 -> 이런 경우는 재료들만 추가함
            {
                //메뉴 객체 생성
                Menu menu1 = new Menu();
                menu1.setName(c_menu.getString(c_menu.getColumnIndexOrThrow(columnName[0])));
                /*code for 조회수 db->object with c_menu.getInt(c_menu.getColumnIndexOrThrow(menu_dam.MENU_VIEWS));*/
                String[] splitcontents = c_menu.getString(c_menu.getColumnIndexOrThrow(columnName[2])).split("<endl>");
                for (String splited : splitcontents) {
                    menu1.getRecipe().addContents(splited);
                }
                /*code for ratio db -> object with  c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.MENU_INGREDIENT_RATIO));*/

                Ingredient ing1 = new Ingredient(
                        c_menu.getString(c_menu.getColumnIndexOrThrow(menu_dam.INGREDIENT_NAME)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.CARBOHYDRATE)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.PROTEIN)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.FAT)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.SODIUM)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.SUGARS)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.AMOUNT)),
                        c_menu.getString(c_menu.getColumnIndexOrThrow(menu_dam.UNIT))
                );
                //메뉴 객체에 필요한 재료 추가
                menu1.getRecipe().addNeededIngredients(ing1);
                //메뉴리스트에 메뉴 추가
                menulist.add(menulist.size(), menu1);

            } else {
                Ingredient ing1 = new Ingredient(
                        c_menu.getString(c_menu.getColumnIndexOrThrow(menu_dam.INGREDIENT_NAME)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.CARBOHYDRATE)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.PROTEIN)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.FAT)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.SODIUM)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.SUGARS)),
                        c_menu.getDouble(c_menu.getColumnIndexOrThrow(menu_dam.AMOUNT)),
                        c_menu.getString(c_menu.getColumnIndexOrThrow(menu_dam.UNIT))
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

    public ArrayList<Menu> getdb_MAIN() {
        sqLiteDatabase = menu_dam.getReadableDatabase();
        String menu_type = menu_dam.TABLE_MAINMENU;
        return selecting(sqLiteDatabase, menu_dam, menu_type);

    }

    public ArrayList<Menu> getdb_SIDE() {
        sqLiteDatabase = menu_dam.getReadableDatabase();
        String menu_type = menu_dam.TABLE_SIDEMENU;
        return selecting(sqLiteDatabase, menu_dam, menu_type);

    }

    public ArrayList<Menu> getdb_SOUP() {
        sqLiteDatabase = menu_dam.getReadableDatabase();
        String menu_type = menu_dam.TABLE_SOUPMENU;
        return selecting(sqLiteDatabase, menu_dam, menu_type);

    }

    public void db_close() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
        if (menu_dam != null) {
            menu_dam.close();
        }
    }
}