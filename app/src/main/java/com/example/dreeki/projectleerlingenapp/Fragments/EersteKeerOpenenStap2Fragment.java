package com.example.dreeki.projectleerlingenapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.App;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.R;

import java.io.File;
import java.io.FileInputStream;

import static android.app.Activity.RESULT_OK;

public class EersteKeerOpenenStap2Fragment extends Fragment implements View.OnClickListener{

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap bitmap;
    private Bitmap profielFotoBitmap;
    private ImageView profielFoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eerste_keer_openen_stap2, container, false);

        ImageView b1 = v.findViewById(R.id.btnGoNextStep5);
        ImageView b2 = v.findViewById(R.id.btnGoPreviousStep8);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        profielFoto = v.findViewById(R.id.imgFoto);
        profielFoto.setImageResource(R.drawable.foto_trekken);
        profielFoto.setOnClickListener(this);

        TextView tv = v.findViewById(R.id.tvIcoonPersoon);

        tv.setText(tv.getText().toString().replace("{persoon}", ((EersteKeerOpenenInterface)getActivity()).getName()));

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoNextStep5:
                if(bitmap != null) {
                    ((EersteKeerOpenenInterface) getActivity()).goToStep3();
                }
                break;
            case R.id.btnGoPreviousStep8:
                ((EersteKeerOpenenInterface) getActivity()).goToStep8();
                break;

            case R.id.imgFoto:
                dispatchTakePictureIntent();
                break;
        }
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
            ((App)getActivity().getApplication()).savePersonalPicture(bitmap);
            profielFoto.setImageBitmap(bitmap);
        }
    }
}
