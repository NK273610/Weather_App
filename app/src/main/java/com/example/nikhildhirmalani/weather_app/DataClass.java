package com.example.nikhildhirmalani.weather_app;

/**
 * Created by nikhildhirmalani on 07/03/18.
 */

//Data class to store the data
public class DataClass {

    String city_name;    //city_name to store name of city
    String weather_desc; //weather_desc to store weather description
    String weather_main; //weather_main to store weather main from api
    String temp_min; //temp_min to store min temperature
    String temp_max; //temp_max to store max temperature
    String humidity_perc; //humdiity_perc to store humidity percentage
    String cloud_perc; //cloud_perc to store clouds percenage
    String temperature; //temperature to store current temperature

    //Getter setters
    public DataClass() {
        super();
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getWeather_desc() {
        return weather_desc;
    }

    public void setWeather_desc(String weather_desc) {
        this.weather_desc = weather_desc;
    }

    public String getWeather_main() {
        return weather_main;
    }

    public void setWeather_main(String weather_main) {
        this.weather_main = weather_main;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getHumidity_perc() {
        return humidity_perc;
    }

    public void setHumidity_perc(String humidity_perc) {
        this.humidity_perc = humidity_perc;
    }

    public String getCloud_perc() {
        return cloud_perc;
    }

    public void setCloud_perc(String cloud_perc) {
        this.cloud_perc = cloud_perc;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    //tostring method
    @Override
    public String toString() {
        return "DataClass{" +
                "city_name='" + city_name + '\'' +
                ", weather_desc='" + weather_desc + '\'' +
                ", weather_main='" + weather_main + '\'' +
                ", temp_min='" + temp_min + '\'' +
                ", temp_max='" + temp_max + '\'' +
                ", humidity_perc='" + humidity_perc + '\'' +
                ", cloud_perc='" + cloud_perc + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}
