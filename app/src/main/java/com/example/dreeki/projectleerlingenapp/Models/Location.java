package com.example.dreeki.projectleerlingenapp.Models;

import io.objectbox.annotation.Entity;

/**
 * Created by dreeki on 26/10/17.
 */

public class Location {
    private int image;
    private String street;
    private String city;
    private String number;
    private int postalCode;
    private String title;
    private String id;
    private String aanwijzing;

    public Location(int picture, String street, String city, String number, int postalCode, String title, String id, String aanwijzing){
        this.image = picture;
        this.street = street;
        this.city = city;
        this.number = number;
        this.postalCode = postalCode;
        this.title = title;
        this.id = id;
        this.aanwijzing = aanwijzing;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public int getImage() {
        return image;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setImage(int picture) {
        this.image = picture;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAanwijzing() {
        return aanwijzing;
    }

    public void setAanwijzing(String aanwijzing) {
        this.aanwijzing = aanwijzing;
    }

    @Override
    public String toString() {
        return this.street + " " + this.number + ", " + this.postalCode + " " + this.city;
    }
}
