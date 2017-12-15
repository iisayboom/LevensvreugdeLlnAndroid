package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.R;

public class EersteKeerOpenenStap7Fragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_stap7, container, false);


        String email = ((EersteKeerOpenenInterface) getActivity()).getEmail();
        if(email != null){
            ((EditText)v.findViewById(R.id.emailInvullenVeld)).setText(email);
        }

        ImageView b = v.findViewById(R.id.btnGoNextStep8);
        ImageView b2 = v.findViewById(R.id.btnPrevStep1);
        b.setOnClickListener(this);
        b2.setOnClickListener(this);

        TextView tv = v.findViewById(R.id.tvIcoonPersoon);

        tv.setText(tv.getText().toString().replace("{persoon}", ((EersteKeerOpenenInterface)getActivity()).getName()));

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoNextStep8:
                String email = ((EditText)v.getRootView().findViewById(R.id.emailInvullenVeld)).getText().toString().trim();
                if(email != null && !email.equals("")) {
                    ((EersteKeerOpenenInterface) getActivity()).setEmail(email);
                    ((EersteKeerOpenenInterface) getActivity()).goToStep8();
                }
                break;
            case R.id.btnPrevStep1:
                ((EersteKeerOpenenInterface) getActivity()).goToStep1();
                break;
        }
    }
}
