package com.example.dreeki.projectleerlingenapp.Models;

/**
 * Created by dreeki on 26/10/17.
 */

public class Problem {
    private String probleem;
    private int image;

    public Problem(String probleem, int image) {
        this.probleem = probleem;
        this.image = image;
    }

    public String getProbleem() {
        return probleem;
    }

    public void setProbleem(String probleem) {
        this.probleem = probleem;
    }
}
