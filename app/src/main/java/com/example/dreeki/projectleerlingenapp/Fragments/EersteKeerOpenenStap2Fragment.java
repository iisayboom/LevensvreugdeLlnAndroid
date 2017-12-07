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

    private boolean selected = false;
    private boolean maleIsSelected = false;
    private boolean femaleIsSelected = false;
    private int lastClicked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_stap2, container, false);

        ImageView b1 = v.findViewById(R.id.btnGoNextStep5);
        ImageView b2 = v.findViewById(R.id.btnGoPreviousStep8);
        final ImageView i2 = v.findViewById(R.id.prent2);
        final ImageView i3 = v.findViewById(R.id.prent3);



        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected == false && maleIsSelected == false) {
                    i2.setImageResource(R.drawable.female_selected);
                    selected = true;
                    femaleIsSelected = true;
                } else if (maleIsSelected == false) {
                    i2.setImageResource(R.drawable.construction_icon);
                    selected = false;
                    femaleIsSelected = false;
                }

                lastClicked = 1;
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected == false && femaleIsSelected == false ) {
                    i3.setImageResource(R.drawable.male_selected);
                    selected = true;
                    maleIsSelected = true;
                } else if(femaleIsSelected == false){
                    i3.setImageResource(R.drawable.worker);
                    selected = false;
                    maleIsSelected = false;
                }

                lastClicked = 2;
            }
        });

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
            case R.id.btnGoNextStep5:
                if(lastClicked == 1) {
                    ((EersteKeerOpenenInterface) getActivity()).getPicture().setPictureLinkId(R.drawable.construction_icon);
                } else if(lastClicked == 2) {
                    ((EersteKeerOpenenInterface) getActivity()).getPicture().setPictureLinkId(R.drawable.worker);
                }

                if(((EersteKeerOpenenInterface) getActivity()).getPicture().getPictureLinkId() != -1){
                    ((EersteKeerOpenenInterface) getActivity()).goToStep3();
                }
                break;
            case R.id.btnGoPreviousStep8:
                ((EersteKeerOpenenInterface) getActivity()).goToStep8();
                break;
        }
    }
}
