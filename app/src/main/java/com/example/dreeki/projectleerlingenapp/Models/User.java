package com.example.dreeki.projectleerlingenapp.Models;

import com.example.dreeki.projectleerlingenapp.Interfaces.Tool;
import com.example.dreeki.projectleerlingenapp.Interfaces.TravellingState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dreeki on 26/10/17.
 */

public class User {
    private List<Destination> destinations;
    private Profile profile;
    private List<Mentor> mentors;
    private List<Tool> tools;
    private TravellingState travellingState;

    public User(Mentor mentor, Profile profile, Destination destination, List<Tool> tools){
        destinations = new ArrayList<>();
        mentors = new ArrayList<>();
        tools = new ArrayList<>();
        travellingState = new NotTraveling();
        destinations.add(destination);
        this.profile = profile;
        this.tools = tools;
    }
}
