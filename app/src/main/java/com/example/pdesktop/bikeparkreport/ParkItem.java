package com.example.pdesktop.bikeparkreport;

/**
 * Created by pedrodecastro on 4/3/16.
 */
public class ParkItem {


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
