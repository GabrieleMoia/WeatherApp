package com.example.giulia.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.w3c.dom.Text;

/**
 * Created by Giulia on 30/03/2018.
 */

public class DetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        Intent intent=getIntent();
        String cityName=intent.getStringExtra("name");

        TextView city=(TextView)findViewById(R.id.città);
        city.setText(cityName);

        String url="http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&units=metric&appid=2439d518e81cee90fd7a61cfe1109dd4";

        GsonRequest jsonObjectRequest=new GsonRequest(url, WeatherOutput.class, null, new Response.Listener<WeatherOutput>() {
            @Override
            public void onResponse(WeatherOutput response) {
                TextView temperature =(TextView)findViewById(R.id.gradi);
                TextView tempo=(TextView) findViewById(R.id.tempo);
                temperature.setText(response.getMain().getTemp());
                tempo.setText(response.getWeathers(0).getMain());
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView details = (TextView) findViewById(R.id.gradi);
                details.setText(error.getMessage());
            }
        });

        ServiceQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}
