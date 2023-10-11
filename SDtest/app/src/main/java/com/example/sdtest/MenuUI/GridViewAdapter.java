package com.example.sdtest.MenuUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sdtest.Menu.Menu;
import com.example.sdtest.Menu.Recipe;
import com.example.sdtest.R;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    public android.widget.Toast Toast;
    ArrayList<Menu> items = new ArrayList<Menu>();

    @Override
    public int getCount() {
        return items.size();
    }

    public void setItems(ArrayList<Menu> items) {
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
        final Menu menuItem = items.get(position);
        final Recipe recipe = menuItem.getRecipe();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_menu, viewGroup, false);

        TextView tv_menu_item_name = (TextView) convertView.findViewById(R.id.tv_menu_item_name);
        TextView tv_menu_item_view = (TextView) convertView.findViewById(R.id.tv_menu_item_views);
        TextView tv_menu_item_ratio1 = (TextView) convertView.findViewById(R.id.tv_menu_item_ratio1);
        TextView tv_menu_item_ratio2 = (TextView) convertView.findViewById(R.id.tv_menu_item_ratio2);
        ImageView iv_menu_item_icon = (ImageView) convertView.findViewById(R.id.iv_menu_item_icon);

        tv_menu_item_name.setText(menuItem.getName());
        tv_menu_item_view.setText(Integer.toString(menuItem.getViews()));
        tv_menu_item_ratio1.setText(Integer.toString(recipe.getNeededIngredients().size()));
        tv_menu_item_ratio2.setText(Integer.toString(recipe.getOwnedIntredientCnt()));

        if (recipe.getPictures().size() > 0)
            iv_menu_item_icon.setImageBitmap(recipe.getPictures().get(recipe.getPictures().size() - 1));
        return convertView;
    }
}
