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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Models.Mentor;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.List;

public class EersteKeerOpenenStap4Fragment extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_stap4, container, false);

        ImageView b1 = v.findViewById(R.id.btnGoNextStep4);
        ImageView b2 = v.findViewById(R.id.btnGoPreviousStep4);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        TextView tv = v.findViewById(R.id.tvIcoonPersoon);

        tv.setText(tv.getText().toString().replace("{persoon}", ((EersteKeerOpenenInterface)getActivity()).getName()));

        if (!(((EersteKeerOpenenInterface) getActivity()).getMentors().size() <= 0)) {
            String naam = ((EersteKeerOpenenInterface) getActivity()).getMentors().get(0).getName();
            if(naam != null){
                ((EditText)v.findViewById(R.id.invoerNaamBegeleider)).setText(naam);
            }

            String telefoonNr = ((EersteKeerOpenenInterface) getActivity()).getMentors().get(0).getPhoneNumber();
            if(telefoonNr != null){
                ((EditText)v.findViewById(R.id.invoerTelefoonBegeleider)).setText(telefoonNr);
            }

            String email = ((EersteKeerOpenenInterface) getActivity()).getMentors().get(0).getEmail();
            if(email != null){
                ((EditText)v.findViewById(R.id.invoerEmailBegeleider)).setText(email);
            }
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoNextStep4:
                if(controleerVelden()){
                    String name = ((EditText)getView().findViewById(R.id.invoerNaamBegeleider)).getText().toString().trim();
                    String number = ((EditText)getView().findViewById(R.id.invoerTelefoonBegeleider)).getText().toString().trim();
                    String email = ((EditText)getView().findViewById(R.id.invoerEmailBegeleider)).getText().toString().trim();
                    ((EersteKeerOpenenInterface) getActivity()).getMentors().add(new Mentor(name, number, email));

                    ((EersteKeerOpenenInterface) getActivity()).goToLoginScreen();
                }
                break;
            case R.id.btnGoPreviousStep4:
                ((EersteKeerOpenenInterface) getActivity()).goToStep3();
                break;
        }
    }

    private boolean controleerVelden(){
        boolean allesIngevuld = true;

        EditText e1 = getView().findViewById(R.id.invoerNaamBegeleider);
        EditText e2 = getView().findViewById(R.id.invoerTelefoonBegeleider);
        EditText e3 = getView().findViewById(R.id.invoerEmailBegeleider);

        if(e1.getText().toString() == null || e1.getText().toString().trim() == ""){
            allesIngevuld = false;
            //error geven voor dit veld
        }

        if(e2.getText().toString() == null || e2.getText().toString().trim() == ""){
            allesIngevuld = false;
            //error geven voor dit veld
        }

        if(e3.getText().toString() == null || e3.getText().toString().trim() == ""){
            allesIngevuld = false;
            //error geven voor dit veld
        }else if(!e3.getText().toString().trim().matches("\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b")){
            allesIngevuld = false;
            //error geven dat het een email moet zijn
        }

        return  allesIngevuld;
    }
}
