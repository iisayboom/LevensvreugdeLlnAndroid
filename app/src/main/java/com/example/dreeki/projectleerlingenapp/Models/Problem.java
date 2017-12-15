package com.example.dreeki.projectleerlingenapp.Models;

import com.example.dreeki.projectleerlingenapp.Utils.MaterialDesignColor;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;

/**
 * Created by dreeki on 26/10/17.
 */

@Entity
public class Problem {
    @Id
    public long id;
    private String probleem;
    private int image;
    private String solution;
    //@Convert(converter = MaterialDesignColorConverter.class, dbType = Integer.class)

    public Problem() {

    }

    public Problem(long id, String probleem, int image, String solution) {
        this.id = id;
        this.probleem = probleem;
        this.image = image;
        this.solution = solution;
    }

    public long getId() {
        return id;
    }

    public String getProbleem() {
        return probleem;
    }

    public void setProbleem(String probleem) {
        this.probleem = probleem;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
