package com.example.dreeki.projectleerlingenapp.Utils;

import android.graphics.Color;

/**
 * Created by dreeki on 28/11/17.
 */

public enum MaterialDesignColor {
    BLUE("#8EB0D7", "#5B94D4"),
    GREEN("#B8E986", "#98C46B"),
    YELLOW("#F6F1B1", "#F7EA49"),
    RED("#F5929E", "#FA5065"),
    GRAY("#DEDADA", "#5B94D4"),
    APPLEBLUESEAGREEN("#50E3C2", "#02C89C"),
    PANIC("#A60317", "#D0021B");

    private int colorLight;
    private int colorDark;

    MaterialDesignColor(String colorLight, String colorDark){
        this.colorLight = Color.parseColor(colorLight);
        this.colorDark = Color.parseColor(colorDark);
    }

    public int getColorDark() {
        return colorDark;
    }

    public int getColorLight() {
        return colorLight;
    }
}
