package com.example.sdtest.Menu;

public class MainMenuList extends MenuList {
    private static MainMenuList instance = null;

    private MainMenuList(){}

    public static MainMenuList getInstance(){
        if(instance == null)
            instance = new MainMenuList();
        return instance;
    }
}
