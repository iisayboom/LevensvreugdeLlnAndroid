package com.example.dreeki.projectleerlingenapp.Models;

import com.example.dreeki.projectleerlingenapp.Interfaces.TravellingState;

import java.util.List;

/**
 * Created by dreeki on 26/10/17.
 */

public class OnBike implements TravellingState {

    private List<String> problemen;

    public OnBike(List<String> problemen) {
        this.problemen = problemen;
    }

    public List<String> getProblemen() {
        return problemen;
    }
}
