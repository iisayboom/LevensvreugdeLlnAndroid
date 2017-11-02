package com.example.dreeki.projectleerlingenapp.Models;

/**
 * Created by dreeki on 26/10/17.
 */

public class Destination {
    private String picture;
    private String street;
    private String city;
    private int number;
    private int postalCode;

    public Destination(String picture, String street, String city, int number, int postalCode){
        this.picture = picture;
        this.street = street;
        this.city = city;
        this.number = number;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getPicture() {
        return picture;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
