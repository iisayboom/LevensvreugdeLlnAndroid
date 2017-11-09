package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.dreeki.projectleerlingenapp.Activities.Benodigdheid;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.R;

public class BenodigdheidFragment extends Fragment {

    @IdRes private final int BENODIGDHEID1 = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_benodigdheid, container, false);

        Benodigdheid b = new Benodigdheid(getContext());
        Benodigdheid b2 = new Benodigdheid(getContext());
        Benodigdheid b3 = new Benodigdheid(getContext());
        Benodigdheid b4 = new Benodigdheid(getContext());
        Benodigdheid b5 = new Benodigdheid(getContext());
        Benodigdheid b6 = new Benodigdheid(getContext());

        GridLayout grid = v.findViewById(R.id.benodigdhedenGrid);

        grid.setColumnCount(2);
        grid.setRowCount(3);

        grid.addView(b);
        grid.addView(b2);
        grid.addView(b3);
        grid.addView(b4);
        grid.addView(b5);
        grid.addView(b6);

        return v;
    }


}
