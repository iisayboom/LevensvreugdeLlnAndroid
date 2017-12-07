package com.example.dreeki.projectleerlingenapp;

import android.app.Application;

import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.Utils.UserDeserialiser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.objectbox.BoxStore;

/**
 * Created by nielsdebruyne on 27/11/2017.
 */

public class App extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        //boxStore = MyObjectBox

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new UserDeserialiser());
        Gson gson = gsonBuilder.create();
    }
}
