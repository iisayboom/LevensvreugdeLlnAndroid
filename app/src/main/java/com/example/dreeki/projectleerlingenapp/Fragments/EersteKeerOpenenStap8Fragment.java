package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.R;

public class EersteKeerOpenenStap8Fragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_stap8, container, false);

        String password = ((EersteKeerOpenenInterface) getActivity()).getPassword();
        if(password != null){
            ((EditText)v.findViewById(R.id.WachtwoordInvullenVeld)).setText(password);
        }

        ImageView b = v.findViewById(R.id.btnGoNextStep3);
        ImageView b2 = v.findViewById(R.id.btnPrevStep3);
        b.setOnClickListener(this);
        b2.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoNextStep3:
                String password = ((EditText)v.getRootView().findViewById(R.id.WachtwoordInvullenVeld)).getText().toString().trim();
                if(password != null && !password.equals("")) {
                    ((EersteKeerOpenenInterface) getActivity()).setPassword(password);
                    ((EersteKeerOpenenInterface) getActivity()).goToStep8();
                }
                ((EersteKeerOpenenInterface) getActivity()).goToStep2();
                break;
            case R.id.btnPrevStep3:
                ((EersteKeerOpenenInterface) getActivity()).goToStep7();
                break;
        }
    }
}
