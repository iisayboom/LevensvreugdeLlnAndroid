package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.R;


public class EersteKeerOpenenStap1Fragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_stap1, container, false);

        String naam = ((EersteKeerOpenenInterface) getActivity()).getName();
        if(naam != null){
            ((EditText)v.findViewById(R.id.naamInvullenVeld)).setText(naam);
        }

        ImageView b = v.findViewById(R.id.btnGoNextStep1);
        b.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoNextStep1:
                String naam = ((EditText)v.getRootView().findViewById(R.id.naamInvullenVeld)).getText().toString().trim();
                if(naam != null && !naam.equals("")) {
                    ((EersteKeerOpenenInterface) getActivity()).setName(naam);
                    ((EersteKeerOpenenInterface) getActivity()).goToStep7();
                }
                break;
        }
    }
}
