package com.example.giulia.weatherapp;

import android.content.Context;

import java.util.List;

/**
 * Created by Giulia on 30/03/2018.
 */

public class DataAccessSource {

    public static void initDataSource(Context context){
        Città milano=new Città("Milano");
        Città roma=new Città("Roma");
        Città firenze=new Città("Firenze");
        addItem(milano, context);
        addItem(roma,context);
        addItem(firenze,context);
    }

    public static List<Città> getDataSourceItemList(Context context) {
        return Singleton.getInstance().getItemList();
    }

    public static void addItem(Città città, Context context){
        Singleton.getInstance().getItemList().add(città);
    }
    public void removeItem(Città città, Context context){
        Singleton.getInstance().getItemList().remove(città);
    }
    public static Città getItemByPosition(Context context, int position){
        return Singleton.getInstance().getItemList().get(position);
    }
}
