package com.example.dreeki.projectleerlingenapp.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Fragments.BenodigdheidFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.BottomNavigationFragment1;
import com.example.dreeki.projectleerlingenapp.Fragments.BottomNavigationFragment2;
import com.example.dreeki.projectleerlingenapp.Fragments.BottomNavigationFragment3;
import com.example.dreeki.projectleerlingenapp.R;

public class bottomNavigation extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);


    }

}
