package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.R;

public class EersteKeerOpenenStap0Fragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_stap0, container, false);

        ImageView iv = v.findViewById(R.id.btnGoNextStep0);
        iv.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoNextStep0:
                ((EersteKeerOpenenInterface)getActivity()).goToStep1();
                break;
        }
    }
}
