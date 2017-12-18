package com.example.dreeki.projectleerlingenapp.Interfaces;

import com.example.dreeki.projectleerlingenapp.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by nielsdebruyne on 11/12/2017.
 */

public interface BackendCalls {
    @GET("studentbymail/{email}/")
    Call<User> getUser(@Path("email") String email);

    @GET("studentbymail/{email}/{password}/")
    Call<User> getUser(@Path("email") String email, @Path("password") String password);

    @POST("addstudent")
    Call<User> addStudent(@Body User user);

    @POST("checkmentoremail")
    Call<String> checkMentorEmail(@Body String email);

    @POST("checkuserpassword/{email}/{password}")
    Call<String> checkUserPassword(@Path("password") String password, @Path("email") String email);

    @PATCH("updateUid/{email}/{firebaseUid}")
    Call<String> updateUid(@Path("firebaseUid") String firebaseUid, @Path("email") String email);

    @POST("checkuserdataversion/{email}/{dataversion}")
    Call<String> checkDataVersion(@Path("email") String email, @Path("dataverison") int dataversion);
}
