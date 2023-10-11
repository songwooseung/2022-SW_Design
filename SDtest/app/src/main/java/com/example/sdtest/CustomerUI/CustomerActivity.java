package com.example.sdtest.CustomerUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.*;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.GridView;

import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.HomeUI.IngredientList;
import com.example.sdtest.MenuUI.IngredientItemAdapter;
import com.example.sdtest.Nutrient.EatenMenu;
import com.example.sdtest.R;
import com.example.sdtest.User.Customer.Customer;

import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {
    private ArrayList<Ingredient> ingredients = IngredientList.getInstance().getIngredientList();
    private Customer customer = Customer.getInstance();
    private GridView ownedGridView;
    private GridView eatenGridView;
    private IngredientItemAdapter ownedAdapter;
    private EatenAdapter eatenAdapter;
    private EditText et_ingredient_add_search;
    private Button btn_ingredient_add_search;
    private Button btn_yes;
    private Button btn_no;
    private Button btn_to_home;
    private Button btn_customer_owned;
    private Button btn_customer_eaten;
    private Button btn_customer_diet;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        btn_to_home = findViewById(R.id.btn_to_home);
        btn_customer_owned = findViewById(R.id.btn_customer_owned);
        btn_customer_eaten = findViewById(R.id.btn_customer_eaten);
        btn_customer_diet = findViewById(R.id.btn_customer_diet);

        btn_customer_owned.setBackgroundResource(R.drawable.button6);

        fragmentView(1);

        btn_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_customer_owned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_customer_owned.setBackgroundResource(R.drawable.button6);
                btn_customer_eaten.setBackgroundResource(R.drawable.button2);
                btn_customer_diet.setBackgroundResource(R.drawable.button2);
                fragmentView(1);
            }
        });

        btn_customer_eaten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_customer_owned.setBackgroundResource(R.drawable.button2);
                btn_customer_eaten.setBackgroundResource(R.drawable.button6);
                btn_customer_diet.setBackgroundResource(R.drawable.button2);
                fragmentView(2);
            }
        });
        // "피드백" 버튼을 누를시
        btn_customer_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getEatenMenu()
                ArrayList<EatenMenu> eatenMenus = Customer.getInstance().getEatenMenu();

                // caculateNutritionFacts(ArrayList<EatenMenu>)
                Customer.getInstance().getDietManagement().caculateNutritionFacts(eatenMenus);

                btn_customer_owned.setBackgroundResource(R.drawable.button2);
                btn_customer_eaten.setBackgroundResource(R.drawable.button2);
                btn_customer_diet.setBackgroundResource(R.drawable.button6);
                fragmentView(3);
            }
        });
    }

    private void fragmentView(int type) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (type) {
            case 1:
                OwnedFragment ownedFragment = new OwnedFragment();
                transaction.replace(R.id.fragment_container, ownedFragment);
                transaction.commit();
                break;
            case 2:
                EatenFragment eatenFragment = new EatenFragment();
                transaction.replace(R.id.fragment_container, eatenFragment);
                transaction.commit();
                break;
            case 3:
                DietFragment dietFragment = new DietFragment();
                transaction.replace(R.id.fragment_container, dietFragment);
                transaction.commit();
                break;
        }

    }
}
