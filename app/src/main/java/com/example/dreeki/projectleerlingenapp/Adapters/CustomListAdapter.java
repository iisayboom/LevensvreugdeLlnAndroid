package com.example.dreeki.projectleerlingenapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.List;

/**
 * Created by nielsdebruyne on 21/11/2017.
 */

public class CustomListAdapter extends ArrayAdapter<Route> {

    public CustomListAdapter(@NonNull Context context, List<Route> lijst) {
        super(context, 0, lijst);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_routes, parent, false);
        }

        // Get the data item for this position
        Route r = getItem(position);

        // Lookup view for data population
        ImageView imgImageView = (ImageView) convertView.findViewById(R.id.imgPrentje);
        TextView textView = (TextView) convertView.findViewById(R.id.txtTitel);
        // Populate the data into the template view using the data object
        textView.setText(r.getEnd().getTitle());
        imgImageView.setImageResource(r.getCheckpoints().get(r.getCheckpoints().size()-1).getImage());
        // Return the completed view to render on screen
        return convertView;
    }
}
