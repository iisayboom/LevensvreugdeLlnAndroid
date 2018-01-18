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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Activities.SettingsListView;
import com.example.dreeki.projectleerlingenapp.App;
import com.example.dreeki.projectleerlingenapp.Interfaces.SettingsInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class InstellingenFragment extends Fragment implements View.OnClickListener {

    private List<SettingsListView> settingsViews;
    private User user;
    private Bitmap userfoto;
    private Bitmap begeleider;
    private ImageView prentjeUser;
    private ImageView prentjeMentor;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap bitmap;
    private int gekliktePrent = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_settings, container, false);

        user = ((SettingsInterface)getActivity()).getUser();

        userfoto = loadImageBitmap(getContext().getApplicationContext(), "profielfoto");

        vulLijst(v);

        ImageView iv = v.findViewById(R.id.btnBack);
        iv.setOnClickListener(this);

        Button b = v.findViewById(R.id.btnSave);
        b.setOnClickListener(this);

        return v;


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnBack:
                ((SettingsInterface)getActivity()).showLoginScreen();
                break;

            case R.id.btnSave:
                SaveEverything(v);
                ((SettingsInterface)getActivity()).showLoginScreen();
                break;
        }
    }

    private void SaveEverything(View v) {
        //adres wordt niet opgeslagen tot we databank hebben
        List<String> values = new ArrayList<>();
        for (SettingsListView slv : settingsViews) {
            TextView inhoud = slv.findViewById(R.id.txtStraat);
            values.add(inhoud.getText().toString());
        }

        user.profile.getTarget().setName(values.get(0));
        user.mentor.getTarget().setName(values.get(1));
        user.profile.getTarget().setEmail(values.get(2));

        ((App)getActivity().getApplication()).updateUser();
    }

    private void vulLijst(View v) {
        LinearLayout listView = v.findViewById(R.id.listSettings);

        settingsViews = new ArrayList<>();

        SettingsListView s1 = new SettingsListView(getContext(), userfoto,"User", user.profile.getTarget().getName());
        SettingsListView s2 = new SettingsListView(getContext(), begeleider,"Begeleider", user.mentor.getTarget().getName());
        SettingsListView s3 = new SettingsListView(getContext(),R.drawable.email,"Email", user.profile.getTarget().getEmail());
        SettingsListView s4 = new SettingsListView(getContext(),R.drawable.lock,"Wachtwoord", "");
        SettingsListView s5 = new SettingsListView(getContext(), R.drawable.home, user.profile.getTarget().home.getTarget().getStreet(), user.profile.getTarget().home.getTarget().getCity(), user.profile.getTarget().home.getTarget().getNumber(), user.profile.getTarget().home.getTarget().getPostalCode());

        prentjeUser = s1.findViewById(R.id.imgPrentje);
        prentjeUser.setImageResource(R.drawable.foto_trekken);
        prentjeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gekliktePrent = 1;
                dispatchTakePictureIntent();
            }
        });

        prentjeMentor = s2.findViewById(R.id.imgPrentje);
        prentjeMentor.setImageResource(R.drawable.foto_trekken);
        prentjeMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gekliktePrent = 2;
                dispatchTakePictureIntent();
            }
        });

        settingsViews.add(s1);
        settingsViews.add(s2);
        settingsViews.add(s3);
        settingsViews.add(s4);
        settingsViews.add(s5);

        for (SettingsListView slv : settingsViews) {
            listView.addView(slv);
        }

    }

    public Bitmap loadImageBitmap(Context context, String imageName) {
        Bitmap bitmap = null;
        FileInputStream fiStream;
        try {
            File file = context.getApplicationContext().getFileStreamPath(imageName);
            if (file.exists()) Log.d("file", imageName);
            fiStream = context.openFileInput(imageName);
            bitmap = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 3, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
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
            if(gekliktePrent == 1) {
                ((App)getActivity().getApplication()).savePersonalPicture(bitmap);
                prentjeUser.setImageBitmap(bitmap);
            } else if (gekliktePrent == 2) {
                ((App)getActivity().getApplication()).saveMentorPicture(bitmap);
                prentjeMentor.setImageBitmap(bitmap);
            }
            gekliktePrent = -1;
        }
    }
}
