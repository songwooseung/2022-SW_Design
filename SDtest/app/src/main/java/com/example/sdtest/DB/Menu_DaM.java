package com.example.sdtest.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Menu_DaM extends android.database.sqlite.SQLiteOpenHelper {

    public String[] MENU_COLS = {"MENU_ID", "MENU_NAME", "MENU_VIEWS", "MENU_CONTENTS", "MENU_INGREDIENT_RATIO"};
    public String TABLE_MENU = "TABLE_MENU";
    public String TABLE_MAINMENU = "TABLE_MAINMENU";
    public String TABLE_SIDEMENU = "TABLE_SIDEMENU";
    public String TABLE_SOUPMENU = "TABLE_SOUPMENU";
    public String MENU_ID = MENU_COLS[0];
    public String MENU_NAME = MENU_COLS[1];
    public String MENU_VIEWS = MENU_COLS[2];
    public String MENU_CONTENTS = MENU_COLS[3];
    public String MENU_INGREDIENT_RATIO = MENU_COLS[4];
    public String MENU_INGREDIENT = "MENU_INGREDIENT";
    public String MENU_INGREDIENT_COUNT = "MENU_INGREDIENT_COUNT";
    //사진 저장 방법 생각해보기
    public String INGREDIENT = "TABLE_INGREDIENT";
    public String INGREDIENT_NAME = "INGREDIENT_NAME";
    public String CARBOHYDRATE = "CARBONHYDRATE";
    public String PROTEIN = "PROTEIN";
    public String FAT = "FAT";
    public String SODIUM = "SODIUM";
    public String SUGARS = "SUGARS";
    public String AMOUNT = "AMOUNT";
    public String UNIT = "UNIT";
    public String PURCHASELINK = "PURCHASELINK";

    public Menu_DaM(Context context, String dbname) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create_ingredient = "CREATE TABLE IF NOT EXISTS " + INGREDIENT + "(" +
                "INGREDIENT_NAME NOT NULL, CARBONHYDRATE DOUBLE, PROTEIN DOUBLE, FAT DOUBLE, SODIUM DOUBLE, SUGARS DOUBLE , AMOUNT DOUBLE, UNIT, PURCHASELINK," +
                "CONSTRAINT PK_GROUP PRIMARY KEY (INGREDIENT_NAME, AMOUNT));";
        sqLiteDatabase.execSQL(create_ingredient);

        String create_menu = "CREATE TABLE IF NOT EXISTS " + TABLE_MENU +
                " (" +
                MENU_NAME + " NOT NULL, " +
                MENU_VIEWS + " NOT NULL, " +
                MENU_CONTENTS + " NOT NULL, " +
                MENU_INGREDIENT_RATIO + " DOUBLE NOT NULL, " +
                MENU_INGREDIENT + " NOT NULL, " +
                MENU_INGREDIENT_COUNT + " INT NOT NULL, " +
                "FOREIGN KEY (MENU_INGREDIENT) REFERENCES TABLE_INGREDIENT(INGREDIENT_NAME)," +
                "FOREIGN KEY(MENU_INGREDIENT_COUNT) REFERENCES TABLE_INGREDIENT(AMOUNT) );";


        sqLiteDatabase.execSQL(create_menu);

        String create_mainmenu = "CREATE TABLE IF NOT EXISTS " + TABLE_MAINMENU +
                " (" +
                MENU_NAME + " NOT NULL, " +
                MENU_VIEWS + " NOT NULL, " +
                MENU_CONTENTS + " NOT NULL, " +
                MENU_INGREDIENT_RATIO + " DOUBLE NOT NULL, " +
                MENU_INGREDIENT + " NOT NULL, " +
                MENU_INGREDIENT_COUNT + " DOUBLE NOT NULL, " +
                "FOREIGN KEY (MENU_INGREDIENT) REFERENCES TABLE_INGREDIENT(INGREDIENT_NAME)," +
                "FOREIGN KEY(MENU_INGREDIENT_COUNT) REFERENCES TABLE_INGREDIENT(AMOUNT) );";

        sqLiteDatabase.execSQL(create_mainmenu);

        String create_sidemenu = "CREATE TABLE IF NOT EXISTS " + TABLE_SIDEMENU +
                " (" +
                MENU_NAME + " NOT NULL, " +
                MENU_VIEWS + " NOT NULL, " +
                MENU_CONTENTS + " NOT NULL, " +
                MENU_INGREDIENT_RATIO + " DOUBLE NOT NULL, " +
                MENU_INGREDIENT + " NOT NULL, " +
                MENU_INGREDIENT_COUNT + " DOUBLE NOT NULL, " +
                "FOREIGN KEY (MENU_INGREDIENT) REFERENCES TABLE_INGREDIENT(INGREDIENT_NAME)," +
                "FOREIGN KEY(MENU_INGREDIENT_COUNT) REFERENCES TABLE_INGREDIENT(AMOUNT) );";
        sqLiteDatabase.execSQL(create_sidemenu);

        String create_soupmenu = "CREATE TABLE IF NOT EXISTS " + TABLE_SOUPMENU +
                " (" +
                MENU_NAME + " NOT NULL, " +
                MENU_VIEWS + " NOT NULL, " +
                MENU_CONTENTS + " NOT NULL, " +
                MENU_INGREDIENT_RATIO + " DOUBLE NOT NULL, " +
                MENU_INGREDIENT + " NOT NULL, " +
                MENU_INGREDIENT_COUNT + " DOUBLE NOT NULL, " +
                "FOREIGN KEY (MENU_INGREDIENT) REFERENCES TABLE_INGREDIENT(INGREDIENT_NAME)," +
                "FOREIGN KEY(MENU_INGREDIENT_COUNT) REFERENCES TABLE_INGREDIENT(AMOUNT) );";


        sqLiteDatabase.execSQL(create_soupmenu);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_query = "drop table " + TABLE_MENU + ";";
        sqLiteDatabase.execSQL(drop_query);
        String drop_query1 = "drop table " + TABLE_SOUPMENU + ";";
        sqLiteDatabase.execSQL(drop_query1);
        String drop_query2 = "drop table " + TABLE_MAINMENU + ";";
        sqLiteDatabase.execSQL(drop_query2);
        String drop_query3 = "drop table " + TABLE_SIDEMENU + ";";
        sqLiteDatabase.execSQL(drop_query3);

        onCreate(sqLiteDatabase);
    }
}