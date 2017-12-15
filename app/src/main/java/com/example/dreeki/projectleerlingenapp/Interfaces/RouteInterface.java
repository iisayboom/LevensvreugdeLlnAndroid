package com.example.dreeki.projectleerlingenapp.Interfaces;

import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.Models.Locatie;
import com.example.dreeki.projectleerlingenapp.Models.Problem;
import com.example.dreeki.projectleerlingenapp.Models.Route;

/**
 * Created by nielsdebruyne on 04/11/2017.
 */

public interface RouteInterface {
    void toonManieren();
    void toonBenodigdheden();
    void volgendCheckpoint(String Titel,String subTitel, int src, String stap);
    void toonRouteKeuze();
    void toonRoutes();
    void setGekozenRoute(Route r);
    Route getRouteKeuze();
    void toonGeslaagdScherm();
    Locatie getLocation();
    void resetCurrentCheckPoint();
    Locatie getNextLocation();
    void goToLoginScreen();
    void toonProblemen();
    void toonHuidigCheckpoint();
    void probleemManier(Problem problem);
    User getUser();
    void toonProbleemTekst(Problem problem);
    Problem getGeselecteerdProbleem();
}
