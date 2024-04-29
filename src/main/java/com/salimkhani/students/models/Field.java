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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Field{");
        sb.append("fieldIndex=").append(fieldIndex.get());
        sb.append(", fieldName=").append(fieldName.get());
        sb.append('}');
        return sb.toString();
    }

    public static void search(String fieldName, Field[] fields, Field out) throws InstanceNotFoundException {
        if (fields != null)
        {
            for (int i = 0; i < fields.length; i++) {
                if (fields[i] != null)
                {
                    if(fields[i].fieldName.get().equals(fieldName))
                    {
                        out =  fields[i];
                        break;
                    }
                }
            }
        }
    }
}
