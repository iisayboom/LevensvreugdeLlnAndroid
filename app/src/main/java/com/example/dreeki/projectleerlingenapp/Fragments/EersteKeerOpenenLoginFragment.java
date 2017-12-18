package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.Models.Profile;
import com.example.dreeki.projectleerlingenapp.R;

public class EersteKeerOpenenLoginFragment extends Fragment implements View.OnClickListener {

    private String email;
    private String wachtwoord;
    private EditText edt1;
    private EditText edt2;
    private Profile profile;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_login, container, false);

        Button b = v.findViewById(R.id.btnLogin);
        b.setOnClickListener(this);

        edt1 = v.findViewById(R.id.txtUsername);
        edt2 = v.findViewById(R.id.txtWachtwoord);

        ImageView ivBack = v.findViewById(R.id.btnBack);
        ivBack.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        email = edt1.getText().toString();

        wachtwoord = edt2.getText().toString();

        switch(v.getId()) {
            case R.id.btnLogin:
                    ((EersteKeerOpenenInterface)getActivity()).geefEersteKeerOpenenLoginFragmentAanApp(this);
                    ((EersteKeerOpenenInterface)getActivity()).getUserFromBackend(email, wachtwoord);
                break;
            case R.id.btnBack:
                ((EersteKeerOpenenInterface)getActivity()).showFirstScreen();
                break;
        }
    }

}
