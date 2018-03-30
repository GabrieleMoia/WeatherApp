package com.example.giulia.weatherapp;

/**
 * Created by Giulia on 30/03/2018.
 */

public class WeatherOutput {

    int id;
    String name;
    main main;
    weather[] weather=new weather[Singleton.getInstance().getItemList().size()];

    public weather getWeathers(int position) {
        return weather[position];
    }

    public void setWeathers(com.example.giulia.weatherapp.weather[] weathers) {
        this.weather = weathers;
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public main getMain() {
        return main;
    }

    public void setMain(main main) {
        this.main = main;
    }
}
