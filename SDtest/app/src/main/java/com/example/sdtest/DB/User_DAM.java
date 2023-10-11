package com.example.sdtest.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class User_DAM extends android.database.sqlite.SQLiteOpenHelper {

    public String TABLE_OWN_INGREDIENT = "TABLE_OWN_INGREDIENT";
    public String TABlE_MENU_INGREDIENT = "TABlE_MENU_INGREDIENT";
    public String INGREDIENT_NAME = "INGREDIENT_NAME";
    public String CARBOHYDRATE = "CARBONHYDRATE";
    public String PROTEIN = "PROTEIN";
    public String FAT = "FAT";
    public String SODIUM = "SODIUM";
    public String SUGARS = "SUGARS";
    public String AMOUNT = "AMOUNT";
    public String UNIT = "UNIT";
    public String PURCHASELINK = "PURCHASELINK";

    //EATEN MENU
    public String[] MENU_COLS = {"MENU_ID", "MENU_NAME", "MENU_VIEWS", "MENU_CONTENTS", "MENU_INGREDIENT_RATIO"};
    public String TABLE_MENU = "TABLE_MENU";
    public String MENU_NAME = MENU_COLS[1];
    public String MENU_VIEWS = MENU_COLS[2];
    public String MENU_CONTENTS = MENU_COLS[3];
    public String MENU_INGREDIENT_RATIO = MENU_COLS[4];
    public String MENU_INGREDIENT = "MENU_INGREDIENT";
    public String MENU_INGREDIENT_COUNT = "MENU_INGREDIENT_COUNT";
    public String MENU_EATEN_DATE = "MENU_EATEN_DATE";
    //사진 저장 방법 생각해보기

    public User_DAM(Context context, String dbname) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Customer.owningredient
        String create_own_ingredient = "CREATE TABLE IF NOT EXISTS " + TABLE_OWN_INGREDIENT + "(" +
                "INGREDIENT_NAME NOT NULL, CARBONHYDRATE DOUBLE, PROTEIN DOUBLE, FAT DOUBLE, SODIUM DOUBLE, SUGARS DOUBLE , AMOUNT DOUBLE, UNIT, PURCHASELINK, " +
                "CONSTRAINT PK_GROUP PRIMARY KEY (INGREDIENT_NAME, AMOUNT));";
        sqLiteDatabase.execSQL(create_own_ingredient);

        //Eatenmenu class
        String create_menu = "CREATE TABLE IF NOT EXISTS " + TABLE_MENU +
                " (" +
                MENU_NAME + " NOT NULL, " +
                MENU_VIEWS + " NOT NULL, " +
                MENU_CONTENTS + " NOT NULL, " +
                MENU_INGREDIENT_RATIO + " DOUBLE NOT NULL, " +
                MENU_INGREDIENT + " NOT NULL, " +
                MENU_INGREDIENT_COUNT + " DOUBLE NOT NULL, " +
                MENU_EATEN_DATE + " INT NOT NULL, " + //Eatenmenu 추가항목
                "FOREIGN KEY (MENU_INGREDIENT) REFERENCES TABLE_MENU_INGREDIENT(INGREDIENT_NAME)," +
                "FOREIGN KEY(MENU_INGREDIENT_COUNT) REFERENCES TABlE_MENU_INGREDIENT(AMOUNT) );";

        sqLiteDatabase.execSQL(create_menu);

        //Eatenmenu.recipe.neededingredient
        String create_menu_ingredient = "CREATE TABLE IF NOT EXISTS " + TABlE_MENU_INGREDIENT + "(" +
                "INGREDIENT_NAME NOT NULL, CARBONHYDRATE DOUBLE, PROTEIN DOUBLE, FAT DOUBLE, SODIUM DOUBLE, SUGARS DOUBLE , AMOUNT DOUBLE, UNIT, PURCHASELINK, " +
                "CONSTRAINT PK_GROUP PRIMARY KEY (INGREDIENT_NAME, AMOUNT));";
        sqLiteDatabase.execSQL(create_menu_ingredient);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_query = "drop table " + TABLE_OWN_INGREDIENT + ";";
        sqLiteDatabase.execSQL(drop_query);
        String drop_query1 = "drop table " + TABLE_MENU + ";";
        sqLiteDatabase.execSQL(drop_query1);
        String drop_query2 = "drop table " + TABlE_MENU_INGREDIENT + ";";
        sqLiteDatabase.execSQL(drop_query2);

        onCreate(sqLiteDatabase);
    }

}