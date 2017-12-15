package com.example.dreeki.projectleerlingenapp.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.Models.Locatie;
import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.Locale;

public class CheckpointFragment extends Fragment implements View.OnClickListener, TextToSpeech.OnInitListener  {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private String titel;
    private int img;
    private String stap;

    private TextView textViewTitel;
    private TextView txtStap;
    private ImageView imgageView;

    private static Locatie locatie;
    private TextToSpeech tts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_checkpoint, container, false);

        textViewTitel = v.findViewById(R.id.txtBestemming);
        imgageView = v.findViewById(R.id.imgCheckpoint);
        txtStap = v.findViewById(R.id.txtStap);

        ImageView showlogin = v.findViewById(R.id.btnToonRoutes);
        showlogin.setOnClickListener(this);

        ImageView gefaald = v.findViewById(R.id.btnGefaald);
        gefaald.setOnClickListener(this);


            locatie = ((RouteInterface)getActivity()).getLocation();
            titel = locatie.toString();
            img = getArguments().getInt("img");
            stap = getArguments().getString("stap");

            txtStap.setText(stap);
            textViewTitel.setText(titel);
            imgageView.setImageResource(img);




        ImageView img = v.findViewById(R.id.btnGelukt);
        img.setOnClickListener(this);

        ImageView iv = v.findViewById(R.id.btnNood);
        iv.setOnClickListener(this);

        tts = new TextToSpeech(getContext(), this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Route route = ((RouteInterface) getActivity()).getRouteKeuze();

        switch (v.getId()) {
            case R.id.btnGelukt:
                Locatie locatie2 = ((RouteInterface) getActivity()).getNextLocation();
                //sendSMSMessage();
                if (locatie2 == null) {
                    ((RouteInterface) getActivity()).toonGeslaagdScherm();
                } else {
                    ((RouteInterface) getActivity()).volgendCheckpoint(locatie2.getTitle(), "1/" + (route.checkpoints.size() - 1), locatie2.getImage(), locatie2.getAanwijzing());
                }
                break;
            case R.id.btnGefaald:
                tts.speak("Wat is je probleem?", TextToSpeech.QUEUE_FLUSH, null);
                ((RouteInterface) getActivity()).toonProblemen();
                break;
            case R.id.btnNood:
                String text = locatie.getAanwijzing();
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btnToonRoutes:
                ((RouteInterface)getActivity()).toonRoutes();
                ((RouteInterface)getActivity()).resetCurrentCheckPoint();
                break;
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale lang = tts.getLanguage();
            int result = tts.setLanguage(lang);

            if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                Log.e("Language","This language is not supported");
            }
        } else {
            Log.e("init failed","init failed");
        }
    }

    private String phoneNo = ("tel:" + "1234");
    private String message = "Message";

    protected void sendSMSMessage() {


        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(),"SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}
