package com.example.dreeki.projectleerlingenapp.Adapters;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Models.Problem;
import com.example.dreeki.projectleerlingenapp.R;
import com.example.dreeki.projectleerlingenapp.Utils.ColorHandlerForAndroidMaterialDesign;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nielsdebruyne on 28/11/2017.
 */

public class ProblemAdapter extends BaseAdapter {
    private List<Problem> problems;
    private ColorHandlerForAndroidMaterialDesign colorHandler;

    private static class ProblemViewHolder {

        public TextView textView;
        public ImageView myImageView;
        public CardView card;

        public ProblemViewHolder(View itemView) {
            textView = itemView.findViewById(R.id.problemText);
            myImageView = itemView.findViewById(R.id.problemImage);
            card = itemView.findViewById(R.id.card_view);
        }
    }

    public ProblemAdapter() {
        this.problems = new ArrayList<>();
        colorHandler = new ColorHandlerForAndroidMaterialDesign();
    }

    public void setProblems(List<Problem> problems) {
        this.problems.clear();
        this.problems.addAll(problems);
        Problem panicProblem = new Problem(0,"Help", R.drawable.phone,"");
        this.problems.add(panicProblem);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProblemViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.problem, parent, false);
            holder = new ProblemViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ProblemViewHolder) convertView.getTag();
        }

        Problem problem = getItem(position);

        if(position == this.problems.size()-1){
            holder.textView.setText(problem.getProbleem());
            holder.textView.setBackgroundColor(colorHandler.getPanicColor().getColorDark());
            holder.myImageView.setImageResource(problem.getImage());
            holder.card.setCardBackgroundColor(colorHandler.getPanicColor().getColorLight());
        } else {
            holder.textView.setText(problem.getProbleem());
            holder.textView.setBackgroundColor(colorHandler.giveColorForItemOnPosition(position).getColorDark());
            holder.myImageView.setImageResource(problem.getImage());
            holder.card.setCardBackgroundColor(colorHandler.giveColorForItemOnPosition(position).getColorLight());
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return problems.size();
    }

    @Override
    public Problem getItem(int position) {
        return problems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Problem> getProblems() {
        return problems;
    }
}
