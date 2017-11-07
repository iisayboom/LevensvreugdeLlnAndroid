package com.example.dreeki.projectleerlingenapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.example.dreeki.projectleerlingenapp.Fragments.*;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Interfaces.Tool;
import com.example.dreeki.projectleerlingenapp.Models.Destination;
import com.example.dreeki.projectleerlingenapp.Models.Mentor;
import com.example.dreeki.projectleerlingenapp.Models.PersonalPicture;
import com.example.dreeki.projectleerlingenapp.Models.Profile;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.List;

public class EersteKeerOpenenActivity extends AppCompatActivity implements EersteKeerOpenenInterface{
    private String name;
    private PersonalPicture picture;
    private String street;
    private int number;
    private String city;
    private Mentor mentor;
    private int postalCode;
    private List<Tool> toolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eerste_keer_openen);

        EersteKeerOpenenStap0Fragment f = new EersteKeerOpenenStap0Fragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
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
    public void goToFinalStep() {
        EersteKeerOpenenStap6Fragment f = new EersteKeerOpenenStap6Fragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f);
        ft.addToBackStack(null);
        ft.commit();
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
    public void voltooiInstellingen() {
        Profile profile = new Profile(null, picture, name, street, number, city, postalCode);
        Destination homeDestination = new Destination(null, street, city, number, postalCode);
        User user = new User(mentor, profile, homeDestination, toolList);

        Intent intent = new Intent(this, StartupActivity.class);
        startActivity(intent);
    }

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
    public int getNumber() {
        return this.number;
    }

    @Override
    public Mentor getMentor() {
        return this.mentor;
    }

    @Override
    public int getPostalCode() {
        return this.postalCode;
    }

    @Override
    public List<Tool> getToolList() {
        return this.toolList;
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
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void setPicture(PersonalPicture pic) {
        this.picture = pic;
    }

    @Override
    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    @Override
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public void setToolList(List<Tool> toolList) {
        this.toolList = toolList;
    }
}
