package com.example.sdtest.Menu;

import java.util.ArrayList;
import java.util.Collections;

public class MenuList {
    private ArrayList<Menu> menuList;

    public void addMenu(Menu menu) {
        this.menuList.add(menu);
    }
    public void sortByViews() {
        Collections.sort(menuList, new MenuViewsComparator());
    }
    public void sortByRatio() {
        Collections.sort(menuList, new MenuRatioComparator());
    }

    // set & get
    public void setMenuList(ArrayList<Menu> menuList) {
        this.menuList = menuList;
    }

    public ArrayList<Menu> getMenuList() {
        return menuList;
    }
}
