package com.example.dreeki.projectleerlingenapp.Utils;

import android.util.Log;

import com.example.dreeki.projectleerlingenapp.Models.Locatie;
import com.example.dreeki.projectleerlingenapp.Models.Mentor;
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
        final String userName = jsonUser.get("name").getAsString();

        final int userImage = R.drawable.worker;
        final String userEmail = jsonUser.get("email").getAsString();

        final int userDataVersion = jsonUser.get("dataVersion").getAsInt();

        final JsonObject jsonUserHoofdBegeleider = jsonUser.getAsJsonObject("hoofdMentor");
        final String jsonUserHoofdBegeleiderName = jsonUserHoofdBegeleider.get("username").getAsString();
        final String jsonUserHoofdBegeleiderPhone = jsonUserHoofdBegeleider.get("phone").getAsString();
        final String jsonUserHoofdBegeleiderEmail = jsonUserHoofdBegeleider.get("email").getAsString();

        final Mentor hoofdbegeleider = new Mentor(0,jsonUserHoofdBegeleiderName,jsonUserHoofdBegeleiderPhone,jsonUserHoofdBegeleiderEmail);

        final JsonObject jsonUserAdress = jsonUser.getAsJsonObject("adress");
        //final String adressId = jsonUserAdress.get("_id").getAsString();

        //final int adressImage = jsonUserAdress.get("_id").getAsInt();
        //final int adressImage = R.drawable.huis;
        final String adressStreet = jsonUserAdress.get("street").getAsString();
        final int adressPos = jsonUserAdress.get("postalCode").getAsInt();
        final String adressCity = jsonUserAdress.get("city").getAsString();
        final String adressNumber = jsonUserAdress.get("number").getAsString();
        final String adressTitle = jsonUserAdress.get("title").getAsString();

        final Locatie homeAdress = new Locatie(0, adressStreet,adressCity,adressNumber,adressPos,adressTitle);

        final List<Route> userRoutes = new ArrayList<>();
        final JsonArray jsonRoutes = jsonUser.getAsJsonArray("routes");
        for (JsonElement r:jsonRoutes) {
            final JsonObject rObj = r.getAsJsonObject();
            //final String routeId = rObj.get("id").getAsString();
            final JsonObject begin = rObj.getAsJsonObject("begin");

            //final String adressIdBegin = begin.get("begin").getAsString();

            //final int adressImageBegin = begin.get("_id").getAsInt();
            final String adressImageBegin = replaceImgurThings(begin.get("image").getAsString());
            final String adressStreetBegin = begin.get("street").getAsString();
            final int adressPosBegin = begin.get("postalCode").getAsInt();
            final String adressCityBegin = begin.get("city").getAsString();
            final String adressNumberBegin = begin.get("number").getAsString();
            final String adressTitleBegin = begin.get("title").getAsString();
            //final String adressVerplaatsManierBegin = begin.get("verplaatsWijze").getAsString();
            final String adressVerplaatsManierBegin = "Bus";
            final Locatie beginLocatie = new Locatie(0,adressImageBegin, adressStreetBegin, adressCityBegin, adressNumberBegin, adressPosBegin, adressTitleBegin, adressVerplaatsManierBegin);

            final JsonObject end = rObj.getAsJsonObject("end");
            //final String adressIdEnd = end.get("id").getAsString();

            //final int adressImageEnd = end.get("_id").getAsInt();
            final String adressImageEnd = replaceImgurThings(end.get("image").getAsString());
            final String adressStreetEnd = end.get("street").getAsString();
            final int adressPosEnd = end.get("postalCode").getAsInt();
            final String adressCityEnd = end.get("city").getAsString();
            final String adressNumberEnd = end.get("number").getAsString();
            final String adressTitleEnd = end.get("title").getAsString();
            final String adressClueEnd = end.get("aanwijzing").getAsString();

            final Locatie endLocatie = new Locatie(0,adressImageEnd, adressStreetEnd, adressCityEnd, adressNumberEnd, adressPosEnd, adressTitleEnd, adressClueEnd, "Te voet");

            final JsonArray jsonCheckpoints = rObj.getAsJsonArray("checkpoints");

            final List<Locatie> routeCheckpoints = new ArrayList<>();

            for (JsonElement e : jsonCheckpoints) {
                JsonObject checkpoint = e.getAsJsonObject();

                //final String adressIdcheckpoint = checkpoint.get("id").getAsString();

                //final int adressImagecheckpoint = checkpoint.get("_id").getAsInt();
                final String adressImagecheckpoint = replaceImgurThings(checkpoint.get("image").getAsString());
                final String adressStreetcheckpoint = checkpoint.get("street").getAsString();
                final int adressPoscheckpoint = checkpoint.get("postalCode").getAsInt();
                final String adressCitycheckpoint = checkpoint.get("city").getAsString();
                final String adressNumbercheckpoint = checkpoint.get("number").getAsString();
                final String adressTitlecheckpoint = checkpoint.get("title").getAsString();
                final String adressCluecheckpoint = checkpoint.get("aanwijzing").getAsString();
                final String adressVerplaatsManierCheckpoint = "Bus";
                routeCheckpoints.add(new Locatie(0,adressImagecheckpoint, adressStreetcheckpoint, adressCitycheckpoint, adressNumbercheckpoint, adressPoscheckpoint, adressTitlecheckpoint, adressCluecheckpoint, adressVerplaatsManierCheckpoint));
            }
            final JsonObject begeleider = rObj.getAsJsonObject("routeMentor");

            final String begeleiderNaam = begeleider.get("username").getAsString();
            final String begeleiderNummer = begeleider.get("phone").getAsString();

            Mentor mentor = new Mentor(0,begeleiderNaam, begeleiderNummer);

            Route route = new Route(0);
            Log.i("fff", beginLocatie.toString());
            route.begin.setTarget(beginLocatie);
            route.end.setTarget(endLocatie);
            route.checkpoints.addAll(routeCheckpoints);
            route.routeMentor.setTarget(mentor);
            userRoutes.add(route);
        }

        final Profile profile = new Profile(0, userImage, userName, userEmail);
        profile.home.setTarget(homeAdress);

        final User user = new User(0);
        user.profile.setTarget(profile);
        user.setDataVersion(userDataVersion);
        user.routes.addAll(userRoutes);
        user.mentor.setTarget(hoofdbegeleider);

        return user;
    }

    private String replaceImgurThings(String image) {
        String path = image.replace("https://i.imgur.com/","");

        //add l to ge small image from imgur
        String nameT = path.replaceAll(".jpg|.png","");
        String imageT = path.replaceAll(nameT,nameT + "s");

        return imageT;
    }

}
