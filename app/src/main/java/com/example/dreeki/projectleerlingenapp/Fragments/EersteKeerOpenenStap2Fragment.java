package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.R;

public class EersteKeerOpenenStap2Fragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_stap2, container, false);

        Button b1 = v.findViewById(R.id.btnGoNextStep2);
        Button b2 = v.findViewById(R.id.btnGoPreviousStep2);
        ImageView i1 = v.findViewById(R.id.prent1);
        ImageView i2 = v.findViewById(R.id.prent2);
        ImageView i3 = v.findViewById(R.id.prent3);
        ImageView i4 = v.findViewById(R.id.prent4);
        ImageView i5 = v.findViewById(R.id.prent5);
        ImageView i6 = v.findViewById(R.id.prent6);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        i4.setOnClickListener(this);
        i5.setOnClickListener(this);
        i6.setOnClickListener(this);

        TextView tv = v.findViewById(R.id.tvIcoonPersoon);

        tv.setText(tv.getText().toString().replace("{persoon}", ((EersteKeerOpenenInterface)getActivity()).getName()));

        int prentId = ((EersteKeerOpenenInterface) getActivity()).getPicture().getPictureLinkId();
        if(prentId != -1){
            //selecteer prent met findById(prentId)
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoNextStep2:
                if(((EersteKeerOpenenInterface) getActivity()).getPicture().getPictureLinkId() != -1){
                    ((EersteKeerOpenenInterface) getActivity()).goToStep3();
                }
                break;
            case R.id.btnGoPreviousStep2:
                ((EersteKeerOpenenInterface) getActivity()).goToStep1();
                break;
            case R.id.prent1:
                ((EersteKeerOpenenInterface) getActivity()).getPicture().setPictureLinkId(R.drawable.architect);
                //omcirkelen enzo
                break;
            case R.id.prent2:
                ((EersteKeerOpenenInterface) getActivity()).getPicture().setPictureLinkId(R.drawable.construction_icon);
                break;
            case R.id.prent3:
                ((EersteKeerOpenenInterface) getActivity()).getPicture().setPictureLinkId(R.drawable.cook_male);
                break;
            case R.id.prent4:
                ((EersteKeerOpenenInterface) getActivity()).getPicture().setPictureLinkId(R.drawable.fireman_icon);
                break;
            case R.id.prent5:
                ((EersteKeerOpenenInterface) getActivity()).getPicture().setPictureLinkId(R.drawable.cook);
                break;
            case R.id.prent6:
                ((EersteKeerOpenenInterface) getActivity()).getPicture().setPictureLinkId(R.drawable.worker);
                break;
        }
    }
}
