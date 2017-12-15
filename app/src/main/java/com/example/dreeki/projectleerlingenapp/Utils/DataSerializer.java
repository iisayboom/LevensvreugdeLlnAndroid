package com.example.dreeki.projectleerlingenapp.Utils;

import com.example.dreeki.projectleerlingenapp.Models.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nielsdebruyne on 13/12/2017.
 */

public class DataSerializer {
    public JSONObject serializeEmail(String email) throws JSONException {
        return new JSONObject().put("email", email);
    }

    public JSONObject serializeUser(User user) {
        JSONObject jsonUser = new JSONObject();
        JSONObject jsonHoofdmentor = new JSONObject();
        JSONObject jsonAdress = new JSONObject();

        try {
            jsonAdress.put("street", user.profile.getTarget().home.getTarget().getStreet());

            jsonAdress.put("city", user.profile.getTarget().home.getTarget().getCity());
            jsonAdress.put("number", user.profile.getTarget().home.getTarget().getNumber());
            jsonAdress.put("postalCode", user.profile.getTarget().home.getTarget().getPostalCode());
            jsonAdress.put("title", user.profile.getTarget().home.getTarget().getTitle());

            jsonHoofdmentor.put("email", user.mentor.getTarget().getEmail());


            jsonUser.put("name", user.profile.getTarget().getName());
            jsonUser.put("email", user.profile.getTarget().getEmail());
            jsonUser.put("adress", jsonAdress);
            jsonUser.put("hoofdMentor", jsonHoofdmentor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonUser;

    }

}
