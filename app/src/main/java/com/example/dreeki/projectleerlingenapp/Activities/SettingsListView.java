package com.example.dreeki.projectleerlingenapp.Activities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.R;

/**
 * Created by nielsdebruyne on 28/11/2017.
 */

public class SettingsListView extends LinearLayout {

    public SettingsListView(Context context,int image,String titel, String inhoud) {
        super(context);
        vulLijst(image,titel, inhoud);
    }

    public SettingsListView(Context context, @Nullable AttributeSet attrs,int image,String titel, String inhoud) {
        super(context, attrs);
        vulLijst(image,titel, inhoud);
    }

    public SettingsListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,int image,String titel, String inhoud) {
        super(context, attrs, defStyleAttr);
        vulLijst(image,titel, inhoud);
    }

    public void vulLijst(int image,String titel, String inhoud) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_settings, this, true);

        ImageView i = findViewById(R.id.imgPrentje);
        i.setImageResource(image);

        TextView t1 = findViewById(R.id.txtTitel);
        t1.setText(titel);

        TextView t2 = findViewById(R.id.txtInhoud);
        t2.setText(inhoud);
    }
}
