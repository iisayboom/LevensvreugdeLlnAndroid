package com.example.dreeki.projectleerlingenapp.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreeki.projectleerlingenapp.Fragments.BenodigdheidFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.RouteFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.VerplaatsManier;
import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.R;

public class RouteActivity extends AppCompatActivity implements RouteInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        RouteFragment f = new RouteFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, f);
        ft.addToBackStack(null);
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
}
