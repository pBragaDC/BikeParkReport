package com.example.pdesktop.bikeparkreport;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    JSONObject json;

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
        try {
            json = new JSONObject(readUrl(url));

            //String title = (String) json.get("title");

        } catch (Exception e) {
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

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public String getIconID() throws JSONException {
        iconID = (String) json.get("icon");
        return iconID;
    }

    public void setIconID(String iconID) {
        this.iconID = iconID;
    }

    public String getCurTemp() {
        try {
            curTemp = (String) json.get("temp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return curTemp;
    }

    public void setCurTemp(String curTemp) {
        this.curTemp = curTemp;
    }

    public String getMinTemp() {
        try {
            minTemp = (String) json.get("temp_min");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp()  {
        try {
            maxTemp = (String) json.get("temp_max");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getHumidity() {
        try {
            humidity = (String) json.get("humidity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCloud() {
        try {
            cloud = (String) json.get("clouds");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getSnow() {
        return snow;
    }

    public void setSnow(String snow) {
        this.snow = snow;
    }

    public String getSunriseTime()  {
        try {
            sunriseTime = (String) json.get("sunrise");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime()  {
        try {
            sunriseTime = (String) json.get("sunset");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }
}
