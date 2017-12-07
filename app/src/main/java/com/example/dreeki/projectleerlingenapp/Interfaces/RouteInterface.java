package com.example.dreeki.projectleerlingenapp.Interfaces;

import android.widget.ImageView;

import com.example.dreeki.projectleerlingenapp.Models.Location;
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
    Location getLocation();
    void resetCurrentCheckPoint();
    Location getNextLocation();
    void goToLoginScreen();
    void toonProblemen();
}
