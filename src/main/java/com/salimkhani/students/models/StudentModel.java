package com.salimkhani.students.models;

import com.salimkhani.tools.Property;

public class StudentModel {
    public Property<Student[][]>  Students;

    public StudentModel()
    {
        Students = new Property<>();
    }
    public Integer getStLength()
    {
        if (Students.get() != null)
            return Students.get().length;
        return null;
    }
}
