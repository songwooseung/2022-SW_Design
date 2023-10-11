package com.example.sdtest.MenuUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

class NeededIngredientItemAdapter extends BaseAdapter {
    private ArrayList<Ingredient> items;

    public NeededIngredientItemAdapter(ArrayList<Ingredient> ingredients) {
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
        final Ingredient neededIngredientItem = items.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_needed_ingredient, viewGroup, false);

        // 준비물 이름 설정
        TextView tv_needed_ingredient_name = (TextView) convertView.findViewById(R.id.tv_needed_ingredient_item_name);
        tv_needed_ingredient_name.setText(neededIngredientItem.getName());
        // 준비물 양 설정
        TextView tv_needed_ingredient_item_amount = (TextView) convertView.findViewById(R.id.tv_needed_ingredient_item_amount);
        DecimalFormat df = new DecimalFormat("#.##");
        tv_needed_ingredient_item_amount.setText(df.format(neededIngredientItem.getAmount()) + neededIngredientItem.getUnit());

        return convertView;  //뷰 객체 반환
    }
}
