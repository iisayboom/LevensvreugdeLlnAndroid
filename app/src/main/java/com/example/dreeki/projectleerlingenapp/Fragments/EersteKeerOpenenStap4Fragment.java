package com.example.dreeki.projectleerlingenapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.R;

public class EersteKeerOpenenStap4Fragment extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_stap4, container, false);

        Button b1 = v.findViewById(R.id.btnGoNextStep4);
        Button b2 = v.findViewById(R.id.btnGoPreviousStep4);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoNextStep4:
                if(true){
                    ((EersteKeerOpenenInterface) getActivity()).goToStep5();
                }
                break;
            case R.id.btnGoPreviousStep4:
                ((EersteKeerOpenenInterface) getActivity()).goToStep3();
                break;
        }
    }
}