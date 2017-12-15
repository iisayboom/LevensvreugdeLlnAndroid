package com.example.dreeki.projectleerlingenapp.Utils;

import io.objectbox.converter.PropertyConverter;

/**
 * Created by nielsdebruyne on 11/12/2017.
 */

class MaterialDesignColorConverter implements PropertyConverter<MaterialDesignColor, Integer> {

    @Override
    public MaterialDesignColor convertToEntityProperty(Integer databaseValue) {
        if(databaseValue == null) {
            return null;
        }

        for(MaterialDesignColor color : MaterialDesignColor.values()) {

        }
        return null;
    }

    @Override
    public Integer convertToDatabaseValue(MaterialDesignColor entityProperty) {
        return null;
    }
}
