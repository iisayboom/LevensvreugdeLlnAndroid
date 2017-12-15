package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Activities.SettingsListView;
import com.example.dreeki.projectleerlingenapp.Interfaces.SettingsInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.ArrayList;
import java.util.List;

public class InstellingenFragment extends Fragment implements View.OnClickListener {

    private List<SettingsListView> settingsViews;
    private User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_settings, container, false);

        user = ((SettingsInterface)getActivity()).getUser();

        vulLijst(v);

        ImageView iv = v.findViewById(R.id.btnBack);
        iv.setOnClickListener(this);

        Button b = v.findViewById(R.id.btnSave);
        b.setOnClickListener(this);

        return v;


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnBack:
                ((SettingsInterface)getActivity()).showLoginScreen();
                break;

            case R.id.btnSave:
                SaveEverything(v);
                ((SettingsInterface)getActivity()).showLoginScreen();
                break;
        }
    }

    private void SaveEverything(View v) {
        //adres wordt niet opgeslagen tot we databank hebben
        List<String> values = new ArrayList<>();
        for (SettingsListView slv : settingsViews) {
            TextView inhoud = slv.findViewById(R.id.txtStraat);
            values.add(inhoud.getText().toString());
        }

        user.profile.getTarget().setName(values.get(0));
        user.mentor.getTarget().setName(values.get(2));
        user.profile.getTarget().setEmail(values.get(3));
        //user.profile.getTarget().setPassword(values.get(4));
    }

    private void vulLijst(View v) {
        LinearLayout listView = v.findViewById(R.id.listSettings);

        settingsViews = new ArrayList<>();

        SettingsListView s1 = new SettingsListView(getContext(), user.profile.getTarget().getPersonalPicture(),"User", user.profile.getTarget().getName());
        SettingsListView s2 = new SettingsListView(getContext(),R.drawable.begeleider,"Begeleider", user.mentor.getTarget().getName());
        SettingsListView s3 = new SettingsListView(getContext(),R.drawable.email,"Email", user.profile.getTarget().getEmail());
        SettingsListView s4 = new SettingsListView(getContext(),R.drawable.lock,"Wachtwoord", "test");
        SettingsListView s5 = new SettingsListView(getContext(), R.drawable.home, user.profile.getTarget().home.getTarget().getStreet(), user.profile.getTarget().home.getTarget().getCity(), user.profile.getTarget().home.getTarget().getNumber(), user.profile.getTarget().home.getTarget().getPostalCode());

        settingsViews.add(s1);
        settingsViews.add(s2);
        settingsViews.add(s3);
        settingsViews.add(s4);
        settingsViews.add(s5);

        for (SettingsListView slv : settingsViews) {
            listView.addView(slv);
        }

    }
}
