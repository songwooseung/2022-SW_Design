package com.example.sdtest.User.Customer;

import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.Nutrient.DietManagement;
import com.example.sdtest.Nutrient.EatenMenu;
import com.example.sdtest.User.User;

import java.util.ArrayList;

public class Customer extends User {
    private static Customer instance = null;
    private boolean isActivity = true;
    private ArrayList<Ingredient> owned = new ArrayList<Ingredient>();
    private ArrayList<EatenMenu> eatenMenu = new ArrayList<EatenMenu>();
    private DietManagement dietManagement = new DietManagement();

    public void addOwnedIngredient(Ingredient i) {
        owned.add(i);
    }
    public void addEatenMenu(EatenMenu em) {
        eatenMenu.add(em);
    }

    // constructor & set & get
    private Customer() {

    }

    public static Customer getInstance() {
        if (instance == null)
            instance = new Customer();
        return instance;
    }

    public void setIsActivity(boolean isActivity) {
        this.isActivity = isActivity;
    }

    public boolean getIsActivity() {
        return isActivity;
    }

    public void setOwned(ArrayList<Ingredient> owned) {
        this.owned = owned;
    }

    public ArrayList<Ingredient> getOwned() {
        return owned;
    }

    public void deleteOwnIngredient(Ingredient d) {
        owned.remove(d);
    }

    public void setEatenMenu(ArrayList<EatenMenu> eatenMenu) {
        this.eatenMenu = eatenMenu;
    }

    public ArrayList<EatenMenu> getEatenMenu() {
        return eatenMenu;
    }

    public DietManagement getDietManagement() {
        return dietManagement;
    }
}