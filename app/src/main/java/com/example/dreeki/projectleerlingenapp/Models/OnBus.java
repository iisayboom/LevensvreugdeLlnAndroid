package com.example.dreeki.projectleerlingenapp.Models;

import com.example.dreeki.projectleerlingenapp.Interfaces.TravelingState;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dreeki on 26/10/17.
 */

public class OnBus implements TravelingState {
    private List<Problem> problemen;

    public OnBus(String adres) {
        problemen = new ArrayList<>();

        Problem problem1 = new Problem(0,"Niet afgestap", R.drawable.lekkeband,"Beste,\n" +
                "Ik ben vergeten afstappen. Ik ben op weg naar\n" +
                adres +
                "\n" +
                "Kan u mij helpen?\n" +
                "\n" +
                "Bedankt.");
        Problem problem2 = new Problem(0,"Verkeerde halte", R.drawable.lekkeband,"Beste,\n" +
                "Ik ben bij de verkeerde halte afgestapt. Ik ben op weg naar\n" +
                adres +
                "\n" +
                "Kan u mij helpen?\n" +
                "\n" +
                "Bedankt.");

        problemen.add(problem1);
        problemen.add(problem2);
    }

    @Override
    public List<Problem> getProblemen() {
        return this.problemen;
    }
}
