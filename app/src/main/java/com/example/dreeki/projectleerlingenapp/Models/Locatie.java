package com.example.dreeki.projectleerlingenapp.Models;

import com.example.dreeki.projectleerlingenapp.Interfaces.TravelingState;

import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;

/**
 * Created by dreeki on 26/10/17.
 */
@Entity
public class Locatie {
    private String image;
    private String street;
    private String city;
    private String number;
    private int postalCode;
    private String title;
    @Id
    public long id;
    private String aanwijzing;

    @Transient
    private TravelingState state;
    private List<Problem> problemen;

    public Locatie() {
    }

    public Locatie(long id, String street, String city, String number, int postalCode, String title){
        this(id,"",street,city,number,postalCode,title, "","");
    }

    public Locatie(long id, String picture, String street, String city, String number, int postalCode, String title){
        this(id,picture,street,city,number,postalCode,title, "","");
    }

    public Locatie(long id, String picture, String street, String city, String number, int postalCode, String title, String state){
        this(id,picture,street,city,number,postalCode,title, "",state);
    }

    public Locatie(long id, String picture, String street, String city, String number, int postalCode, String title, String aanwijzing, String state){
        this.image = picture;
        this.street = street;
        this.city = city;
        this.number = number;
        this.postalCode = postalCode;
        this.title = title;
        this.id = id;
        this.aanwijzing = aanwijzing;
        this.state = beslisState(state);
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

    public String getImage() {
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

    public void setImage(String picture) {
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

    public long getId() {
        return id;
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

    public List<Problem> getProblemen() {
        this.problemen = state.getProblemen();
        return this.problemen;
    }

    public TravelingState beslisState(String state) {
        TravelingState s;
        switch (state){
            case "Bus":
                s = new OnBus(toString());
                break;
            case "Fiets":
                s = new OnBike(toString());
                break;
            case "Trein":
                s = new OnTrain(toString());
                break;
            case "Te voet":
                s = new OnFoot(toString());
                break;
            default:
                s = new OnFoot(toString());
        }

        return this.state = s;
    }
}
