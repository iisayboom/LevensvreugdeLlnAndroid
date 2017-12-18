package com.example.dreeki.projectleerlingenapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Models.Route;
import com.example.dreeki.projectleerlingenapp.R;

import java.io.File;
import java.io.FileInputStream;
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
        textView.setText(r.end.getTarget().getTitle());

        String newImageName = r.end.getTarget().getImage().replace("uploads/", "");
        Bitmap test = loadImageBitmap(parent.getContext().getApplicationContext(), newImageName);
        imgImageView.setImageBitmap(test);
        // Return the completed view to render on screen
        return convertView;
    }

    public Bitmap loadImageBitmap(Context context, String imageName) {
        Bitmap bitmap = null;
        FileInputStream fiStream;
        try {
            String path = imageName.replaceAll(".png|.jpg", "");
            File file = context.getApplicationContext().getFileStreamPath(path);
            if (file.exists()) Log.d("file", imageName);
            fiStream = context.openFileInput(path);
            bitmap = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 3, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
    }
}
