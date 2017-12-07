package com.example.dreeki.projectleerlingenapp.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreeki.projectleerlingenapp.Fragments.BenodigdheidFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.CheckpointFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.LoginFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.ProblemFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.RouteFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.RouteGeslaagdFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.RouteKeuzeFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.VerplaatsManier;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.Models.Location;
import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.R;

public class RouteActivity extends AppCompatActivity implements RouteInterface{

    private Route gekozenRoute;
    private Location checkpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        RouteFragment f = new RouteFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.commit();
    }


    @Override
    public void toonManieren() {
        VerplaatsManier f = new VerplaatsManier();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void toonBenodigdheden() {
        BenodigdheidFragment f = new BenodigdheidFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void volgendCheckpoint(String t,String st, int src, String stap) {

        Bundle b = new Bundle();
        b.putString("titel", t);
        b.putString("subtitel", st);
        b.putInt("img", src);
        b.putString("stap", stap);

        CheckpointFragment f = new CheckpointFragment();
        f.setArguments(b);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void toonRouteKeuze() {
        RouteKeuzeFragment f = new RouteKeuzeFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void toonRoutes() {
        RouteFragment f = new RouteFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public Location getLocation(){
        return this.checkpoint;
    }


    @Override
    public void setGekozenRoute(Route r) {
        this.checkpoint = null;
        this.gekozenRoute = r;
    }

    @Override
    public Route getRouteKeuze() {
        return gekozenRoute;
    }

    @Override
    public void toonGeslaagdScherm() {
        RouteGeslaagdFragment f = new RouteGeslaagdFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void resetCurrentCheckPoint() {
        checkpoint = null;
    }

    @Override
    public Location getNextLocation() {
        if (checkpoint == null){
            if(gekozenRoute.getCheckpoints().size() == 0){
                checkpoint = gekozenRoute.getEnd();
            }else {
                checkpoint = gekozenRoute.getCheckpoints().get(0);
            }
        }else{
            if(checkpoint == gekozenRoute.getEnd()){
                checkpoint = null;
            }else{
                if(checkpoint == gekozenRoute.getCheckpoints().get(gekozenRoute.getCheckpoints().size()-1)){
                    checkpoint = gekozenRoute.getEnd();
                }else {
                    for(int i = 0; i < gekozenRoute.getCheckpoints().size(); i++){
                        if(checkpoint == gekozenRoute.getCheckpoints().get(i)){
                            checkpoint = gekozenRoute.getCheckpoints().get(i +1);
                            break;
                        }
                    }
                }
            }
        }

        return checkpoint;
    }

    @Override
    public void goToLoginScreen() {
        LoginFragment f = new LoginFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void toonProblemen() {
        ProblemFragment f = new ProblemFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }
}
