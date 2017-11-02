package com.example.dreeki.projectleerlingenapp.Models;

/**
 * Created by dreeki on 26/10/17.
 */

public class PersonalPicture {
    private String pictureLink;

    public PersonalPicture(String link){
        pictureLink = link;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
}
