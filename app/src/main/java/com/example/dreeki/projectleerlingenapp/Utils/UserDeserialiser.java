package com.example.dreeki.projectleerlingenapp.Utils;

import com.example.dreeki.projectleerlingenapp.Models.Location;
import com.example.dreeki.projectleerlingenapp.Models.Mentor;
import com.example.dreeki.projectleerlingenapp.Models.PersonalPicture;
import com.example.dreeki.projectleerlingenapp.Models.Profile;
import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nielsdebruyne on 27/11/2017.
 */

public class UserDeserialiser implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonUser = json.getAsJsonObject();
        final String userId = jsonUser.get("_id").getAsString();
        final String userName = jsonUser.get("name").getAsString();
        final int userImage = R.drawable.construction_icon;
        final String userEmail = jsonUser.get("email").getAsString();
        final String userPassword = jsonUser.get("password").getAsString();

        final JsonObject jsonUserAdress = jsonUser.getAsJsonObject("adress");
        final String adressId = jsonUserAdress.get("_id").getAsString();

        //final int adressImage = jsonUserAdress.get("_id").getAsInt();
        final int adressImage = R.drawable.huis;
        final String adressStreet = jsonUserAdress.get("_id").getAsString();
        final int adressPos = jsonUserAdress.get("postalCode").getAsInt();
        final String adressCity = jsonUserAdress.get("_id").getAsString();
        final String adressNumber = jsonUserAdress.get("_id").getAsString();
        final String adressTitle = jsonUserAdress.get("_id").getAsString();
        final String adressClue = jsonUserAdress.get("clue").getAsString();

        final Location homeAdress = new Location(adressImage, adressStreet,adressCity,adressNumber,adressPos,adressTitle, adressId,adressClue);
        final List<Route> userRoutes = new ArrayList<>();
        final JsonArray jsonRoutes = jsonUser.getAsJsonArray("routes");
        for (JsonElement r:jsonRoutes) {
            final JsonObject rObj = r.getAsJsonObject();
            final String routeId = rObj.get("_id").getAsString();
            final JsonObject begin = rObj.getAsJsonObject("begin");

            final String adressIdBegin = begin.get("_id").getAsString();

            //final int adressImageBegin = begin.get("_id").getAsInt();
            final int adressImageBegin = R.drawable.huis;
            final String adressStreetBegin = begin.get("_id").getAsString();
            final int adressPosBegin = begin.get("postalCode").getAsInt();
            final String adressCityBegin = begin.get("_id").getAsString();
            final String adressNumberBegin = begin.get("_id").getAsString();
            final String adressTitleBegin = begin.get("_id").getAsString();
            final String adressClueBegin = begin.get("clue").getAsString();

            final Location beginLocation = new Location(adressImageBegin, adressStreetBegin, adressCityBegin, adressNumberBegin, adressPosBegin, adressTitleBegin, adressIdBegin, adressClueBegin);

            final JsonObject end = rObj.getAsJsonObject("end");
            final String adressIdEnd = end.get("_id").getAsString();

            //final int adressImageEnd = end.get("_id").getAsInt();
            final int adressImageEnd = R.drawable.huis;
            final String adressStreetEnd = end.get("_id").getAsString();
            final int adressPosEnd = end.get("postalCode").getAsInt();
            final String adressCityEnd = end.get("_id").getAsString();
            final String adressNumberEnd = end.get("_id").getAsString();
            final String adressTitleEnd = end.get("_id").getAsString();
            final String adressClueEnd = end.get("clue").getAsString();

            final Location endLocation = new Location(adressImageEnd, adressStreetEnd, adressCityEnd, adressNumberEnd, adressPosEnd, adressTitleEnd, adressIdEnd, adressClueEnd);

            final JsonArray jsonCheckpoints = rObj.getAsJsonArray("checkpoints");

            final List<Location> routeCheckpoints = new ArrayList<>();

            for (JsonElement e : jsonCheckpoints) {
                JsonObject checkpoint = e.getAsJsonObject();

                final String adressIdcheckpoint = checkpoint.get("_id").getAsString();

                //final int adressImagecheckpoint = checkpoint.get("_id").getAsInt();
                final int adressImagecheckpoint = R.drawable.huis;
                final String adressStreetcheckpoint = checkpoint.get("_id").getAsString();
                final int adressPoscheckpoint = checkpoint.get("postalCode").getAsInt();
                final String adressCitycheckpoint = checkpoint.get("_id").getAsString();
                final String adressNumbercheckpoint = checkpoint.get("_id").getAsString();
                final String adressTitlecheckpoint = checkpoint.get("_id").getAsString();
                final String adressCluecheckpoint = checkpoint.get("clue").getAsString();

                routeCheckpoints.add(new Location(adressImagecheckpoint, adressStreetcheckpoint, adressCitycheckpoint, adressNumbercheckpoint, adressPoscheckpoint, adressTitlecheckpoint, adressIdcheckpoint, adressCluecheckpoint));

            }
            userRoutes.add(new Route(beginLocation, endLocation, routeCheckpoints, routeId));
        }

        final Profile profile = new Profile(userPassword,new PersonalPicture(userImage), userName, homeAdress, userEmail);

        //return new User(null, profile, userRoutes, true, userId);
        return null;
    }
}
