package com.example.dreeki.projectleerlingenapp.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dreeki.projectleerlingenapp.Fragments.LoginFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.RouteFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.SettingsFragment;
import com.example.dreeki.projectleerlingenapp.Interfaces.SettingsInterface;
import com.example.dreeki.projectleerlingenapp.R;

public class SettingsActivity extends AppCompatActivity implements SettingsInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingsFragment f = new SettingsFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.commit();
    }

    @Override
    public void showLoginScreen() {
        LoginFragment f = new LoginFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }
}
