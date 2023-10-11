package com.example.sdtest.MenuUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sdtest.Menu.MainMenuList;
import com.example.sdtest.Menu.Menu;
import com.example.sdtest.Menu.SideMenuList;
import com.example.sdtest.Menu.SoupMenuList;
import com.example.sdtest.Menu.MenuList;
import com.example.sdtest.R;
import com.example.sdtest.User.Customer.Customer;

public class MenuActivity extends AppCompatActivity {
    private MainMenuList mainList = MainMenuList.getInstance();
    private SideMenuList sideList = SideMenuList.getInstance();
    private SoupMenuList soupList = SoupMenuList.getInstance();
    private Customer customer = Customer.getInstance();

    private MenuList currList = mainList;
    private int category = 0;
    private int sortType = 0;
    private GridView gridView;
    private GridViewAdapter adapter;

    private Button btn_main;
    private Button btn_side;
    private Button btn_soup;
    private Button btn_sortViews;
    private Button btn_sortRatio;
    private Button btn_menuAdd;
    private Button btn_to_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        for (int i = 0; i < mainList.getMenuList().size(); i++)
            mainList.getMenuList().get(i).getRecipe().setIngredientRatio(customer.getOwned());

        for (int i = 0; i < sideList.getMenuList().size(); i++)
            sideList.getMenuList().get(i).getRecipe().setIngredientRatio(customer.getOwned());

        for (int i = 0; i < soupList.getMenuList().size(); i++)
            soupList.getMenuList().get(i).getRecipe().setIngredientRatio(customer.getOwned());

        btn_main = findViewById(R.id.btn_main);
        btn_side = findViewById(R.id.btn_side);
        btn_soup = findViewById(R.id.btn_soup);
        btn_sortViews = findViewById(R.id.btn_sortViews);
        btn_sortRatio = findViewById(R.id.btn_sortRatio);
        btn_menuAdd = findViewById(R.id.btn_menuAdd);
        btn_to_home = findViewById(R.id.btn_to_home);
        gridView = findViewById(R.id.gridview);
        adapter = new GridViewAdapter();
        adapter.setItems(currList.getMenuList());
        gridView.setAdapter(adapter);

        btn_main.setBackgroundResource(R.drawable.button2);
        btn_main.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
        btn_side.setBackgroundResource(R.drawable.button1);
        btn_side.setTextColor(getApplicationContext().getResources().getColor(R.color.pink3));
        btn_soup.setBackgroundResource(R.drawable.button1);
        btn_soup.setTextColor(getApplicationContext().getResources().getColor(R.color.pink3));

        // "메인" 버튼 누를시
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = 0;
                currList = mainList;
                sort(sortType);

                // getMenuList()
                adapter.setItems(currList.getMenuList());

                adapter.notifyDataSetChanged();
                // 화면에 보여줌
                gridView.setAdapter(adapter);
                btn_main.setBackgroundResource(R.drawable.button2);
                btn_main.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
                btn_side.setBackgroundResource(R.drawable.button1);
                btn_side.setTextColor(getApplicationContext().getResources().getColor(R.color.pink3));
                btn_soup.setBackgroundResource(R.drawable.button1);
                btn_soup.setTextColor(getApplicationContext().getResources().getColor(R.color.pink3));
            }
        });
        // "반찬" 버튼 누를시
        btn_side.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = 1;
                currList = sideList;
                sort(sortType);

                // getMenuList()
                adapter.setItems(currList.getMenuList());

                adapter.notifyDataSetChanged();
                // 화면에 보여줌
                gridView.setAdapter(adapter);
                btn_main.setBackgroundResource(R.drawable.button1);
                btn_main.setTextColor(getApplicationContext().getResources().getColor(R.color.pink3));
                btn_side.setBackgroundResource(R.drawable.button2);
                btn_side.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
                btn_soup.setBackgroundResource(R.drawable.button1);
                btn_soup.setTextColor(getApplicationContext().getResources().getColor(R.color.pink3));
            }
        });
        // "국" 버튼 누를시
        btn_soup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = 2;
                currList = soupList;
                sort(sortType);

                // getMenuList()
                adapter.setItems(currList.getMenuList());

                adapter.notifyDataSetChanged();
                // 화면에 보여줌
                gridView.setAdapter(adapter);
                btn_main.setBackgroundResource(R.drawable.button1);
                btn_main.setTextColor(getApplicationContext().getResources().getColor(R.color.pink3));
                btn_side.setBackgroundResource(R.drawable.button1);
                btn_side.setTextColor(getApplicationContext().getResources().getColor(R.color.pink3));
                btn_soup.setBackgroundResource(R.drawable.button2);
                btn_soup.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
            }
        });
        // "인기순" 버튼 누를시
        btn_sortViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortType = 0;

                // sortByViews()
                currList.sortByViews();

                adapter.notifyDataSetChanged();
                gridView.setAdapter(adapter);
            }
        });
        // "비율순" 버튼 누를시
        btn_sortRatio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortType = 1;

                // sortByRatio()
                currList.sortByRatio();

                adapter.notifyDataSetChanged();
                gridView.setAdapter(adapter);
            }
        });
        // "메뉴 추가" 버튼 누를시
        btn_menuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MenuAddActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                Intent intent = new Intent(MenuActivity.this, RecipeViewActivity.class);
                intent.putExtra("category", category);
                intent.putExtra("position", a_position);
                startActivity(intent);
            }
        });
        btn_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void sort(int sortType) {
        if (sortType == 0)
            currList.sortByViews();
        else
            currList.sortByRatio();
    }

    @Override
    protected void onResume() {
        super.onResume();

        for(Menu e:mainList.getMenuList())
            e.getRecipe().setIngredientRatio(customer.getOwned());
        for(Menu e:sideList.getMenuList())
            e.getRecipe().setIngredientRatio(customer.getOwned());
        for(Menu e:soupList.getMenuList())
            e.getRecipe().setIngredientRatio(customer.getOwned());

        sort(sortType);

        adapter.setItems(currList.getMenuList());
        gridView.setAdapter(adapter);
    }
}