package com.example.dreeki.projectleerlingenapp.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreeki.projectleerlingenapp.App;
import com.example.dreeki.projectleerlingenapp.Fragments.BenodigdheidFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.CheckpointFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.ProbleemManier;
import com.example.dreeki.projectleerlingenapp.Fragments.ProbleemTekst;
import com.example.dreeki.projectleerlingenapp.Fragments.ProblemFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.RouteFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.RouteGeslaagdFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.RouteKeuzeFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.VerplaatsManier;
import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.Models.Locatie;
import com.example.dreeki.projectleerlingenapp.Models.Problem;
import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.R;

import io.objectbox.Box;

public class RouteActivity extends AppCompatActivity implements RouteInterface{

    private Route gekozenRoute;
    private Locatie checkpoint;
    private User user;
    private CheckpointFragment checkpointFragment;
    private App app;
    private Box<User> userBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = ((App)getApplication());
        user = app.getUser();

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

        checkpointFragment = new CheckpointFragment();
        checkpointFragment.setArguments(b);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, checkpointFragment);
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
    public Locatie getLocation(){
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
    public Locatie getNextLocation() {
        if (checkpoint == null){
            if(gekozenRoute.checkpoints.size() == 0){
                checkpoint = gekozenRoute.end.getTarget();
            }else {
                checkpoint = gekozenRoute.checkpoints.get(0);
            }
        }else{
            if(checkpoint == gekozenRoute.end.getTarget()){
                checkpoint = null;
            }else{
                if(checkpoint == gekozenRoute.checkpoints.get(gekozenRoute.checkpoints.size()-1)){
                    checkpoint = gekozenRoute.end.getTarget();
                }else {
                    for(int i = 0; i < gekozenRoute.checkpoints.size(); i++){
                        if(checkpoint == gekozenRoute.checkpoints.get(i)){
                            checkpoint = gekozenRoute.checkpoints.get(i +1);
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
        /*LoginFragment f = new LoginFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();*/
        this.finish();
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

    @Override
    public void toonHuidigCheckpoint() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, checkpointFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void probleemManier(Problem problem) {
        ProbleemManier f = new ProbleemManier();
        f.setProblem(problem);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    public User getUser() {
        return user;
    }

    @Override
    public void toonProbleemTekst(Problem problem) {
        ProbleemTekst f = new ProbleemTekst();
        f.setProblem(problem);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public Problem getGeselecteerdProbleem() {
        return null;
    }
}
