package com.example.dreeki.projectleerlingenapp.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Activities.RouteActivity;
import com.example.dreeki.projectleerlingenapp.Activities.SettingsActivity;
import com.example.dreeki.projectleerlingenapp.App;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0;
    private String m_Text;
    private User user;
    private ImageView ivNood;
    private ImageView imageView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap bitmap;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        user = ((EersteKeerOpenenInterface) getActivity()).getUser();

        ImageView b1 = v.findViewById(R.id.btnGoToRoutes);

        b1.setOnClickListener(this);

        TextView tv = v.findViewById(R.id.tvIcoonPersoon);

        tv.setText(tv.getText().toString().replace("{persoon}", user.profile.getTarget().getName()));

        //Bitmap img = loadImageBitmap(getContext().getApplicationContext(), "profielfoto");
        Bitmap img = loadImageBitmap(getContext().getApplicationContext(), "mentorfoto");

        imageView = v.findViewById(R.id.imagePrent);
        if(img != null){
            imageView.setImageBitmap(img);
        } else{
            imageView.setImageResource(R.drawable.foto_trekken);
        }

        imageView.setOnClickListener(this);

        ImageView iv = v.findViewById(R.id.btnSettings);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Gelieve uw wachtwoord in te geven");

                View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.popup_window, (ViewGroup) getView(), false);

                final EditText input = (EditText) viewInflated.findViewById(R.id.input);
                builder.setView(viewInflated);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        m_Text = input.getText().toString();
                        ((App)getActivity().getApplication()).checkUserPassword(m_Text, getActivity());
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        ivNood = v.findViewById(R.id.btnNood);
        ivNood.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoToRoutes:
                Intent intentRoutes = new Intent(this.getActivity(), RouteActivity.class);
                startActivity(intentRoutes);
                break;
            case R.id.btnNood:
                callUser();
                break;
            case R.id.imagePrent:
                dispatchTakePictureIntent();
                break;
        }
    }

    public void callUser(){
        String number = user.mentor.getTarget().getPhoneNumber();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            try {
                startActivity(intent);
            } catch(SecurityException e) {
                e.printStackTrace();
            }
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
            ((App)getActivity().getApplication()).savePersonalPicture(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

}
