package com.example.dreeki.projectleerlingenapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Interfaces.RouteInterface;
import com.example.dreeki.projectleerlingenapp.Models.Problem;
import com.example.dreeki.projectleerlingenapp.R;
import com.example.dreeki.projectleerlingenapp.Utils.ColorHandlerForAndroidMaterialDesign;

public class ProbleemManier extends Fragment implements View.OnClickListener {

    private ColorHandlerForAndroidMaterialDesign colorHandlerForAndroidMaterialDesign;
    private Problem problem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_probleem_manier, container, false);

        colorHandlerForAndroidMaterialDesign = new ColorHandlerForAndroidMaterialDesign();

        ImageView ivTekstBg = v.findViewById(R.id.imgBgTekst);
        ivTekstBg.setBackgroundColor(colorHandlerForAndroidMaterialDesign.giveColorForItemOnPosition(0).getColorLight());
        ivTekstBg.setOnClickListener(this);

        TextView txtTekst = v.findViewById(R.id.txtTekst);
        txtTekst.setBackgroundColor(colorHandlerForAndroidMaterialDesign.giveColorForItemOnPosition(0).getColorDark());


        ImageView ivGeluidBg = v.findViewById(R.id.imgBgGeluid);
        ivGeluidBg.setBackgroundColor(colorHandlerForAndroidMaterialDesign.giveColorForItemOnPosition(1).getColorLight());
        ivGeluidBg.setOnClickListener(this);

        TextView txtGeluid = v.findViewById(R.id.txtGeluid);
        txtGeluid.setBackgroundColor(colorHandlerForAndroidMaterialDesign.giveColorForItemOnPosition(1).getColorDark());

        ImageView ivTerug = v.findViewById(R.id.btnTerug);
        ivTerug.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTerug:
                ((RouteInterface)getActivity()).toonHuidigCheckpoint();
                break;
            case R.id.imgBgTekst:
                ((RouteInterface)getActivity()).toonProbleemTekst(problem);
                break;
            case R.id.imgBgGeluid:
                break;
        }
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
