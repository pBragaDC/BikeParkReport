package com.example.pdesktop.bikeparkreport;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;

/**
 * Created by pedrodecastro on 4/3/16.
 */
public class ParkItem {


    //firebase variables
    private String address;
    private String city;
    private String closeTime;
    private String description;
    private String fees;
    private float lat;
    private float lon;
    private String openTime;
    private String parkName;
    private String phone;
    private String state;
    private String website;
    private String zip;

    //weather api variables
    private String iconID;
    private String curTemp;
    private String minTemp;
    private String maxTemp;
    private String humidity;
    private String cloud;
    private String rain;
    private String snow;
    private String sunriseTime;
    private String sunsetTime;

    ParkItem()
    {}

    ParkItem( String address, String city, String closeTime
            , String description, String fees, float lat, float lon
            , String openTime, String parkName, String phone, String state, String website
            , String zip)
    {

        this.address = address;
        this.city = city;
        this.closeTime = closeTime;
        this.description = description;
        this.fees = fees;
        this.lat = lat;
        this.lon = lon;
        this.openTime = openTime;
        this.parkName = parkName;
        this.phone = phone;
        this.state = state;
        this.website = website;
        this.zip = zip;




        String url = "http://api.openweathermap.org/data/2.5/weather?zip=" + zip + ",us&APPID=5124a3a7f8fa3158eee99af01c581d23";
        /*
         * {"title":"Free Music Archive - Genres","message":"","errors":[],"total" : "161","total_pages":81,"page":1,"limit":"2",
         * "dataset":
         * [{"genre_id": "1","genre_parent_id":"38","genre_title":"Avant-Garde" ,"genre_handle": "Avant-Garde","genre_color":"#006666"},
         * {"genre_id":"2","genre_parent_id" :null,"genre_title":"International","genre_handle":"International","genre_color":"#CC3300"}]}
         */
        try {
            String genreJson = IOUtils.toString(new URL(url));
            JSONObject genreJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
            // get the title
            System.out.println(genreJsonObject.get("title"));
            // get the data
            JSONArray genreArray = (JSONArray) genreJsonObject.get("dataset");
            // get the first genre
            JSONObject firstGenre = (JSONObject) genreArray.get(0);
            System.out.println(firstGenre.get("genre_title"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
