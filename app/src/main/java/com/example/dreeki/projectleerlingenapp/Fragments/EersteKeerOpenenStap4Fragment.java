package com.example.dreeki.projectleerlingenapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.App;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Models.Mentor;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class EersteKeerOpenenStap4Fragment extends Fragment implements View.OnClickListener{
    private TextView foutBoodschap;
    private Bitmap bitmap;
    private ImageView begeleiderFoto;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_stap4, container, false);

        ImageView b1 = v.findViewById(R.id.btnGoNextStep4);
        ImageView b2 = v.findViewById(R.id.btnGoPreviousStep4);
        foutBoodschap = v.findViewById(R.id.txtFout);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        TextView tv = v.findViewById(R.id.tvIcoonPersoon);

        tv.setText(tv.getText().toString().replace("{persoon}", ((EersteKeerOpenenInterface)getActivity()).getName()));

        if(((EersteKeerOpenenInterface)getActivity()).getMentorEmail() != null && ((EersteKeerOpenenInterface)getActivity()).getMentorEmail().trim() != ""){
            EditText et = v.findViewById(R.id.invoerEmailBegeleider);
            et.setText(((EersteKeerOpenenInterface)getActivity()).getMentorEmail());
        }

        begeleiderFoto = v.findViewById(R.id.imgFoto);
        begeleiderFoto.setImageResource(R.drawable.foto_trekken);
        begeleiderFoto.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoNextStep4:
                if(controleerVelden() && bitmap != null){
                    String email = ((EditText)getView().findViewById(R.id.invoerEmailBegeleider)).getText().toString().trim();
                    ((EersteKeerOpenenInterface)getActivity()).setMentorEmail(email);
                    ((EersteKeerOpenenInterface)getActivity()).geefEersteKeerOpenenStapFragmentAanApp(this);
                    ((EersteKeerOpenenInterface)getActivity()).goToLoginScreen();
                }
                break;
            case R.id.btnGoPreviousStep4:
                ((EersteKeerOpenenInterface) getActivity()).goToStep3();
                break;
            case R.id.imgFoto:
                dispatchTakePictureIntent();
                break;
        }
    }

    private boolean controleerVelden(){
        boolean allesIngevuld = true;

        EditText e3 = getView().findViewById(R.id.invoerEmailBegeleider);

        if(e3.getText().toString() == null || e3.getText().toString().trim() == ""){
            allesIngevuld = false;
            //error geven voor dit veld
        }else if(!e3.getText().toString().trim().matches("\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b")){
            allesIngevuld = false;
            //error geven dat het een email moet zijn
        }

        return  allesIngevuld;
    }

    public void setFoutBoodschap(String foutBoodschap) {
        this.foutBoodschap.setText(foutBoodschap);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            ((App)getActivity().getApplication()).setPersoonIcoon(bitmap);
            ((App)getActivity().getApplication()).saveMentorPicture(bitmap);
            begeleiderFoto.setImageBitmap(bitmap);
        }
    }
}
