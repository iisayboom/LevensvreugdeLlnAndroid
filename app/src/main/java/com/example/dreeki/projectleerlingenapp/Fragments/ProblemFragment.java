package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.dreeki.projectleerlingenapp.Adapters.ProblemAdapter;
import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.Models.Problem;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.ArrayList;
import java.util.List;

public class ProblemFragment extends Fragment {

    private ProblemAdapter problemAdapter;
    private List<Problem> problems;
    private Problem problem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_problem, container, false);

        GridView problemListView = v.findViewById(R.id.problemList);

        problemAdapter = new ProblemAdapter();
        problemListView.setAdapter(problemAdapter);

//      Alle agreements ophalen van die route
        updateProblems();

        problemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Problem p = problemAdapter.getItem(position);

            }
        });


        return v;
    }

    private void updateProblems() {
        // query uitvoeren, en resultaat opslaan in lijst, die lijst dan toevoegen aan de adapter

        problems = new ArrayList<>();
        problems.add(new Problem("Probleem 1", R.drawable.huis));
        problems.add(new Problem("Probleem 2", R.drawable.lock));
        problemAdapter.setProblems(problems);

    }
}
