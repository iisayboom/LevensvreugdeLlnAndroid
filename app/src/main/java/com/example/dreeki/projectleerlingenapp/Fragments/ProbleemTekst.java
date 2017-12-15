package com.example.dreeki.projectleerlingenapp.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.Models.Problem;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.Locale;

public class ProbleemTekst extends Fragment implements View.OnClickListener, TextToSpeech.OnInitListener {

    private Problem problem;
    private TextToSpeech tts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_probleem_tekst, container, false);

        ImageView iv = v.findViewById(R.id.btnBack);
        iv.setOnClickListener(this);

        ImageView imageView = v.findViewById(R.id.btnLuidspreker);
        imageView.setOnClickListener(this);

        TextView textView = v.findViewById(R.id.txtTekst);
        textView.setText(problem.getSolution());
        textView.setLineSpacing(100,1);
        textView.setBackgroundColor(Color.WHITE);

        tts = new TextToSpeech(getContext(), this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                ((RouteInterface)getActivity()).toonHuidigCheckpoint();
                break;
            case R.id.btnLuidspreker:
                tts.speak(problem.getSolution(), TextToSpeech.QUEUE_FLUSH, null);
                break;
        }
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
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
