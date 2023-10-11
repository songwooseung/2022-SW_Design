package com.example.sdtest.CustomerUI;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sdtest.DB.User_Control;
import com.example.sdtest.DB.User_DAM;
import com.example.sdtest.HomeUI.IngredientList;
import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.R;
import com.example.sdtest.User.Customer.Customer;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OwnedFragment extends Fragment {
    private GridView gridView;
    private OwnedIngredientItemAdapter adapter;
    private EditText et_ingredient_add_search;
    private Button btn_ingredient_add_search;
    private Button btn_yes;
    private Button btn_no;

    private User_DAM user_dam;
    private User_Control user_control;

    private ArrayList<Ingredient> ingredients = IngredientList.getInstance().getIngredientList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_owned, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView = view.findViewById(R.id.owned_gridview);
        adapter = new OwnedIngredientItemAdapter();
        gridView.setAdapter(adapter);

        btn_ingredient_add_search = view.findViewById(R.id.btn_ingredient_add_search);
        et_ingredient_add_search = view.findViewById(R.id.et_ingredient_add_search);

        user_dam = new User_DAM(getContext(), "dbuser0.db");
        user_control = new User_Control(user_dam);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                final Ingredient item = (Ingredient) adapter.getItem(a_position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                AlertDialog ad = dialog.create();
                View vd = (View) View.inflate(getActivity(), R.layout.dialog_ingredient_amount, null);
                ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ad.requestWindowFeature(Window.FEATURE_NO_TITLE);
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

                            if(temp > 0){
                                ArrayList<Ingredient> owned = Customer.getInstance().getOwned();
                                boolean exist = false;
                                for(Ingredient e:owned){
                                    if(e.getName().equals(item.getName())){
                                        exist = true;
                                        e.setAmount(temp);
                                        break;
                                    }
                                }
                                if(!exist){
                                    Ingredient ownedIngredient = new Ingredient(item.getName(), item.getCarbohydrate(), item.getProtein(), item.getFat(), item.getSodium(), item.getSugars(), temp, item.getUnit());
                                    owned.add(ownedIngredient);
                                    user_control.add_db_own_ingredient(ownedIngredient);
                                    user_control.update_db();
                                }

                                DecimalFormat df = new DecimalFormat("#.##");
                                TextView tv_owned_ingredient_item_amount = a_view.findViewById(R.id.tv_owned_ingredient_item_amount);
                                tv_owned_ingredient_item_amount.setText(df.format(temp));
                                a_view.findViewById(R.id.tv_owned_ingredient_item_name).setBackgroundResource(R.drawable.button4);
                            }
                            ad.dismiss();
                        } catch (NumberFormatException e) {
                            ad.dismiss();
                            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                            AlertDialog subAd = dialog.create();
                            subAd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            subAd.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            View subVd = (View) View.inflate(getActivity(), R.layout.dialog_check, null);
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

                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

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
    }

    public int changeDp(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }
}
