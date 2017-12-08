package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Models.Location;
import com.example.dreeki.projectleerlingenapp.Models.Mentor;
import com.example.dreeki.projectleerlingenapp.Models.PersonalPicture;
import com.example.dreeki.projectleerlingenapp.Models.Profile;
import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.ArrayList;
import java.util.List;

public class EersteKeerOpenenLoginFragment extends Fragment implements View.OnClickListener {

    private String email;
    private String wachtwoord;
    private EditText edt1;
    private EditText edt2;
    private Profile profile;
    private User user2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_login, container, false);



        List<Mentor> mentors = new ArrayList<>();;
        profile = new Profile("test",new PersonalPicture(R.drawable.construction_icon),"Niels",new Location(R.drawable.laatste,"Arbeidstraat", "Aalst","14",9300,"Hogent","4","Stap richting campus hogeschool Gent."),"niels@hotmail.com");
        List<Route> routes = new ArrayList<>();
        List<Location> checkpoints = new ArrayList<>();

        Location l1 = new Location(R.drawable.eerste,"Onegem","Aalst", "5", 9300, "Station Aalst",  "1","Stap richting het station van Aalst.");
        Location l2 = new Location(R.drawable.tweede,"Stationstraat","Aalst", "17", 9300, "Esplanade Plein","2","Stap nu richting het Esplanade plein.");
        Location l3 = new Location(R.drawable.derde,"Grote Markt","Aalst", "76", 9300, "Grote Markt","3","Stap richting de grote markt.");

        mentors.add(new Mentor("Andreas De Witte", "04567483", "andreas.dewitte@hotmail.com"));
        checkpoints.add(l1);
        checkpoints.add(l2);
        checkpoints.add(l3);
        routes.add(new Route(profile.getHome(),profile.getHome(), checkpoints,"50"));

        //user2 = user2.getUser2(new User(mentors, profile, routes, true, "10"));


        Button b = v.findViewById(R.id.btnLogin);
        edt1 = v.findViewById(R.id.txtUsername);
        edt2 = v.findViewById(R.id.txtWachtwoord);




            b.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        email = edt1.getText().toString();
        wachtwoord = edt2.getText().toString();
        switch(v.getId()) {
            case R.id.btnLogin:
                //if((user.getProfile().getEmail().equals(email)) && (user.getProfile().getPassword().equals(wachtwoord))) {
                    ((EersteKeerOpenenInterface) getActivity()).goToLoginScreen();
                //}
                break;
        }
    }


}
