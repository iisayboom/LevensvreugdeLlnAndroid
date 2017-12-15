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

        Problem problem1 = new Problem(0,"Weg kwijt", R.drawable.lekkeband,
                "Beste,\n" +
                "Ik ben op weg naar \n" +
                        adres +
                        "\nen ik ben de weg kwijt.\n" +
                        "Kan u mij helpen?\n" + "\n" +
                        "Bedankt.");
        Problem problem2 = new Problem(0,"Pijn", R.drawable.lekkeband,
                "Beste,\n" +
                "Ik heb mij pijn gedaan.\n" +
                "Kan u mij alstublieft helpen? \n" +
                "Ik ben onderweg naar " + adres + "\n" +
                "\n" +
                "Bedankt.");
        /*
        Problem probleem3 = new Problem(0,"Weg kwijt", R.drawable.lekkeband);
        Problem probleem4 = new Problem(0,"Pijn", R.drawable.lekkeband);
        Problem probleem5 = new Problem(0,"Weg kwijt", R.drawable.lekkeband);
        Problem probleem6 = new Problem(0,"Pijn", R.drawable.lekkeband);
        Problem probleem7 = new Problem(0,"Weg kwijt", R.drawable.lekkeband);
        Problem probleem8 = new Problem(0,"Pijn", R.drawable.lekkeband);
        */

        problemen.add(problem1);
        problemen.add(problem2);
        /*
        problemen.add(probleem3);
        problemen.add(probleem4);
        problemen.add(probleem5);
        problemen.add(probleem6);
        problemen.add(probleem7);
        problemen.add(probleem8);
        */
    }

    @Override
    public List<Problem> getProblemen() {
        return this.problemen;
    }
}
