package com.example.dreeki.projectleerlingenapp.Models;

import com.example.dreeki.projectleerlingenapp.Interfaces.TravellingState;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by dreeki on 26/10/17.
 */

public class User {
    private String id;
    private List<Route> routes;
    private Profile profile;
    private List<Mentor> mentors;
    private TravellingState travellingState;
    private boolean kanGpsVolgen;
    private static User user;

    private User(List<Mentor> mentors, Profile profile, List<Route> routes, boolean kanGpsVolgen, String id){
        this.routes = routes;
        this.mentors = mentors;
        this.kanGpsVolgen = kanGpsVolgen;
        travellingState = new NotTraveling();
        this.profile = profile;
        this.id = id;
    }

    public static User get(List<Mentor> mentors, Profile profile, List<Route> routes, boolean kanGpsVolgen, String id) {
        if (user == null) {
            user = new User(mentors, profile, routes,kanGpsVolgen,id);
        }

        return get();
    }

    public static User get() {
        if(user == null) {
            user = new User(new ArrayList<Mentor>(),null, new ArrayList<Route>(), false,null);
        }

        return user;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public Profile getProfile() {
        return profile;
    }

    public TravellingState getTravellingState() {
        return travellingState;
    }

    public void setTravellingState(TravellingState travellingState) {
        this.travellingState = travellingState;
    }
}
