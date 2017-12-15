package com.example.dreeki.projectleerlingenapp.Models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by dreeki on 26/10/17.
 */

@Entity
public class Mentor {
    @Id
    public long id;
    private String name;
    private String phoneNumber;
    private String email;

    public Mentor(){

    }

    public Mentor(long id, String name, String phoneNumber, String email){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
