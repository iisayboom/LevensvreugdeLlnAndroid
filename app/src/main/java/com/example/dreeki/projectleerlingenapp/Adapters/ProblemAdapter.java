package com.example.dreeki.projectleerlingenapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreeki.projectleerlingenapp.Models.Problem;
import com.example.dreeki.projectleerlingenapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nielsdebruyne on 28/11/2017.
 */

public class ProblemAdapter extends BaseAdapter {
    private List<Problem> problems;

    private static class ProblemViewHolder {

        public TextView textView;
        public ImageView myImageView;

        public ProblemViewHolder(View itemView) {
            textView = itemView.findViewById(R.id.problemText);
            myImageView = itemView.findViewById(R.id.problemImage);
        }
    }

    public ProblemAdapter() {
        this.problems = new ArrayList<>();
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
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
        holder.textView.setText(problem.getProbleem());
        holder.myImageView.setImageResource(R.drawable.lekkeband);
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
}
