package com.example.dreeki.projectleerlingenapp.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.dreeki.projectleerlingenapp.App;
import com.example.dreeki.projectleerlingenapp.Fragments.*;
import com.example.dreeki.projectleerlingenapp.Fragments.LoginFragment;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.Models.Locatie;
import com.example.dreeki.projectleerlingenapp.Models.Mentor;
import com.example.dreeki.projectleerlingenapp.Models.Profile;
import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.R;
import com.example.dreeki.projectleerlingenapp.Services.TrackingService;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class MainActivity extends AppCompatActivity implements EersteKeerOpenenInterface {
    SharedPreferences prefs = null;
    private String name;
    private int picture;
    private String street;
    private String number;
    private String city;
    private String email;
    private String password;
    private String mentorEmail;
    private List<Mentor> mentors;
    private List<Route> routes;
    private int postalCode;
    private User user;
    private App app;
    private Box<User> userBox;
    private List<Locatie> checkpoints;
    private final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picture = -1;
        mentors = new ArrayList<>();
        routes = new ArrayList<>();
        checkpoints = new ArrayList<>();

        app = ((App)getApplication());
        app.setMainActivity(this);
        userBox = app.getBoxStore().boxFor(User.class);
        //userBox.removeAll();
        try{
            user = userBox.getAll().get(0);
            app.setUser(user);
        } catch(Exception e){
            user = null;
        }

        if(user == null){
            EersteKeerOpenenStap0Fragment f = new EersteKeerOpenenStap0Fragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, f);
            ft.addToBackStack(null);
            ft.commit();
        }else {
            app.checkDataVersion();
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
    public String getMentorEmail() {
        return mentorEmail;
    }

    @Override
    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
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
    public void getUserFromBackend(String email, String password) {
        ((App)getApplication()).getUserFromBackend(email, password);
    }


    //firebaseStuff
    @Override
    public void goToLoginScreen() {

        if(app.getUser() == null) {
            User u = new User(0);
            Profile p = new Profile(0, this.password, this.picture, this.name, this.email);
            Locatie homeLocatie = new Locatie(6, "", street, city, number, postalCode, "Home", "Nog een aanwijzing","");
            Mentor mentor = new Mentor(0);
            mentor.setEmail(mentorEmail);
            u.profile.setTarget(p);
            u.profile.getTarget().home.setTarget(homeLocatie);
            u.mentor.setTarget(mentor);
            app.sendUserToBackend(u);
        }
    }

    @Override
    public Box<User> getUserBox() {
        return userBox;
    }

    @Override
    public void continueToLoginScreen(){
        checkPermissions();

        LoginFragment f = new LoginFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void showFirstScreen() {
        EersteKeerOpenenStap0Fragment f = new EersteKeerOpenenStap0Fragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public User getUser() {
        this.user = app.getUser();
        return this.user;
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
    public int getPicture() {
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
    public void setPicture(int pic) {
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

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void geefEersteKeerOpenenLoginFragmentAanApp(EersteKeerOpenenLoginFragment f) {
        ((App)getApplication()).setFragment(f);
    }

    @Override
    public void geefEersteKeerOpenenStapFragmentAanApp(EersteKeerOpenenStap4Fragment f){
        ((App)getApplication()).setEersteKeerOpenenStap4Fragment(f);
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        } else {
            startTracking();
        }
    }

    private void startTracking() {
        Intent intent = new Intent(this, TrackingService.class);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (grantResults[requestCode]) {

            case 0: {
                // access granted
                startTracking();
                break;
            }

            case -1: {
                // acces denied
                //TODO: show alert
                break;
            }
        }
    }

}
