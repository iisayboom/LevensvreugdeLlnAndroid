package com.example.dreeki.projectleerlingenapp.Models;

import com.example.dreeki.projectleerlingenapp.Interfaces.TravelingState;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dreeki on 26/10/17.
 */

public class OnFoot implements TravelingState {
    private List<Problem> problemen;

    public OnFoot(String adres) {
        problemen = new ArrayList<>();

        Problem problem1 = new Problem(0,"Weg kwijt", R.drawable.weg_kwijt,
                "Beste,\n" +
                "Ik ben op weg naar \n" +
                        adres +
                        "\nen ik ben de weg kwijt.\n" +
                        "Kan u mij helpen?\n" +
                        "Bedankt.");
        Problem problem2 = new Problem(0,"Pijn", R.drawable.pijn,
                "Beste,\n" +
                "Ik heb mij pijn gedaan.\n" +
                "Kan u mij alstublieft helpen? \n" +
                "Ik ben onderweg naar " + adres + "\n" +
                "Bedankt.");

        problemen.add(problem1);
        problemen.add(problem2);
    }

    @Override
    public List<Problem> getProblemen() {
        return this.problemen;
    }
}
