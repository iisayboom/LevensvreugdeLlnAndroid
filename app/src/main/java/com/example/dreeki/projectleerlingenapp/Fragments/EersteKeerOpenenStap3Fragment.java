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
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.R;

public class EersteKeerOpenenStap3Fragment extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_stap3, container, false);

        Button b1 = v.findViewById(R.id.btnGoNextStep3);
        Button b2 = v.findViewById(R.id.btnGoPreviousStep3);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        TextView tv = v.findViewById(R.id.tvIcoonPersoon);

        tv.setText(tv.getText().toString().replace("{persoon}", ((EersteKeerOpenenInterface)getActivity()).getName()));

        String straat = ((EersteKeerOpenenInterface) getActivity()).getStreet();
        if(straat != null){
            ((EditText)v.findViewById(R.id.invoerStraat)).setText(straat);
        }

        int nr = ((EersteKeerOpenenInterface) getActivity()).getNumber();
        if(nr != 0){
            ((EditText)v.findViewById(R.id.invoerNr)).setText(nr + "");
        }

        String gemeente = ((EersteKeerOpenenInterface) getActivity()).getCity();
        if(gemeente != null){
            ((EditText)v.findViewById(R.id.invoerGemeente)).setText(gemeente);
        }

        int postCode = ((EersteKeerOpenenInterface) getActivity()).getPostalCode();
        if(postCode != 0){
            ((EditText)v.findViewById(R.id.invoerPostcode)).setText(postCode + "");
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoNextStep3:
                if(controleerVelden()){
                    ((EersteKeerOpenenInterface) getActivity()).setStreet(((EditText)getView().findViewById(R.id.invoerStraat)).getText().toString().trim());
                    ((EersteKeerOpenenInterface) getActivity()).setNumber(Integer.parseInt(((EditText)getView().findViewById(R.id.invoerNr)).getText().toString().trim()));
                    ((EersteKeerOpenenInterface) getActivity()).setCity(((EditText)getView().findViewById(R.id.invoerGemeente)).getText().toString().trim());
                    ((EersteKeerOpenenInterface) getActivity()).setPostalCode(Integer.parseInt(((EditText)getView().findViewById(R.id.invoerPostcode)).getText().toString().trim()));

                    ((EersteKeerOpenenInterface) getActivity()).goToStep4();
                }
                break;
            case R.id.btnGoPreviousStep3:
                ((EersteKeerOpenenInterface) getActivity()).goToStep2();
                break;
        }
    }

    private boolean controleerVelden(){
        boolean allesIngevuld = true;

        EditText e1 = getView().findViewById(R.id.invoerStraat);
        EditText e2 = getView().findViewById(R.id.invoerNr);
        EditText e3 = getView().findViewById(R.id.invoerGemeente);
        EditText e4 = getView().findViewById(R.id.invoerPostcode);

        if(e1.getText().toString() == null || e1.getText().toString().trim() == ""){
            allesIngevuld = false;
            //error geven voor dit veld
        }

        if(e2.getText().toString() == null || e2.getText().toString().trim() == ""){
            allesIngevuld = false;
            //error geven voor dit veld
        }else if(!e2.getText().toString().trim().matches("[1-9][0-9]*")){
            allesIngevuld = false;
            //error geven dat het een nummer moet zijn
        }

        if(e3.getText().toString() == null || e3.getText().toString().trim() == ""){
            allesIngevuld = false;
            //error geven voor dit veld
        }

        if(e4.getText().toString() == null || e4.getText().toString().trim() == ""){
            allesIngevuld = false;
            //error geven voor dit veld
        }else if(!e4.getText().toString().trim().matches("[1-9][0-9]{3}")){
            allesIngevuld = false;
            //error geven dat het een nummer moet zijn van max 4 lang, tussen 1000 en 9999
        }

        return  allesIngevuld;
    }
}
