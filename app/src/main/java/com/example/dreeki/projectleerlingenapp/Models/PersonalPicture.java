package com.example.dreeki.projectleerlingenapp.Models;

/**
 * Created by dreeki on 26/10/17.
 */

public class PersonalPicture {
    private int pictureLinkId;

    public PersonalPicture(int pictureLinkId){
        this.pictureLinkId = pictureLinkId;
    }

    public PersonalPicture(){ this.pictureLinkId = -1;}

    public int getPictureLinkId() {
        return pictureLinkId;
    }

    public void setPictureLinkId(int pictureLinkId) {
        this.pictureLinkId = pictureLinkId;
    }
}
