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

        Problem problem1 = new Problem(0,"Niet afgestapt", R.drawable.bus,"Beste,\n" +
                "Ik ben vergeten afstappen. Ik ben op weg naar\n" +
                adres +
                "\n" +
                "Kan u mij helpen?\n" +
                "Bedankt.");
        Problem problem2 = new Problem(0,"Verkeerde halte", R.drawable.bus,"Beste,\n" +
                "Ik ben bij de verkeerde halte afgestapt. Ik ben op weg naar\n" +
                adres +
                "\n" +
                "Kan u mij helpen?\n" +
                "Bedankt.");

        Problem problem3 = new Problem(0,"Bus te laat", R.drawable.telaat,"Beste,\n" +
                "Ik moet de bus hebben naar "+ adres + "\n" + " maar hij is te laat, kan u mij tonen wanneer de volgende is?" +
                "\n" +
                "Bedankt.");

        problemen.add(problem1);
        problemen.add(problem2);
        problemen.add(problem3);
    }

    @Override
    public List<Problem> getProblemen() {
        return this.problemen;
    }
}
