package com.example.dreeki.projectleerlingenapp.Activities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.R;

/**
 * Created by nielsdebruyne on 28/11/2017.
 */

public class SettingsListView extends LinearLayout {

    public SettingsListView(Context context, int image, String titel, String inhoud) {
        super(context);
        vulLijst(image,titel, inhoud);
    }

    public SettingsListView(Context context, @Nullable AttributeSet attrs, int image, String titel, String inhoud) {
        super(context, attrs);
        vulLijst(image,titel, inhoud);
    }

    public SettingsListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int image, String titel, String inhoud) {
        super(context, attrs, defStyleAttr);
        vulLijst(image,titel, inhoud);
    }

    public SettingsListView(Context context, int image, String street, String city, String number, int postal) {
        super(context);
        vulAdres(image,street, number, postal, city);
    }

    public void vulLijst(int image,String titel, String inhoud) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_settings, this, true);

        ImageView i = findViewById(R.id.imgPrentje);
        i.setImageResource(image);

        TextView t1 = findViewById(R.id.txtTitel);
        t1.setText(titel);

        EditText t2 = findViewById(R.id.txtStraat);
        t2.setText(inhoud);
    }

    public void vulAdres(int image,String street, String number, int postal, String city) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_settings_adress, this, true);

        ImageView i = findViewById(R.id.imgPrentje);
        i.setImageResource(image);

        EditText t1 = findViewById(R.id.txtStraat);
        t1.setText(street);

        EditText t2 = findViewById(R.id.txtNummer);
        t2.setText(number);

        EditText t3 = findViewById(R.id.txtPostcode);
        t3.setText(postal + "");

        EditText t4 = findViewById(R.id.txtStad);
        t4.setText(city);
    }
}
