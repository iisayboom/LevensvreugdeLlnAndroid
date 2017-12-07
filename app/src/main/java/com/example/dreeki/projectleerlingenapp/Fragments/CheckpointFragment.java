package com.example.dreeki.projectleerlingenapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.Models.Location;
import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.zip.Inflater;

public class CheckpointFragment extends Fragment implements View.OnClickListener {

    private String titel;
    private int img;
    private String stap;

    private TextView textViewTitel;
    private TextView txtStap;
    private ImageView imgageView;

    private Location location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_checkpoint, container, false);

        textViewTitel = v.findViewById(R.id.txtBestemming);
        imgageView = v.findViewById(R.id.imgCheckpoint);
        txtStap = v.findViewById(R.id.txtStap);

        ImageView gefaald = v.findViewById(R.id.btnGefaald);
        gefaald.setOnClickListener(this);

        location = ((RouteInterface)getActivity()).getLocation();
        titel = location.toString();
        img = getArguments().getInt("img");
        stap = getArguments().getString("stap");

        txtStap.setText(stap);
        textViewTitel.setText(titel);
        imgageView.setImageResource(img);

        ImageView img = v.findViewById(R.id.btnGelukt);
        img.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        Route route = ((RouteInterface)getActivity()).getRouteKeuze();
        Location location = ((RouteInterface)getActivity()).getNextLocation();

        switch(v.getId()) {
            case R.id.btnGelukt:
                if(location == null) {
                    ((RouteInterface)getActivity()).toonGeslaagdScherm();
                } else {
                    ((RouteInterface)getActivity()).volgendCheckpoint(location.getTitle(), "1/" + (route.getCheckpoints().size()-1) , location.getImage(), location.getAanwijzing());
                }
                break;
            case R.id.btnGefaald:
                //((RouteInterface)getActivity()).toonProblemen();
                break;
        }
    }

}
