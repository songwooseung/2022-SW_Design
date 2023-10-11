package com.example.sdtest.Menu;

public class SideMenuList extends MenuList {
    private static SideMenuList instance = null;

    private SideMenuList(){}

    public static SideMenuList getInstance(){
        if(instance == null)
            instance = new SideMenuList();
        return instance;
    }
}
