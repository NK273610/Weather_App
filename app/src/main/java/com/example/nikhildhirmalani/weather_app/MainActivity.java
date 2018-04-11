package com.example.nikhildhirmalani.weather_app;

import android.app.Activity;

import android.app.ProgressDialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;

import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class  MainActivity extends AppCompatActivity {

    Button btnHit;

    public static TextView cityname,temperature,tem_min,temp_max,weather_desc,weather_more,humidity_perc,clouds_perc; //all feilds in activity main declared
    ProgressDialog pd;
    String my_edit_text_value="";
    String description;

    String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHit=findViewById(R.id.button);

        cityname=findViewById(R.id.cityname); //all feilds instantiated
        temperature=findViewById(R.id.temperature);
        tem_min=findViewById(R.id.temp_min);
        temp_max=findViewById(R.id.temp_max);
        weather_desc=findViewById(R.id.weather_desc);
        weather_more=findViewById(R.id.weather_more_desc);
        humidity_perc=findViewById(R.id.humidity_oerc);
        clouds_perc=findViewById(R.id.clouds_perc);



        final MainActivity a=this;

        final AutoCompleteTextView autocompleteView = (AutoCompleteTextView)findViewById(R.id.autocomplete); //autocomplete view
        autocompleteView.setAdapter(new PlacesAutoCompleteAdapter(this,R.layout.autocomplete_list_item));

        autocompleteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get data associated with the specified position
                // in the list (AdapterView)
                 description = (String) parent.getItemAtPosition(position);


            }
        });
        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboard(view); //hide Keyboard method called
                if(autocompleteView.getText().toString().equalsIgnoreCase(description)) {
                    url = "http://api.openweathermap.org/data/2.5/weather?q=" + description + "&APPID=ad32c34a50ac2da5e7ec337d8d220177";
                    DataFetcher df = new DataFetcher(a);
                    df.execute(url);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please enter correct city name",Toast.LENGTH_SHORT).show();
                }

                description=null;

            }
        });
    }



    public void hideKeyboard(View view) { //method to make keyboard go down after pressing button
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}


