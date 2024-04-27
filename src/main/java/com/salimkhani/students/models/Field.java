package com.salimkhani.students.models;

import com.salimkhani.tools.Property;

import javax.management.InstanceNotFoundException;

public class Field {
    public Property<Integer> fieldIndex;
    public Property<String> fieldName;

    public Field()
    {
        fieldIndex = new Property<>();
        fieldName = new Property<>();
    }
    public static Field search(String fieldName, Field[] fields) throws InstanceNotFoundException {
        for (int i = 0; i < fields.length; i++) {
            if(fields[i].fieldName.get().equals(fieldName))
                return fields[i];
        }
        throw new InstanceNotFoundException("Filed::search: Instance of "+ fieldName + " Not Found!");
    }
}
