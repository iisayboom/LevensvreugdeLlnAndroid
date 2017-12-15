package com.example.dreeki.projectleerlingenapp.Fragments;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RouteFragment extends Fragment implements View.OnClickListener, TextToSpeech.OnInitListener {

    private ListView list;
    private List<Route> routes;
    private List<Locatie> checkpointsRoute1;
    private List<Locatie> checkpointsRoute2;
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
        checkpointsRoute1 = new ArrayList<>();
        checkpointsRoute2 = new ArrayList<>();

        /*
        Locatie l1 = new Locatie(0,R.drawable.eerste,"Onegem","Aalst", "5", 9300, "Station Aalst","Stap richting het station van Aalst.");
        Locatie l2 = new Locatie(0,R.drawable.tweede,"Stationstraat","Aalst", "17", 9300, "Esplanade Plein","Stap nu richting het Esplanade plein.");
        Locatie l3 = new Locatie(0,R.drawable.derde,"Grote Markt","Aalst", "76", 9300, "Grote Markt","Stap richting de grote markt.");
        Locatie l4 = new Locatie(0,R.drawable.laatste,"Arbeidstraat", "Aalst","14",9300,"Hogent","Stap richting campus hogeschool Gent.");


        checkpointsRoute1.add(l2);
        checkpointsRoute1.add(l3);
        //begin, eind, checkpoints
        routes.add(new Route(l1,l4, checkpointsRoute1,"10"));

        checkpointsRoute2.add(l3);
        checkpointsRoute2.add(l2);
        routes.add(new Route(l4,l1,checkpointsRoute2,"11"));
        */
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
