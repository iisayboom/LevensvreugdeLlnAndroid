package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.Models.Profile;
import com.example.dreeki.projectleerlingenapp.R;

public class EersteKeerOpenenLoginFragment extends Fragment implements View.OnClickListener {

    private String email;
    private String wachtwoord;
    private EditText edt1;
    private EditText edt2;
    private Profile profile;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_login, container, false);

        Button b = v.findViewById(R.id.btnLogin);
        b.setOnClickListener(this);

        edt1 = v.findViewById(R.id.txtUsername);
        edt2 = v.findViewById(R.id.txtWachtwoord);

        /*
        User user = new User();

        user.mentors.add(new Mentor(0,"Andreas De Witte", "04567483", "andreas.dewitte@hotmail.com"));
        user.profile.setTarget(new Profile(0,"test",R.drawable.construction_icon,"Niels","niels@hotmail.com"));


        //dummy data
        List<Locatie> checkpoints = new ArrayList<>();

        Locatie l1 = new Locatie(0,R.drawable.eerste,"Onegem","Aalst", "5", 9300, "Station Aalst","Stap richting het station van Aalst.");
        Locatie l2 = new Locatie(0,R.drawable.tweede,"Stationstraat","Aalst", "17", 9300, "Esplanade Plein","Stap nu richting het Esplanade plein.");
        Locatie l3 = new Locatie(0,R.drawable.derde,"Grote Markt","Aalst", "76", 9300, "Grote Markt","Stap richting de grote markt.");

        checkpoints.add(l1);
        checkpoints.add(l2);
        checkpoints.add(l3);

        Route route = new Route(0);
        route.begin.setTarget(l1);
        route.end.setTarget(l2);
        user.routes.add(route);
        user.routes.get(0).checkpoints.addAll(checkpoints);
        */

        return v;
    }

    @Override
    public void onClick(View v) {
        email = edt1.getText().toString();

        wachtwoord = edt2.getText().toString();

        switch(v.getId()) {
            case R.id.btnLogin:
                    ((EersteKeerOpenenInterface)getActivity()).geefEersteKeerOpenenLoginFragmentAanApp(this);
                    ((EersteKeerOpenenInterface)getActivity()).getUserFromBackend(email, wachtwoord);
                    // via activity deze fragment doorgeven aan app
                break;
        }
    }

}
