package com.example.nikhildhirmalani.weather_app;

import android.os.AsyncTask;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nikhildhirmalani on 06/03/18.
 */

//Class to fetch data for the given city from wathermap api

public class DataFetcher extends AsyncTask<String,String,Void> {
    String buffer=""; //ALl variables used are declared
    JSONArray json_array;
    JSONObject json_obj;
    MainActivity activity;
    DataClass data;
    DataFetcher(MainActivity obj)
    {
        activity=obj;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url=new URL(strings[0]);
            connection= (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));


            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer=buffer+line+"\n";

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        try {
            data=new DataClass();
            JSONObject reader= new JSONObject(buffer);
            JSONArray weatherArray = reader.getJSONArray("weather");
            JSONObject weather_obj0 = weatherArray.getJSONObject(0);
            JSONObject cloud=reader.getJSONObject("clouds");
            JSONObject temp=reader.getJSONObject("main");

            data.setWeather_desc(weather_obj0.getString("description")); //all values set to data object of data class
            data.setWeather_main(weather_obj0.getString("main"));
            data.setCity_name(reader.getString("name"));
            data.setTemperature(temp.getString("temp"));
            data.setTemp_min(temp.getString("temp_min"));
            data.setTemp_max(temp.getString("temp_max"));
            data.setCloud_perc(cloud.getString("all"));
            data.setHumidity_perc(temp.getString("humidity"));

            activity.runOnUiThread(new Runnable() {
                public void run() {
                    MainActivity.cityname.setText(data.getCity_name());   //all the values from data obj set to the Mainactivity feilds
                    MainActivity.humidity_perc.setText(data.getHumidity_perc() + "%\nHumidity");
                    MainActivity.clouds_perc.setText(data.getCloud_perc() + "%\nClouds");
                    MainActivity.temp_max.setText("Max." + temp_change(data.getTemp_max()) + (char) 0x00B0 + " C");
                    MainActivity.tem_min.setText("Min. " + temp_change(data.getTemp_min()) + (char) 0x00B0 + " C");
                    MainActivity.temperature.setText(temp_change(data.getTemperature()) + (char) 0x00B0 + " C");
                    MainActivity.weather_desc.setText(data.getWeather_desc());
                    MainActivity.weather_more.setText(data.getWeather_main());
                }});



        } catch (JSONException e) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(activity, "Please enter correct city name", Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        }

    }

    public static String temp_change(String s)  //tempchange method to change temperature from kelvin to celcius
    {
        float temp=Float.parseFloat(s);
        temp= (float) (temp-273.15);

        return (String.valueOf(Math.round(temp)));
    }
}
