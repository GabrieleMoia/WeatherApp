package com.example.giulia.weatherapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Giulia on 30/03/2018.
 */

public class DetailActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 10;
    String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        Intent intent = getIntent();
        cityName = intent.getStringExtra("name");

        TextView city = (TextView) findViewById(R.id.città);
        city.setText(cityName);

        if (cityName.equals("Posizione Corrente")) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

                    new AlertDialog.Builder(this)
                            .setTitle("Permessi")
                            .setMessage("Permessi")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Prompt the user once explanation has been shown
                                    ActivityCompat.requestPermissions(DetailActivity.this,
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                            REQUEST_LOCATION);
                                }
                            })
                            .create()
                            .show();
                } else {

                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_LOCATION);
                }
            } else {
                cityName = DataAccessSource.getCurrentCityName(getApplicationContext());
                String url = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=2439d518e81cee90fd7a61cfe1109dd4";

                GsonRequest jsonObjectRequest = new GsonRequest(url, WeatherOutput.class, null, new Response.Listener<WeatherOutput>() {
                    @Override
                    public void onResponse(WeatherOutput response) {
                        TextView città = (TextView) findViewById(R.id.città);
                        città.setText(cityName);
                        TextView temperature = (TextView) findViewById(R.id.gradi);
                        TextView tempo = (TextView) findViewById(R.id.tempo);
                        temperature.setText(response.getMain().getTemp());
                        tempo.setText(response.getWeathers(0).getMain());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TextView details = (TextView) findViewById(R.id.gradi);
                        details.setText(error.getMessage());
                    }
                });

                ServiceQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
            }
        } else {
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=2439d518e81cee90fd7a61cfe1109dd4";

            GsonRequest jsonObjectRequest = new GsonRequest(url, WeatherOutput.class, null, new Response.Listener<WeatherOutput>() {
                @Override
                public void onResponse(WeatherOutput response) {
                    TextView città = (TextView) findViewById(R.id.città);
                    città.setText(cityName);
                    TextView temperature = (TextView) findViewById(R.id.gradi);
                    TextView tempo = (TextView) findViewById(R.id.tempo);
                    temperature.setText(response.getMain().getTemp());
                    tempo.setText(response.getWeathers(0).getMain());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    TextView details = (TextView) findViewById(R.id.gradi);
                    details.setText(error.getMessage());
                }
            });

            ServiceQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        cityName = DataAccessSource.getCurrentCityName(getApplicationContext());
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Permesso Negato", Toast.LENGTH_SHORT);
                    }
                }
            }
        }
    }
}