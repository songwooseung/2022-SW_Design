package com.example.sdtest.MenuUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.HomeUI.IngredientList;
import com.example.sdtest.R;

import java.util.ArrayList;

public class IngredientItemAdapter extends BaseAdapter {
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
        convertView = inflater.inflate(R.layout.item_ingredient, viewGroup, false);

        TextView tv_ingredient_item_name = (TextView) convertView.findViewById(R.id.tv_ingredient_item_name);
        tv_ingredient_item_name.setText(ingredientItem.getName());

        return convertView;
    }
}
