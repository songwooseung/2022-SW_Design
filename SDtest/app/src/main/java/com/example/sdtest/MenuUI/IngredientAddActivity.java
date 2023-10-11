package com.example.sdtest.MenuUI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.HomeUI.IngredientList;
import com.example.sdtest.R;

import java.util.ArrayList;

public class IngredientAddActivity extends AppCompatActivity {
    private GridView gridView;
    private IngredientItemAdapter adapter;
    private EditText et_ingredient_add_search;
    private Button btn_ingredient_add_search;
    private Button btn_ingredient_add_back;
    private Button btn_yes;
    private Button btn_no;

    private ArrayList<Ingredient> ingredients = IngredientList.getInstance().getIngredientList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_add);

        gridView = findViewById(R.id.gridview);
        adapter = new IngredientItemAdapter();
        gridView.setAdapter(adapter);
        et_ingredient_add_search = findViewById(R.id.et_ingredient_add_search);
        btn_ingredient_add_search = findViewById(R.id.btn_ingredient_add_search);
        btn_ingredient_add_back = findViewById(R.id.btn_back);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                final Ingredient item = (Ingredient) adapter.getItem(a_position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(IngredientAddActivity.this);
                AlertDialog ad = dialog.create();
                ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ad.requestWindowFeature(Window.FEATURE_NO_TITLE);
                View vd = (View) View.inflate(IngredientAddActivity.this, R.layout.dialog_ingredient_amount, null);
                ad.setView(vd);

                TextView tv = vd.findViewById(R.id.tv_ingredient_amount_name);
                tv.setText(item.getName());
                tv = vd.findViewById(R.id.tv_ingredient_amount_unit);
                tv.setText(item.getUnit());

                btn_yes = vd.findViewById(R.id.btn_yes);
                btn_no = vd.findViewById(R.id.btn_no);

                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText et = vd.findViewById(R.id.et_ingredient_amount);
                        try {
                            Double temp = Double.parseDouble(et.getText().toString());
                            Intent intent = new Intent();
                            intent.putExtra("ingredient", item);
                            intent.putExtra("amount", temp);
                            setResult(RESULT_OK, intent);
                            finish();
                        } catch (NumberFormatException e) {
                            ad.dismiss();
                            AlertDialog.Builder dialog = new AlertDialog.Builder(IngredientAddActivity.this);
                            AlertDialog subAd = dialog.create();
                            subAd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            subAd.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            View subVd = (View) View.inflate(IngredientAddActivity.this, R.layout.dialog_check, null);
                            subAd.setView(subVd);
                            Button btn_check_yes = subVd.findViewById(R.id.btn_check_yes);

                            btn_check_yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    subAd.dismiss();
                                }
                            });

                            subAd.show();
                            WindowManager.LayoutParams subP = subAd.getWindow().getAttributes();
                            subP.width = changeDp(300);
                            subAd.getWindow().setAttributes(subP);
                        }
                    }
                });

                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ad.dismiss();
                    }
                });

                ad.show();
                WindowManager.LayoutParams p = ad.getWindow().getAttributes();
                p.width = changeDp(300);
                ad.getWindow().setAttributes(p);
            }
        });

        btn_ingredient_add_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(et_ingredient_add_search.getText().toString().equals(""))) {
                    ArrayList<Ingredient> items = new ArrayList<Ingredient>();

                    InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    for (Ingredient e : ingredients) {
                        if (e.getName().equals(et_ingredient_add_search.getText().toString()))
                            items.add(e);
                    }
                    if (items.size() > 0) {
                        adapter.setItems(items);
                        gridView.setAdapter(adapter);
                    } else {
                        adapter.setItems(ingredients);
                        gridView.setAdapter(adapter);
                    }
                    et_ingredient_add_search.setText(null);
                } else {
                    adapter.setItems(ingredients);
                    gridView.setAdapter(adapter);
                }
            }
        });

        btn_ingredient_add_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public int changeDp(int value) {
        int changed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
        return changed;
    }
}

