package com.salimkhani.students.models;

import com.salimkhani.tools.Property;

public class StudentModel {
    public Property<Student[][]>  Students;
    public Property<Field[]> Fields;

    public StudentModel()
    {
        Students = new Property<>();
        Fields = new Property<>();
    }
}
