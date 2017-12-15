package com.example.dreeki.projectleerlingenapp.Utils;

import com.example.dreeki.projectleerlingenapp.Models.User;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import java.lang.reflect.Type;

/**
 * Created by nielsdebruyne on 13/12/2017.
 */

public class UserSerializer implements JsonSerializer<User>{
    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonUser = new JsonObject();
        JsonObject jsonHoofdmentor = new JsonObject();
        JsonObject jsonAdress = new JsonObject();

        jsonAdress.addProperty("street", src.profile.getTarget().home.getTarget().getStreet());

        jsonAdress.addProperty("city", src.profile.getTarget().home.getTarget().getCity());
        jsonAdress.addProperty("number", src.profile.getTarget().home.getTarget().getNumber());
        jsonAdress.addProperty("postalCode", src.profile.getTarget().home.getTarget().getPostalCode());
        jsonAdress.addProperty("title", src.profile.getTarget().home.getTarget().getTitle());

        jsonHoofdmentor.addProperty("email", src.mentor.getTarget().getEmail());


        jsonUser.addProperty("name", src.profile.getTarget().getName());
        jsonUser.addProperty("email", src.profile.getTarget().getEmail());
        jsonUser.addProperty("password", src.profile.getTarget().getPassword());
        jsonUser.add("adress", jsonAdress);
        jsonUser.add("hoofdMentor", jsonHoofdmentor);



        return jsonUser;
    }
}
