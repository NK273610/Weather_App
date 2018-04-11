package com.example.nikhildhirmalani.weather_app;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * Created by nikhildhirmalani on 15/03/18.
 */

//code taken from site http://codetheory.in/google-place-api-autocomplete-service-in-android-application/
//I have taken code to fetch the cities from google api using the following link
//I have taken code directly from this site made minor changes but whole class code is from given link

public class GooglePlaceAPI {


    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    MainActivity activity;
    private static final String API_KEY = "AIzaSyAuPA0-uOPx-aHkuqj9AxGAPnwaoEzjSmw";

    public GooglePlaceAPI() {
    }

    GooglePlaceAPI(MainActivity obj)
    {
        activity=obj;
    }
    public ArrayList<String> autocomplete (String input) {
        ArrayList<String> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();

        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            sb.append("&types=(cities)");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());


            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);

            }
         
        } catch (MalformedURLException e) {
            Log.e("Error", "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e("Error", "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {

            Log.e("Error", "Cannot process JSON results", e);
        }

        return resultList;
    }
}
