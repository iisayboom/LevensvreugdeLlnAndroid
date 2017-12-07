package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dreeki.projectleerlingenapp.Adapters.CustomListAdapter;
import com.example.dreeki.projectleerlingenapp.Interfaces.EersteKeerOpenenInterface;
import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.Models.Location;
import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.ArrayList;
import java.util.List;

public class RouteFragment extends Fragment implements View.OnClickListener {

    private ListView list;
    private List<Route> routes;
    private List<Location> checkpointsRoute1;
    private List<Location> checkpointsRoute2;
    private Route gekozenRoute;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_route, container, false);

        ImageView iv = v.findViewById(R.id.btnBack);
        iv.setOnClickListener(this);

        list = v.findViewById(R.id.listRoutes);
        routes = new ArrayList<>();
        checkpointsRoute1 = new ArrayList<>();
        checkpointsRoute2 = new ArrayList<>();

        Location l1 = new Location(R.drawable.eerste,"Onegem","Aalst", "5", 9300, "Station Aalst",  "1","Stap richting het station van Aalst.");
        Location l2 = new Location(R.drawable.tweede,"Stationstraat","Aalst", "17", 9300, "Esplanade Plein","2","Stap nu richting het Esplanade plein.");
        Location l3 = new Location(R.drawable.derde,"Grote Markt","Aalst", "76", 9300, "Grote Markt","3","Stap richting de grote markt.");
        Location l4 = new Location(R.drawable.laatste,"Arbeidstraat", "Aalst","14",9300,"Hogent","4","Stap richting campus hogeschool Gent.");

        checkpointsRoute1.add(l2);
        checkpointsRoute1.add(l3);
        //begin, eind, checkpoints
        routes.add(new Route(l1,l4, checkpointsRoute1,"10"));

        checkpointsRoute2.add(l3);
        checkpointsRoute2.add(l2);
        routes.add(new Route(l4,l1,checkpointsRoute2,"11"));

        CustomListAdapter adapter = new CustomListAdapter(getContext(), routes);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gekozenRoute = routes.get(position);
                ((RouteInterface)getActivity()).setGekozenRoute(gekozenRoute);
                Location location = ((RouteInterface)getActivity()).getNextLocation();
                ((RouteInterface)getActivity()).volgendCheckpoint(location.getTitle(), "1/" + (gekozenRoute.getCheckpoints().size() + 1) , location.getImage(), location.getAanwijzing());
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
}
