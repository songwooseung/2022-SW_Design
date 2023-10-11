package com.example.sdtest.MenuUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sdtest.DB.User_Control;
import com.example.sdtest.DB.User_DAM;
import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.Menu.MainMenuList;
import com.example.sdtest.Menu.SideMenuList;
import com.example.sdtest.Menu.SoupMenuList;
import com.example.sdtest.Menu.Menu;
import com.example.sdtest.Menu.Recipe;
import com.example.sdtest.Nutrient.EatenMenu;
import com.example.sdtest.R;
import com.example.sdtest.User.Customer.Customer;

import java.util.ArrayList;

public class RecipeViewActivity extends AppCompatActivity {
    private TextView tv_menu_view_title;
    private ImageView iv_menu_view_img;
    private LinearLayout ll_recipe_view_recipe;
    private LinearLayout ll_recipe_view_picture;
    private GridView gridView;
    private NeededIngredientItemAdapter adapter;
    private Button btn_recipe_view_save;
    private Button btn_recipe_view_back;
    private User_DAM user_dam;
    private User_Control user_control;

    private ArrayList<Menu> mainList = MainMenuList.getInstance().getMenuList();
    private ArrayList<Menu> sideList = SideMenuList.getInstance().getMenuList();
    private ArrayList<Menu> soupList = SoupMenuList.getInstance().getMenuList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        tv_menu_view_title = findViewById(R.id.tv_menu_view_title);
        iv_menu_view_img = findViewById(R.id.iv_menu_view_img);
        gridView = findViewById(R.id.tv_needed_ingredient_gridview);
        ll_recipe_view_recipe = findViewById(R.id.ll_recipe_view_recipe);
        ll_recipe_view_picture = findViewById(R.id.ll_recipe_view_picture);
        btn_recipe_view_save = findViewById(R.id.btn_recipe_view_save);
        btn_recipe_view_back = findViewById(R.id.btn_back);

        Intent intent = getIntent();
        int category = intent.getIntExtra("category", 0);
        int position = intent.getIntExtra("position", 0);
        Menu menu;
        if (category == 0)
            menu = mainList.get(position);
        else if (category == 1)
            menu = sideList.get(position);
        else
            menu = soupList.get(position);
        menu.addViews();
        Recipe recipe = menu.getRecipe();

        // 제목
        tv_menu_view_title.setText(menu.getName());

        // 대표 사진
        if (recipe.getPictures().size() > 0)
            iv_menu_view_img.setImageBitmap(recipe.getPictures().get(recipe.getPictures().size() - 1));

        // 준비물
        adapter = new NeededIngredientItemAdapter(recipe.getNeededIngredients());
        LinearLayout.LayoutParams grid_p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, changeDp(85) * ((recipe.getNeededIngredients().size() - 1) / 3 + 1));
        grid_p.leftMargin = changeDp(20);
        grid_p.rightMargin = changeDp(20);
        grid_p.topMargin = changeDp(10);
        gridView.setLayoutParams(grid_p);
        gridView.setAdapter(adapter);

        // 레시피
        for (int i = 0; i < recipe.getContents().size(); i++) {
            LinearLayout.LayoutParams ll_p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams tv1_p = new LinearLayout.LayoutParams(changeDp(30), changeDp(30));
            LinearLayout.LayoutParams tv2_p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, changeDp(60));
            LinearLayout ll = new LinearLayout(getApplicationContext());
            TextView tv1 = new TextView(getApplicationContext());
            TextView tv2 = new TextView(getApplicationContext());

            ll.setLayoutParams(ll_p);

            tv1_p.topMargin = changeDp(20);
            tv1_p.leftMargin = changeDp(20);
            tv1.setBackgroundResource(R.drawable.button5);
            tv1.setLayoutParams(tv1_p);
            tv1.setGravity(1);
            tv1.setText(Integer.toString(i + 1));
            tv1.setTextSize(20);
            tv1.setTextColor(getApplicationContext().getResources().getColor(R.color.white));

            tv2_p.topMargin = changeDp(15);
            tv2_p.leftMargin = changeDp(15);
            tv2_p.rightMargin = changeDp(20);
            tv2.setLayoutParams(tv2_p);
            tv2.setText(recipe.getContents().get(i));
            tv2.setTextColor(getApplicationContext().getResources().getColor(R.color.black));
            tv2.setGravity(Gravity.CENTER_VERTICAL);
            ll.addView(tv1);
            ll.addView(tv2);
            ll_recipe_view_recipe.addView(ll);
        }

        // 사진
        for (int i = 0; i < recipe.getPictures().size(); i++) {
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(changeDp(90), changeDp(90));
            ImageView iv_recipe_view_picture = new ImageView(this);
            p.leftMargin = changeDp(15);
            iv_recipe_view_picture.setLayoutParams(p);
            iv_recipe_view_picture.setImageBitmap(recipe.getPictures().get(i));
            ll_recipe_view_picture.addView(iv_recipe_view_picture);
        }

        btn_recipe_view_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Ingredient i : menu.getRecipe().getNeededIngredients()) {
                    for (Ingredient j : Customer.getInstance().getOwned()) {
                        if (i.getName().equals(j.getName())) {
                            double temp = j.getAmount() - i.getAmount();
                            if (temp <= 0)
                                Customer.getInstance().getOwned().remove(j);
                            else
                                j.setAmount(temp);
                            break;
                        }
                    }
                }

                EatenMenu eaten = new EatenMenu();
                eaten.setName(menu.getName());
                eaten.setViews(menu.getViews());
                eaten.setRecipe(menu.getRecipe());

                Customer.getInstance().getEatenMenu().add(eaten);
                user_dam = new User_DAM(getApplicationContext(),"dbuser0.db");
                user_control = new User_Control(user_dam);
                user_control.add_db_eaten(eaten);
                user_control.update_db();
                user_control.db_close();
                finish();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                final Ingredient item = (Ingredient) adapter.getItem(a_position);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getPurchaseLink() + item.getName()));
                startActivity(intent);
            }
        });

        btn_recipe_view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public int changeDp(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }
}