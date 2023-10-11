package com.example.sdtest.MenuUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sdtest.DB.Menu_Control;
import com.example.sdtest.DB.Menu_DaM;
import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.Menu.MainMenuList;
import com.example.sdtest.Menu.Menu;
import com.example.sdtest.Menu.SideMenuList;
import com.example.sdtest.Menu.SoupMenuList;
import com.example.sdtest.R;
import com.example.sdtest.User.Customer.Customer;

import java.io.InputStream;
import java.text.DecimalFormat;

public class MenuAddActivity extends AppCompatActivity {
    private MainMenuList mainList = MainMenuList.getInstance();
    private SideMenuList sideList = SideMenuList.getInstance();
    private SoupMenuList soupList = SoupMenuList.getInstance();
    private Customer customer = Customer.getInstance();

    private ImageView iv_menu_add_picture;
    private EditText et_menu_add_title;
    private Button btn_menu_add_picture;
    private Button btn_menu_add_ingredientAdd;
    private Button btn_menu_add_btn_menu_add_recipeAdd;
    private LinearLayout ll_menu_add_picture;
    private LinearLayout ll_menu_add_ingredient;
    private LinearLayout ll_menu_add_recipe;
    private Button btn_menu_add_save;
    private Button btn_menu_add_back;

    private Menu menu;
    private int ingredientCnt = 0;
    private int recipeCnt = 0;

    private Menu_DaM menu_daM;
    private Menu_Control menu_control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add);
        et_menu_add_title = findViewById(R.id.et_menu_add_title);
        btn_menu_add_picture = findViewById(R.id.btn_menu_add_picture);
        btn_menu_add_ingredientAdd = findViewById(R.id.btn_menu_add_ingredientAdd);
        btn_menu_add_btn_menu_add_recipeAdd = findViewById(R.id.btn_menu_add_recipeAdd);
        ll_menu_add_picture = findViewById(R.id.ll_menu_add_picture);
        ll_menu_add_ingredient = findViewById(R.id.ll_menu_add_ingredient);
        ll_menu_add_recipe = findViewById(R.id.ll_menu_add_recipe);
        btn_menu_add_save = findViewById(R.id.btn_menu_add_save);
        btn_menu_add_back = findViewById(R.id.btn_back);

        int category = getIntent().getIntExtra("category", 0);
        // <<create> Menu
        // <<create> Recipe
        menu = new Menu();

        // "사진 첨부" 버튼 누를시
        btn_menu_add_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
        // "준비물 추가" 버튼 누를시
        btn_menu_add_ingredientAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAddActivity.this, IngredientAddActivity.class);
                startActivityForResult(intent, 2);
            }
        });
        // "레시피 추가" 버튼 누를시
        btn_menu_add_btn_menu_add_recipeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout.LayoutParams ll_p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, changeDp(55));
                LinearLayout.LayoutParams tv_p = new LinearLayout.LayoutParams(changeDp(50), changeDp(50));
                LinearLayout.LayoutParams et_p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, changeDp(50));

                LinearLayout ll = new LinearLayout(getApplicationContext());
                TextView tv = new TextView(getApplicationContext());
                EditText et = new EditText(getApplicationContext());

                ll_p.topMargin = changeDp(10);
                ll_p.bottomMargin = changeDp(5);
                ll.setLayoutParams(ll_p);

                tv.setLayoutParams(tv_p);
                tv.setGravity(Gravity.CENTER);
                tv.setText(Integer.toString(recipeCnt + 1));
                tv.setTextSize(changeDp(8));
                tv.setTextColor(getApplicationContext().getResources().getColor(R.color.black));
                tv_p.leftMargin = changeDp(20);
                tv.setBackgroundResource(R.drawable.button1);

                et.setBackgroundResource(R.drawable.button1);
                et.setPadding(changeDp(20), 0, 30, 0);
                et_p.leftMargin = changeDp(10);
                et_p.rightMargin = changeDp(20);
                et.setTextSize(changeDp(5));
                et.setMaxLines(2);
                et.setLayoutParams(et_p);
                et.setId(recipeCnt++);
                et.requestFocus();

                ll.addView(tv);
                ll.addView(et);
                if (recipeCnt == 2)
                    ll_menu_add_recipe.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ll_menu_add_recipe.addView(ll);
                check();
            }
        });

        btn_menu_add_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_daM = new Menu_DaM(getApplicationContext(),"dbmenu0.db");
                menu_control = new Menu_Control(menu_daM);
                if (menu.getRecipe().getPictures().size() > 0 && ingredientCnt > 0 && recipeCnt > 0) {
                    menu.setName(et_menu_add_title.getText().toString());
                    menu.getRecipe().setIngredientRatio(customer.getOwned());

                    // addContents(String)
                    for (int i = 0; i < recipeCnt; i++) {
                        EditText et = findViewById(i);
                        menu.getRecipe().addContents(et.getText().toString());
                    }

                    // addMenu(Menu)
                    if (category == 0) {
                        mainList.addMenu(menu);
                        menu_control.add_db_mainMenu(menu);
                    } else if (category == 1) {
                        sideList.addMenu(menu);
                        menu_control.add_db_sideMenu(menu);
                    } else {
                        soupList.addMenu(menu);
                        menu_control.add_db_soupMenu(menu);
                    }
                    menu_control.update_db();
                    menu_control.db_close();

                    finish();
                }
            }
        });

        btn_menu_add_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 사진 추가
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(changeDp(90), changeDp(90));

            p.leftMargin = changeDp(15);
            iv_menu_add_picture = new ImageView(getApplicationContext());
            iv_menu_add_picture.setLayoutParams(p);
            ll_menu_add_picture.addView(iv_menu_add_picture);
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    // addPhoto(Bitmap)
                    menu.getRecipe().addPhoto(img);

                    iv_menu_add_picture.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // 준비물 추가
        else if (requestCode == 2 && resultCode == RESULT_OK) {
            ingredientCnt++;

            Ingredient ingredient = (Ingredient) data.getSerializableExtra("ingredient");
            ingredient.setAmount(data.getDoubleExtra("amount", 0));
            Double amount = ingredient.getAmount();
            ingredient.setCarbohydrate(ingredient.getCarbohydrate() * amount);
            ingredient.setProtein(ingredient.getProtein() * amount);
            ingredient.setFat(ingredient.getFat() * amount);
            ingredient.setSodium(ingredient.getSodium() * amount);
            ingredient.setSugars(ingredient.getSugars() * amount);

            // addNeededIngredients(Ingredient)
            menu.getRecipe().addNeededIngredients(ingredient);

            LinearLayout.LayoutParams ll_p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, changeDp(55));
            LinearLayout.LayoutParams tv_p1 = new LinearLayout.LayoutParams(0, changeDp(50));
            LinearLayout.LayoutParams tv_p2 = new LinearLayout.LayoutParams(0, changeDp(50));
            tv_p1.weight = 1;
            tv_p1.leftMargin = changeDp(20);
            tv_p1.rightMargin = changeDp(5);
            tv_p2.weight = 1;
            tv_p2.leftMargin = changeDp(5);
            tv_p2.rightMargin = changeDp(20);

            LinearLayout ll = new LinearLayout(getApplicationContext());
            TextView tv1 = new TextView(getApplicationContext());
            TextView tv2 = new TextView(getApplicationContext());

            ll_p.topMargin = changeDp(10);
            ll_p.bottomMargin = changeDp(5);
            ll.setLayoutParams(ll_p);

            tv1.setLayoutParams(tv_p1);
            tv1.setGravity(Gravity.CENTER);
            tv1.setText(ingredient.getName());
            tv1.setTextSize(changeDp(8));
            tv1.setBackgroundResource(R.drawable.button1);
            tv1.setTextColor(getApplicationContext().getResources().getColor(R.color.black));
            ll.addView(tv1);

            tv2.setLayoutParams(tv_p2);
            tv2.setGravity(Gravity.CENTER);
            DecimalFormat df = new DecimalFormat("#.##");
            tv2.setText(df.format(ingredient.getAmount()) + ingredient.getUnit());
            tv2.setTextSize(changeDp(6));
            tv2.setBackgroundResource(R.drawable.button1);
            tv2.setTextColor(getApplicationContext().getResources().getColor(R.color.black));
            ll.addView(tv2);

            if (ingredientCnt == 2)
                ll_menu_add_ingredient.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ll_menu_add_ingredient.addView(ll);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        check();
    }

    public void check() {
        if (menu.getRecipe().getPictures().size() > 0 && ingredientCnt > 0 && recipeCnt > 0) {
            btn_menu_add_save.setBackgroundResource(R.drawable.button2);
            btn_menu_add_save.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
        }
    }

    public int changeDp(int value) {
        int changed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
        return changed;
    }
}