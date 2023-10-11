package com.example.sdtest.Nutrient;

import com.example.sdtest.Menu.Menu;

import java.util.Date;

public class EatenMenu extends Menu {
    private long date;
    // private Customer customer;

    // constructor & get
    public EatenMenu() {
        Date now = new Date();
        now.getTime();
        date = now.getTime();
    }

    public long getDate() {
        return date;
    }
}