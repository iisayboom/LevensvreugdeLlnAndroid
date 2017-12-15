package com.example.dreeki.projectleerlingenapp;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.example.dreeki.projectleerlingenapp.Fragments.EersteKeerOpenenLoginFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.EersteKeerOpenenStap4Fragment;
import com.example.dreeki.projectleerlingenapp.Interfaces.BackendCalls;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.Models.MyObjectBox;
import com.example.dreeki.projectleerlingenapp.Utils.DataSerializer;
import com.example.dreeki.projectleerlingenapp.Utils.UserDeserialiser;
import com.example.dreeki.projectleerlingenapp.Utils.UserSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nielsdebruyne on 27/11/2017.
 */

public class App extends Application {
    private BoxStore boxStore;
    private User user;
    private BackendCalls backendCalls;
    private EersteKeerOpenenLoginFragment fragment;
    private EersteKeerOpenenInterface mainActivity;
    private EersteKeerOpenenStap4Fragment eersteKeerOpenenStap4Fragment;
    private DataSerializer dataSerializer;
    private TextToSpeech tts;
    public static File _file;
    public static File _dir;
    public static Bitmap bitmap;

    @Override
    public void onCreate() {
        super.onCreate();

        dataSerializer = new DataSerializer();

        boxStore = MyObjectBox.builder().androidContext(App.this).build();

        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(this);
        }

    }

    public BoxStore getBoxStore() {
        return this.boxStore;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    //zeer onveilig, maar gebrek aan beter
    public void getUserFromBackend(String email, String password) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new UserDeserialiser());
        Gson gson = gsonBuilder.create();

        if(isNetworkAvailable()) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/API/").addConverterFactory(GsonConverterFactory.create(gson)).build();
            backendCalls = retrofit.create(BackendCalls.class);


            Call<User> call = backendCalls.getUser(email, password);
            //mainActivity.getUserBox().removeAll();

            Log.i("test","gestart");

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.i("test","geslaagd");

                    if (response.isSuccessful()) {
                        saveUser(response.body());
                        mainActivity.continueToLoginScreen();
                    }
                    else {
                        // error case
                        switch (response.code()) {
                            case 404:

                                break;
                            case 500:

                                break;
                            default:

                                break;
                        }
                        user = null;
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.i("test","gefaald");
                    t.printStackTrace();
                }
            });
        }

    }

    private void saveUser(User user) {
        Box<User> userBox = this.getBoxStore().boxFor(User.class);
        userBox.put(user);
        this.user = user;
    }



    public void sendUserToBackend(User u) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new UserSerializer());
        Gson gson = gsonBuilder.create();
        user = u;
        if(isNetworkAvailable()) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/API/").addConverterFactory(GsonConverterFactory.create(gson)).build();
            backendCalls = retrofit.create(BackendCalls.class);

            Call<User> call = backendCalls.addStudent(user);

            Log.i("test","gestart");

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.i("test","geslaagd");
                    if (response.isSuccessful()) {
                        saveUser(response.body());
                        mainActivity.continueToLoginScreen();
                    }
                    else {
                        // error case
                        switch (response.code()) {
                            case 404:
                                eersteKeerOpenenStap4Fragment.setFoutBoodschap("ier se doar se, tis kapot eh");
                                break;
                            case 500:
                                eersteKeerOpenenStap4Fragment.setFoutBoodschap("gelieve eerst de begeleider te registreren op de site");
                                break;
                            default:
                                eersteKeerOpenenStap4Fragment.setFoutBoodschap("ai ai ai groot probleem");
                                break;
                        }
                        user = null;
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.i("test","gefaald");
                    t.printStackTrace();
                    getNewCreatedUserFromBackend(user.profile.getTarget().getEmail());
                }
            });
        }else {
            eersteKeerOpenenStap4Fragment.setFoutBoodschap("controleer of u wel internet heeft");
        }
    }

    private void getNewCreatedUserFromBackend(String email) {
        user = null;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new UserDeserialiser());
        Gson gson = gsonBuilder.create();

        if(isNetworkAvailable()) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/API/").addConverterFactory(GsonConverterFactory.create(gson)).build();
            backendCalls = retrofit.create(BackendCalls.class);


            Call<User> call = backendCalls.getUser(email);
            //userBox.removeAll();

            Log.i("test","gestart");

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.i("test","geslaagd");
                    saveUser(response.body());
                    mainActivity.continueToLoginScreen();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.i("test","gefaald");
                    t.printStackTrace();
                    eersteKeerOpenenStap4Fragment.setFoutBoodschap("ai ai ai groot probleem");
                }
            });
        }else {
            eersteKeerOpenenStap4Fragment.setFoutBoodschap("controleer of u wel internet heeft");
        }

    }
/*
    public void checkMentorEmail(String email){
        //post
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new UserDeserialiser());
        Gson gson = gsonBuilder.create();

        if(isNetworkAvailable()) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/API/").addConverterFactory(GsonConverterFactory.create(gson)).build();
            backendCalls = retrofit.create(BackendCalls.class);


            Call<String> call = backendCalls.checkMentorEmail(email);
            //userBox.removeAll();

            Log.i("test","gestart");

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.i("test","geslaagd");
                    eersteKeerOpenenStap4Fragment.continueGoingToLogin();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.i("test","gefaald");
                    t.printStackTrace();
                    eersteKeerOpenenStap4Fragment.setFoutBoodschap("eeehj kapot");
                }
            });
        }
    }
*/
    public void changeFirebaseUIDInBackend() {
        //patch

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setFragment(EersteKeerOpenenLoginFragment fragment) {
        this.fragment = fragment;
    }

    public EersteKeerOpenenInterface getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(EersteKeerOpenenInterface mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setEersteKeerOpenenStap4Fragment(EersteKeerOpenenStap4Fragment eersteKeerOpenenStap4Fragment) {
        this.eersteKeerOpenenStap4Fragment = eersteKeerOpenenStap4Fragment;
    }
}