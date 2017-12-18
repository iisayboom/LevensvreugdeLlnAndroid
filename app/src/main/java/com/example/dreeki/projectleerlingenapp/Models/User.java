package com.example.dreeki.projectleerlingenapp.Models;

import com.example.dreeki.projectleerlingenapp.Interfaces.TravelingState;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

/**
 * Created by dreeki on 26/10/17.
 */

@Entity
public class User {
    @Id
    public long id;
    public ToMany<Route> routes;
    public ToOne<Profile> profile;
    public ToOne<Mentor> mentor;
    @Transient
    public TravelingState travelingState;
    private String firebaseUID;
    private int dataVersion;


    public User() {

    }

    public User(long id){
        this.id = id;
    }

    public TravelingState getTravelingState() {
        return travelingState;
    }

    public void setTravelingState(TravelingState travelingState) {
        this.travelingState = travelingState;
    }

    public long getId() {
        return id;
    }

    public String getFirebaseUID() {
        return firebaseUID;
    }

    public void setFirebaseUID(String firebaseUID) {
        this.firebaseUID = firebaseUID;
    }

    public void setDataVersion(int dataVersion) {
        this.dataVersion = dataVersion;
    }

    public int getDataVersion() {
        return dataVersion;
    }
}
