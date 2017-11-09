package com.example.dreeki.projectleerlingenapp.Activities;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.dreeki.projectleerlingenapp.R;

/**
 * Created by nielsdebruyne on 08/11/2017.
 */

public class Benodigdheid extends ConstraintLayout {

    private boolean geselecteerd;

    public Benodigdheid(Context context) {
        super(context);
        initBenodigdheid();
    }

    public Benodigdheid(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBenodigdheid();
    }

    public Benodigdheid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBenodigdheid();
    }

    private void initBenodigdheid() {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.benodigdheid, this, true);

        geselecteerd = false;

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                geselecteerd = !geselecteerd;
                if (geselecteerd) {
                    duidBenodigdheidAan();
                } else {
                    resetBenodigdheid();
                }
            }
        });
    }

    public void duidBenodigdheidAan() {

        ImageView imageView = findViewById(R.id.omkaderingBenodigdheid);
        imageView.setImageResource(R.drawable.rectangle);
    }

    public void resetBenodigdheid() {
        ImageView imageView = findViewById(R.id.omkaderingBenodigdheid);
        imageView.setImageResource(R.drawable.whiterect);
    }
}