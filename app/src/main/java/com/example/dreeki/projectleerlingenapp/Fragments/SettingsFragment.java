package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dreeki.projectleerlingenapp.Activities.SettingsListView;
import com.example.dreeki.projectleerlingenapp.Interfaces.SettingsInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_settings, container, false);

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

    }

    private void vulLijst(View v) {
        LinearLayout listView = v.findViewById(R.id.listSettings);

        User u = User.get();

        SettingsListView s1 = new SettingsListView(getContext(),u.getProfile().getPersonalPicture().getPictureLinkId(),"Gebruiker", u.getProfile().getName());
        SettingsListView s2 = new SettingsListView(getContext(),R.drawable.home,"Adres", u.getProfile().getHome().getStreet() + " " + u.getProfile().getHome().getNumber() + ", " + u.getProfile().getHome().getPostalCode() + " " + u.getProfile().getHome().getCity());
        SettingsListView s3 = new SettingsListView(getContext(),R.drawable.begeleider,"Begeleider", u.getMentors().get(0).getName());
        SettingsListView s4 = new SettingsListView(getContext(),R.drawable.email,"Email", u.getProfile().getEmail());
        SettingsListView s5 = new SettingsListView(getContext(),R.drawable.lock,"Wachtwoord", u.getProfile().getPassword());

        listView.addView(s1);
        listView.addView(s2);
        listView.addView(s3);
        listView.addView(s4);
        listView.addView(s5);

    }
}
