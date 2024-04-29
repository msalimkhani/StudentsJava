package com.salimkhani.students.models;

import com.salimkhani.tools.Property;

public class Student {
    public Property<Integer> StudentId;
    public Property<Long> StudentCode;
    public Property<String> FullName;
    public Property<Field> StField;
    public Property<Double> Grade;
    public Student()
    {
        StudentId = new Property<>();
        StudentCode = new Property<>();
        StField = new Property<>();
        FullName = new Property<>();
        Grade = new Property<>();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Student{");
        sb.append("StudentId=").append(StudentId.get());
        sb.append(", StudentCode=").append(StudentCode.get());
        sb.append(", FullName=").append(FullName.get());
        sb.append(", Field=").append(StField.get());
        sb.append(", Grade=").append(Grade.get());
        sb.append('}');
        return sb.toString();
    }
}
