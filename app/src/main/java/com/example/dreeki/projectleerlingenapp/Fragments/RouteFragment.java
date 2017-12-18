package com.example.dreeki.projectleerlingenapp.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dreeki.projectleerlingenapp.Adapters.CustomListAdapter;
import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.Models.User;
import com.example.dreeki.projectleerlingenapp.Models.Locatie;
import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RouteFragment extends Fragment implements View.OnClickListener, TextToSpeech.OnInitListener {

    private ListView list;
    private List<Route> routes;
    private Route gekozenRoute;
    private TextToSpeech tts;
    private CustomListAdapter adapter;
    private User user;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_route, container, false);

        ImageView iv = v.findViewById(R.id.btnBack);
        iv.setOnClickListener(this);

        list = v.findViewById(R.id.listRoutes);
        routes = new ArrayList<>();

        user = ((RouteInterface)getActivity()).getUser();
        routes = user.routes;


        adapter = new CustomListAdapter(getContext(), routes);


        this.tts = new TextToSpeech(getContext(), this);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gekozenRoute = routes.get(position);
                ((RouteInterface)getActivity()).setGekozenRoute(gekozenRoute);
                Locatie locatie = ((RouteInterface)getActivity()).getNextLocation();
                ((RouteInterface)getActivity()).volgendCheckpoint(locatie.getTitle(), "1/" + (gekozenRoute.checkpoints.size() + 1) , locatie.getImage(), locatie.getAanwijzing());
                tts.speak(gekozenRoute.end.getTarget().getTitle(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnBack:
                ((RouteInterface)getActivity()).goToLoginScreen();
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


}
