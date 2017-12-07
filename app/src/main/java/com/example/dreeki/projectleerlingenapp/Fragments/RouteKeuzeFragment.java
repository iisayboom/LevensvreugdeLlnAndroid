package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.R;

import static android.content.ContentValues.TAG;

public class RouteKeuzeFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_route_keuze, container, false);

        ImageView iv = v.findViewById(R.id.btnStart);
        iv.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        v.findViewById(R.id.txtBestemming);
        switch(v.getId()) {
            case R.id.btnStart:
                //((RouteInterface)getActivity()).volgendCheckpoint("Oma","1/3", R.drawable.station,);
                break;
        }
    }
}
