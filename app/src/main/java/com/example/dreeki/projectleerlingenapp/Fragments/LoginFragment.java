package com.example.dreeki.projectleerlingenapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Activities.RouteActivity;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.R;

public class LoginFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        Button b1 = v.findViewById(R.id.btnGoToRoutes);

        b1.setOnClickListener(this);

        TextView tv = v.findViewById(R.id.tvIcoonPersoon);

        tv.setText(tv.getText().toString().replace("{persoon}", ((EersteKeerOpenenInterface)getActivity()).getName()));

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoToRoutes:
                Intent intent = new Intent(this.getActivity(), RouteActivity.class);
                startActivity(intent);
                break;
        }
    }
}
