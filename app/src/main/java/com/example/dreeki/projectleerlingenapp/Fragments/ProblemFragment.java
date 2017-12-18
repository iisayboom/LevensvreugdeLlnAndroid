package com.example.dreeki.projectleerlingenapp.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.dreeki.projectleerlingenapp.Adapters.ProblemAdapter;
import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.Models.Problem;
import com.example.dreeki.projectleerlingenapp.R;
import com.example.dreeki.projectleerlingenapp.Utils.ColorHandlerForAndroidMaterialDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProblemFragment extends Fragment implements View.OnClickListener, TextToSpeech.OnInitListener {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0;
    private ProblemAdapter problemAdapter;
    private List<Problem> problems;
    private ColorHandlerForAndroidMaterialDesign colorHandler;
    private TextToSpeech tts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_problem, container, false);
        problems = new ArrayList<>();
        colorHandler = new ColorHandlerForAndroidMaterialDesign();
        
        final GridView problemListView = v.findViewById(R.id.problemList);

        CardView c = v.findViewById(R.id.card_view);

        problemAdapter = new ProblemAdapter();
        problemAdapter.setProblems(((RouteInterface)getActivity()).getLocation().getProblemen());

        problemListView.setAdapter(problemAdapter);
        problems.addAll(problemAdapter.getProblems());
        problemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == getProblems().size()-1) {
                    callUser();
                } else {
                    tts.speak(problems.get(position).getProbleem(), TextToSpeech.QUEUE_FLUSH, null);
                    ((RouteInterface) getActivity()).toonProbleemTekst(problemAdapter.getItem(position));
                }
            }
        });

        ImageView ivBack = v.findViewById(R.id.btnBack);
        ivBack.setOnClickListener(this);

        tts = new TextToSpeech(getContext(), this);

        return v;
    }

    public void callUser(){
        String number = ("tel:" + "1234");
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(number));

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

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnBack:
                ((RouteInterface) getActivity()).toonHuidigCheckpoint();
                break;
        }
    }

    public List<Problem> getProblems() {
        return problems;
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
