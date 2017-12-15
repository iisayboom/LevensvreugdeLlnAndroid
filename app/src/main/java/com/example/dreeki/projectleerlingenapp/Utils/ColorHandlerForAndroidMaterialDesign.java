package com.example.dreeki.projectleerlingenapp.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dreeki on 28/11/17.
 */

public class ColorHandlerForAndroidMaterialDesign {
    private List<MaterialDesignColor> colors;
    private MaterialDesignColor panicColor;

    public ColorHandlerForAndroidMaterialDesign(){
        colors = new ArrayList<>();
        panicColor = MaterialDesignColor.PANIC;

        for(MaterialDesignColor c: MaterialDesignColor.values()){
            if(c != MaterialDesignColor.PANIC){
                colors.add(c);
            }
        }
    }

    public MaterialDesignColor getPanicColor() {
        return panicColor;
    }

    public MaterialDesignColor giveColorForItemOnPosition(int position){
        return colors.get(position%colors.size());
    }
}
