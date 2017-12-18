package com.example.dreeki.projectleerlingenapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.dreeki.projectleerlingenapp.Activities.MainActivity;
import com.example.dreeki.projectleerlingenapp.Activities.SettingsActivity;
import com.example.dreeki.projectleerlingenapp.Activities.SettingsListView;
import com.example.dreeki.projectleerlingenapp.Fragments.EersteKeerOpenenLoginFragment;
import com.example.dreeki.projectleerlingenapp.Fragments.EersteKeerOpenenStap4Fragment;
import com.example.dreeki.projectleerlingenapp.Interfaces.BackendCalls;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Models.Locatie;
import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.Models.MyObjectBox;
import com.example.dreeki.projectleerlingenapp.Utils.DataSerializer;
import com.example.dreeki.projectleerlingenapp.Utils.UserDeserialiser;
import com.example.dreeki.projectleerlingenapp.Utils.UserSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by nielsdebruyne on 27/11/2017.
 */

public class App extends Application {
    private BoxStore boxStore;
    private User user;
    private BackendCalls backendCalls;
    private Bitmap persoonIcoon;
    private EersteKeerOpenenLoginFragment fragment;
    private EersteKeerOpenenInterface mainActivity;
    private EersteKeerOpenenStap4Fragment eersteKeerOpenenStap4Fragment;
    private DataSerializer dataSerializer;
    private TextToSpeech tts;
    public static File _file;
    public static File _dir;
    public static Bitmap bitmap;
    private String fileName;
    private final String imageUrl = "https://i.imgur.com/";

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
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://levensvreugde-lln.herokuapp.com/API/").addConverterFactory(GsonConverterFactory.create(gson)).build();
            //Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/API/").addConverterFactory(GsonConverterFactory.create(gson)).build();
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

                        for(Route r :response.body().routes) {
                            new DownloadFile().execute(imageUrl + r.end.getTarget().getImage());
                            for(Locatie l : r.checkpoints) {
                                new DownloadFile().execute(imageUrl + l.getImage());
                            }
                        }

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
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://levensvreugde-lln.herokuapp.com/API/").addConverterFactory(GsonConverterFactory.create(gson)).build();
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
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://levensvreugde-lln.herokuapp.com/API/").addConverterFactory(GsonConverterFactory.create(gson)).build();
            backendCalls = retrofit.create(BackendCalls.class);


            Call<User> call = backendCalls.getUser(email);
            //userBox.removeAll();

            Log.i("test","gestart");

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.i("test","geslaagd");
                    saveUser(response.body());
                    for(Route r :response.body().routes) {
                        new DownloadFile().execute(imageUrl + r.end.getTarget().getImage());
                        for(Locatie l : r.checkpoints) {
                            new DownloadFile().execute(imageUrl + l.getImage());
                        }
                    }
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

    public void setUserFirebaseUid(String firebaseUid){

        user.setFirebaseUID(firebaseUid);
        patchUserFirebaseId();
    }

    private void patchUserFirebaseId() {

        if(isNetworkAvailable()) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://levensvreugde-lln.herokuapp.com/API/").addConverterFactory(ScalarsConverterFactory.create()).build();
            backendCalls = retrofit.create(BackendCalls.class);

            Call<String> call = backendCalls.updateUid(user.getFirebaseUID(),user.profile.getTarget().getEmail());
            Log.i("test","gestart");
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    mainActivity.continueToLoginScreen();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.i("test","gefaald");
                    t.printStackTrace();
                }
            });
        }else {
            //eersteKeerOpenenStap4Fragment.setFoutBoodschap("controleer of u wel internet heeft");
        }

    }

    public void checkUserPassword(String password, final FragmentActivity activity) {

        if(isNetworkAvailable()) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://levensvreugde-lln.herokuapp.com/API/").addConverterFactory(ScalarsConverterFactory.create()).build();
            backendCalls = retrofit.create(BackendCalls.class);


            Call<String> call = backendCalls.checkUserPassword(password,user.profile.getTarget().getEmail());
            Log.i("test","gestart");
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Intent intentSettings = new Intent(activity, SettingsActivity.class);
                        startActivity(intentSettings);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.i("test","gefaald");
                    t.printStackTrace();
                }
            });
        }else {
            //eersteKeerOpenenStap4Fragment.setFoutBoodschap("controleer of u wel internet heeft");
        }
    }

    public void setPersoonIcoon(Bitmap persoonIcoon) {
        //hier opslaan op device
        this.persoonIcoon = persoonIcoon;
    }

    public class DownloadFile extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... aurl) {
            Bitmap bitmap = null;
            FileOutputStream foStream;
            String url = (String) aurl[0];
            try {
                String path = url.replaceAll("http://i.imgur.com/|https://i.imgur.com/", "");
                String pathD = path.replaceAll(".png|.jpg", "");
                fileName = pathD;
                InputStream inputStream = new URL(url).openStream();   // Download Image from URL
                bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
                inputStream.close();
                foStream = getApplicationContext().openFileOutput(pathD, Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, foStream);
                foStream.flush();
                foStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);

        }
    }

    public class SaveFile extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... aurl) {
            FileOutputStream foStream;
            try {
                bitmap = getPersoonIcoon();
                foStream = getApplicationContext().openFileOutput("profielfoto", Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, foStream);
                foStream.flush();
                foStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);

        }
    }

    public void savePersonalPicture(Bitmap bitmap) {
        new SaveFile().execute("profielfoto" + bitmap);
    }
/*
    public void checkMentorEmail(String email){
        //post
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new UserDeserialiser());
        Gson gson = gsonBuilder.create();

        if(isNetworkAvailable()) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://levensvreugdelln.herokuapp.com/API/").addConverterFactory(GsonConverterFactory.create(gson)).build();
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

    public void checkDataVersion() {

        if(isNetworkAvailable()) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://levensvreugde-lln.herokuapp.com/API/").addConverterFactory(ScalarsConverterFactory.create()).build();
            backendCalls = retrofit.create(BackendCalls.class);

            Call<String> call = backendCalls.checkDataVersion(user.profile.getTarget().getEmail(), user.getDataVersion());

            Log.i("test","gestart");

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    getNewCreatedUserFromBackend(user.profile.getTarget().getEmail());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.i("test","gefaald");
                    t.printStackTrace();
                }
            });
        }else {
            //eersteKeerOpenenStap4Fragment.setFoutBoodschap("controleer of u wel internet heeft");
        }

    }

    public Bitmap getPersoonIcoon() {
        return persoonIcoon;
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