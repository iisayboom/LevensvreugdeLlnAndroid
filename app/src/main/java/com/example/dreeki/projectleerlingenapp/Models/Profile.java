package com.example.dreeki.projectleerlingenapp.Models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by dreeki on 26/10/17.
 */
@Entity
public class Profile {
    @Id
    public long id;
    private String name;
    public ToOne<Locatie> home;
    private String password;
    private int personalPicture;
    private String email;

    public Profile() {

    }

    public Profile(long id, String password, int personalPicture, String name, String email){
        this.id = id;
        this.password = password;
        this.personalPicture = personalPicture;
        this.name = name;
        this.email = email;
    }

    public Profile(long id, int personalPicture, String name, String email){
        this.id = id;
        this.personalPicture = personalPicture;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPersonalPicture() {
        return personalPicture;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPersonalPicture(int personalPicture) {
        this.personalPicture = personalPicture;
    }
}
