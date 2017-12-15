package com.example.dreeki.projectleerlingenapp.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreeki.projectleerlingenapp.App;
import com.example.dreeki.projectleerlingenapp.Fragments.InstellingenFragment;
import com.example.dreeki.projectleerlingenapp.Interfaces.SettingsInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.R;

public class SettingsActivity extends AppCompatActivity implements SettingsInterface {

    private User user;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = ((App)getApplication());
        user = app.getUser();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        InstellingenFragment f = new InstellingenFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.commit();
    }

    @Override
    public void showLoginScreen() {
        finish();
    }

    @Override
    public User getUser() {
        return user;
    }
}
