package com.example.dreeki.projectleerlingenapp.Interfaces;

import android.view.View;

import com.example.dreeki.projectleerlingenapp.Models.Mentor;
import com.example.dreeki.projectleerlingenapp.Models.PersonalPicture;
import com.example.dreeki.projectleerlingenapp.Models.User;

import java.util.List;

/**
 * Created by dreeki on 31/10/17.
 */

public interface EersteKeerOpenenInterface {
    void goToStep1();
    void goToStep2();
    void goToStep3();
    void goToStep4();
    void goToStep5();
    void goToStep7();
    void goToStep8();
    void goToFinalStep();
    void goToLoginScreen();
    String getName();
    PersonalPicture getPicture();
    String getStreet();
    String getCity();
    String getNumber();
    List<Mentor> getMentors();
    int getPostalCode();
    User getUser();
    String getEmail();
    String getPassword();
    void setName(String name);
    void setStreet(String street);
    void setCity(String city);
    void setNumber(String number);
    void setPicture(PersonalPicture pic);
    void setMentor(List<Mentor> mentors);
    void setPostalCode(int postalCode);
    void setEmail(String email);
    void setPassword(String password);
    void goToProblems();
}
