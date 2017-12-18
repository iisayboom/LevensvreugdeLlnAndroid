package com.example.dreeki.projectleerlingenapp.Interfaces;

import com.example.dreeki.projectleerlingenapp.Fragments.EersteKeerOpenenLoginFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.EersteKeerOpenenStap4Fragment;
import com.example.dreeki.projectleerlingenapp.Models.Mentor;
import com.example.dreeki.projectleerlingenapp.Models.User;

import java.util.List;

import io.objectbox.Box;

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
    Box<User> getUserBox();
    void setMentorEmail(String mentorEmail);
    String getMentorEmail();
    void goToFinalStep();
    void goToLoginScreen();
    String getName();
    int getPicture();
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
    void setPicture(int pic);
    void setMentor(List<Mentor> mentors);
    void setPostalCode(int postalCode);
    void setEmail(String email);
    void setPassword(String password);
    void goToProblems();
    void goToAlreadyRegistered();
    //boolean isNetworkAvailable();
    void getUserFromBackend(String email, String password);
    void geefEersteKeerOpenenLoginFragmentAanApp(EersteKeerOpenenLoginFragment f);
    void geefEersteKeerOpenenStapFragmentAanApp(EersteKeerOpenenStap4Fragment f);
    void continueToLoginScreen();
    void showFirstScreen();
}
