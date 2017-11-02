package com.example.dreeki.projectleerlingenapp.Models;

import com.example.dreeki.projectleerlingenapp.Interfaces.LockType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dreeki on 26/10/17.
 */

public class Profile {
    private String name;
    private String street;
    //number slaat op huisnummer
    private int number;
    private String city;
    private int postalCode;
    private List<Fingerprint> fingerprints;
    private Lock lock;
    private PersonalPicture personalPicture;

    public Profile(Lock lock, PersonalPicture personalPicture, String name, String street, int number, String city, int postalCode){
        this.lock = lock;
        this.personalPicture = personalPicture;
        fingerprints = new ArrayList<>();
        this.name = name;
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
    }

    public void schakelLockUit(){
        lock = null;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public List<Fingerprint> getFingerprints() {
        return fingerprints;
    }

    public Lock getLock() {
        return lock;
    }

    public PersonalPicture getPersonalPicture() {
        return personalPicture;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }
}
