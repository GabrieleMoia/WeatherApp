package com.example.giulia.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Giulia on 30/03/2018.
 */

public class DataAccessSource {

    public static void initDataSource(Context context){
        Città currentPosition = new Città("Posizione Corrente");
        Città milano=new Città("Milano");
        Città roma=new Città("Roma");
        Città firenze=new Città("Firenze");
        addItem(currentPosition,context);
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
    public static String getCurrentCityName(Context context){
        LocationManager locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria=new Criteria();

        locationManager.getBestProvider(criteria,true);
        @SuppressLint("MissingPermission") Location location=locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria,true));
        Geocoder geocoder=new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        String cityName="";
        try{
            addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            if(addresses.size()>0){
                cityName=addresses.get(0).getLocality().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityName;
    }
}
