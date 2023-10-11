package com.example.sdtest.CustomerUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sdtest.Menu.Recipe;
import com.example.sdtest.Nutrient.EatenMenu;
import com.example.sdtest.R;
import com.example.sdtest.User.Customer.Customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EatenAdapter extends BaseAdapter {
    public android.widget.Toast Toast;
    ArrayList<EatenMenu> items = Customer.getInstance().getEatenMenu();

    @Override
    public int getCount() {
        return items.size();
    }

    public void setItems(ArrayList<EatenMenu> items) {
        this.items = items;
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
        final EatenMenu menuItem = items.get(position);
        final Recipe recipe = menuItem.getRecipe();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_eaten, viewGroup, false);

        ImageView iv_eaten_item_icon = (ImageView) convertView.findViewById(R.id.iv_eaten_item_icon);
        TextView tv_eaten_item_name = (TextView) convertView.findViewById(R.id.tv_eaten_item_name);
        TextView tv_eaten_item_date = (TextView) convertView.findViewById(R.id.tv_eaten_item_date);

        tv_eaten_item_name.setText(menuItem.getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        tv_eaten_item_date.setText(dateFormat.format(menuItem.getDate()));

        if (recipe.getPictures().size() > 0)
            iv_eaten_item_icon.setImageBitmap(recipe.getPictures().get(recipe.getPictures().size() - 1));

        return convertView;  //뷰 객체 반환
    }
}
