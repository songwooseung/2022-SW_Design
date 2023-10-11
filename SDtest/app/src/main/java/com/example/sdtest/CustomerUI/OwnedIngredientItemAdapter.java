package com.example.sdtest.CustomerUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sdtest.HomeUI.IngredientList;
import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.R;
import com.example.sdtest.User.Customer.Customer;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OwnedIngredientItemAdapter extends BaseAdapter {
    public android.widget.Toast Toast;
    private ArrayList<Ingredient> items = IngredientList.getInstance().getIngredientList();

    public void setItems(ArrayList<Ingredient> ingredients) {
        this.items = ingredients;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        final Ingredient ingredientItem = items.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_owned_ingredient, viewGroup, false);

        TextView tv_owned_ingredient_item_name = (TextView) convertView.findViewById(R.id.tv_owned_ingredient_item_name);
        tv_owned_ingredient_item_name.setText(ingredientItem.getName());

        TextView tv_owned_ingredient_item_amount = (TextView) convertView.findViewById(R.id.tv_owned_ingredient_item_amount);

        convertView.findViewById(R.id.tv_owned_ingredient_item_name).setBackgroundResource(R.drawable.button1);

        for(Ingredient e: Customer.getInstance().getOwned()){
            if(e.getName().equals(ingredientItem.getName())){
                DecimalFormat df = new DecimalFormat("#.##");
                tv_owned_ingredient_item_amount.setText(df.format(e.getAmount()));
                convertView.findViewById(R.id.tv_owned_ingredient_item_name).setBackgroundResource(R.drawable.button4);
                break;
            }
        }

        TextView tv_owned_ingredient_item_unit = (TextView) convertView.findViewById(R.id.tv_owned_ingredient_item_unit);
        tv_owned_ingredient_item_unit.setText(ingredientItem.getUnit());
        return convertView;
    }
}
