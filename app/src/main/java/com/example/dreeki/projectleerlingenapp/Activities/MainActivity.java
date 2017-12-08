package com.example.dreeki.projectleerlingenapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.dreeki.projectleerlingenapp.App;
import com.example.dreeki.projectleerlingenapp.Fragments.*;
import com.example.dreeki.projectleerlingenapp.Fragments.LoginFragment;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Models.Location;
import com.example.dreeki.projectleerlingenapp.Models.Mentor;
import com.example.dreeki.projectleerlingenapp.Models.PersonalPicture;
import com.example.dreeki.projectleerlingenapp.Models.Profile;
import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class MainActivity extends AppCompatActivity implements EersteKeerOpenenInterface {
    SharedPreferences prefs = null;
    private String name;
    private PersonalPicture picture;
    private String street;
    private String number;
    private String city;
    private String email;
    private String password;
    private List<Mentor> mentors;
    private List<Route> routes;
    private int postalCode;
    private User user;
    private App app;
    private Box<User> userBox;
    private List<Location> checkpoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picture = new PersonalPicture();
        mentors = new ArrayList<>();
        routes = new ArrayList<>();
        checkpoints = new ArrayList<>();
        //mentors.add(new Mentor("null","null","null"));
        //userBox

        prefs = getSharedPreferences("com.example.dreeki.projectleerlingenapp", MODE_PRIVATE);
        prefs.edit().clear().commit();
        if(prefs.getBoolean("firstrun", true)){
            EersteKeerOpenenStap0Fragment f = new EersteKeerOpenenStap0Fragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, f);
            ft.addToBackStack(null);
            ft.commit();
        }else{
            LoginFragment f = new LoginFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, f);
            ft.addToBackStack(null);
            ft.commit();
        }

    }

    @Override
    public void goToStep1(){
        EersteKeerOpenenStap1Fragment f = new EersteKeerOpenenStap1Fragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToStep2() {
        EersteKeerOpenenStap2Fragment f = new EersteKeerOpenenStap2Fragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToStep3() {
        EersteKeerOpenenStap3Fragment f = new EersteKeerOpenenStap3Fragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToStep4() {
        EersteKeerOpenenStap4Fragment f = new EersteKeerOpenenStap4Fragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToStep5() {
        EersteKeerOpenenStap5Fragment f = new EersteKeerOpenenStap5Fragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToStep7() {
        EersteKeerOpenenStap7Fragment f = new EersteKeerOpenenStap7Fragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToStep8() {
        EersteKeerOpenenStap8Fragment f = new EersteKeerOpenenStap8Fragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToFinalStep() {
        EersteKeerOpenenStap6Fragment f = new EersteKeerOpenenStap6Fragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToProblems() {
        ProblemFragment f = new ProblemFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToAlreadyRegistered() {
        EersteKeerOpenenLoginFragment f = new EersteKeerOpenenLoginFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToLoginScreen() {
        Location homeLocation = new Location(R.drawable.voet, street, city, number, postalCode, "Home", "1", "Nog een aanwijzing");
        Profile profile = new Profile(password, picture, name, homeLocation, email);
        //prentje aanpassen naar huisje

        user = User.get(mentors, profile, routes, true,"8");

        prefs.edit().putBoolean("firstrun", false).commit();

        LoginFragment f = new LoginFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    private FragmentManager fm;
    private FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Titel1");
                    BottomNavigationFragment1 f1 = new BottomNavigationFragment1();
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fram, f1);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                case R.id.navigation_dashboard:
                    setTitle("Titel2");
                    BottomNavigationFragment2 f2 = new BottomNavigationFragment2();
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fram, f2);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                case R.id.navigation_notifications:
                    setTitle("Titel3");
                    BottomNavigationFragment3 f3 = new BottomNavigationFragment3();
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fram, f3);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public PersonalPicture getPicture() {
        return this.picture;
    }

    @Override
    public String getStreet() {
        return this.street;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public List<Mentor> getMentors() {
        return this.mentors;
    }

    @Override
    public int getPostalCode() {
        return this.postalCode;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void setPicture(PersonalPicture pic) {
        this.picture = pic;
    }

    @Override
    public void setMentor(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    @Override
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
