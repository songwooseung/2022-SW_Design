package com.example.sdtest.Menu;

public class SoupMenuList extends MenuList {
    private static SoupMenuList instance = null;

    private SoupMenuList(){}

    public static SoupMenuList getInstance(){
        if(instance == null)
            instance = new SoupMenuList();
        return instance;
    }
}
