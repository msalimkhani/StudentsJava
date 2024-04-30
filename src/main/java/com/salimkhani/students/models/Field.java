package com.salimkhani.students.models;

import com.salimkhani.tools.Property;

import javax.management.InstanceNotFoundException;

public class Field {
    public Property<String> fieldName;

    public Field()
    {
        fieldName = new Property<>();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Field{");
        sb.append(", fieldName=").append(fieldName.get());
        sb.append('}');
        return sb.toString();
    }


}
