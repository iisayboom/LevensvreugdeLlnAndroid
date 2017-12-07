package com.example.dreeki.projectleerlingenapp.Models;

import io.objectbox.annotation.Entity;

/**
 * Created by dreeki on 26/10/17.
 */

public class Profile {
    private String name;
    private Location home;
    private String password;
    private PersonalPicture personalPicture;
    private String email;

    public Profile(String password, PersonalPicture personalPicture, String name, Location home, String email){
        this.password = password;
        this.personalPicture = personalPicture;
        this.name = name;
        this.home = home;
        this.email = email;
    }

    public void schakelLockUit(){
        password = null;
    }

    public String getName() {
        return name;
    }

    public PersonalPicture getPersonalPicture() {
        return personalPicture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getHome() {
        return home;
    }

    public void setHome(Location home) {
        this.home = home;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
