package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.R;

public class VerplaatsManier extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_verplaats_manier, container, false);

        ImageButton ibFiets = v.findViewById(R.id.imageButtonFiets);
        ibFiets.setOnClickListener(this);

        ImageButton ibBus = v.findViewById(R.id.imageButtonBus);
        ibBus.setOnClickListener(this);

        ImageButton ibTrein = v.findViewById(R.id.imageButtonTrein);
        ibTrein.setOnClickListener(this);

        ImageButton ibVoet = v.findViewById(R.id.imageButtonVoet);
        ibVoet.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButtonFiets:
                ((RouteInterface) getActivity()).toonBenodigdheden();
                break;
            case R.id.imageButtonBus:
                ((RouteInterface) getActivity()).toonBenodigdheden();
                break;
            case R.id.imageButtonTrein:
                ((RouteInterface) getActivity()).toonBenodigdheden();
                break;
            case R.id.imageButtonVoet:
                ((RouteInterface) getActivity()).toonBenodigdheden();
                break;
        }
    }
}
