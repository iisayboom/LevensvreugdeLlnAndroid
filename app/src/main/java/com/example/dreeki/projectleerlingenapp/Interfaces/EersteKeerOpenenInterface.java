package com.example.dreeki.projectleerlingenapp.Interfaces;

import android.view.View;

import com.example.dreeki.projectleerlingenapp.Models.Mentor;
import com.example.dreeki.projectleerlingenapp.Models.PersonalPicture;

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
    void goToFinalStep();
    void goToLoginScreen();
    void voltooiInstellingen();
    String getName();
    PersonalPicture getPicture();
    String getStreet();
    String getCity();
    int getNumber();
    Mentor getMentor();
    int getPostalCode();
    List<Tool> getToolList();
    void setName(String name);
    void setStreet(String street);
    void setCity(String city);
    void setNumber(int number);
    void setPicture(PersonalPicture pic);
    void setMentor(Mentor mentor);
    void setPostalCode(int postalCode);
    void setToolList(List<Tool> toolList);
    void showTestFrame();
}
