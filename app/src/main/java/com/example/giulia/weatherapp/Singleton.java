package com.example.giulia.weatherapp;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Giulia on 12/02/2018.
 */

public class Singleton {

    //base di appoggio per i nostri dati
    private static Singleton ourInstance = new Singleton();
    private List cityList = new ArrayList<Città>();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {

    }

    public void setItemList(List<Città> cityList) {
        this.cityList = cityList;
    }

    public List<Città> getItemList() {
        return this.cityList;
    }
}
