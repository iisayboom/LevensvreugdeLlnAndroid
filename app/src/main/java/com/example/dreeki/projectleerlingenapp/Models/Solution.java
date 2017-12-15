package com.example.dreeki.projectleerlingenapp.Models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * Created by dreeki on 26/10/17.
 */

@Entity
public class Solution {
    @Id
    public long id;
    private int image;
    private String oplossing;
    public ToMany<Problem> problemen;

    public Solution() {

    }

    public Solution(long id, int image, String oplossing) {
        this.id = id;
        this.image = image;
        this.oplossing = oplossing;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getOplossing() {
        return oplossing;
    }

    public void setOplossing(String oplossing) {
        this.oplossing = oplossing;
    }
}
