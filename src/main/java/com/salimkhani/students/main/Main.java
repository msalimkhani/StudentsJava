package com.salimkhani.students.main;

import com.salimkhani.students.models.Field;
import com.salimkhani.students.models.Student;
import com.salimkhani.students.models.StudentModel;
import com.salimkhani.tools.Property;

import javax.management.InstanceNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static boolean SIGEXIT = false;
    public static void main(String[] args) throws InstanceNotFoundException {
        StudentModel model = new StudentModel();
        Init(model);
    }
    private static void ShowFields(Field[] fields)
    {
        System.out.println("===================================================");
        if (null == fields)
        {
            System.out.println("List is Empty!");
        }
        else {
            for (int i = 0; i < fields.length; i++) {
                if (fields[i] != null)
                {
                    System.out.println(fields[i]);
                }
                else {
                    System.out.println("fields["+i+"] = null!");
                }
            }
        }
    }
    private static void ShowStudents(Student[][] students)
    {
        System.out.println("===================================================");
        if (null == students)
        {
            System.out.println("List is Empty!");
        }
        else {
            for (int i = 0; i < students.length; i++) {
                if (students[i] != null)
                {
                    for (int j = 0; j < students[i].length; j++) {
                        System.out.println(students[i][j]);
                    }
                }
                else
                {
                    System.out.printf("students[%d] == null\n",i);
                }
            }
        }

    }
    private static StudentModel addStudent(StudentModel model) throws InstanceNotFoundException {
        int index = 0;
        int debug = 0;
        Field field = new Field();
        Field sf = null;
        boolean isExistingField = false;
        Student student = new Student();
        System.out.print("Enter Student ID: ");
        student.StudentId.set(readInputInt());
        System.out.print("Enter Student Code: ");
        student.StudentCode.set(readInputLong());
        System.out.print("Enter Student FullName: ");
        student.FullName.set(readInputString());
        System.out.print("Enter Student Grade: ");
        student.Grade.set(readInputDouble());
        System.out.print("Enter Student Field: ");
        field.fieldName.set(readInputString());
        if (model.Fields.get() == null)
        {
            model.Fields.set(new Field[1]);
            debug += 1;
        }
        else
        {
            model.Fields.set(Arrays.copyOf(model.Fields.get(), model.Fields.get().length+1));
            debug += 2;
        }

        if (model.Students.get() == null)
        {
            model.Students.set(new Student[1][]);
            model.Students.get()[0] = new Student[1];
            debug += 3;
        }
        else
        {
            debug += 4;
            if (model.Students.get()[model.Students.get().length-1] == null)
            {
                debug += 5;
                model.Students.get()[model.Students.get().length-1] = new Student[1];
            }
            else
            {
                debug += 6;
                sf = new Field();
                if (model.Fields.get() != null)
                {
                    debug += 7;
                    Field.search(field.fieldName.get(), model.Fields.get(), sf);
                    if(sf.fieldName.get()!= null && sf.fieldIndex.get()!= null)
                    {
                        debug += 8;
                        //field = sf;
                        index = sf.fieldIndex.get();
                        field.fieldIndex.set(index);
                        model.Students.get()[index] = Arrays.copyOf(model.Students.get()[index], model.Students.get()[index].length+1);
                        isExistingField = true;
                    }
                    else {
                        debug += 9;
                        index = model.Students.get().length-1;
                        field.fieldIndex.set(index);
                        model.Fields.get()[model.Fields.get().length-1] = field;
                        model.Students.get()[index] = Arrays.copyOf(model.Students.get()[index], model.Students.get()[index].length+1);
                        isExistingField = false;
                    }
                }
                else
                {
                    debug += 10;
                    System.out.println("Error: fields is null!");
                    System.exit(-1);
                }
            }
        }
        if (isExistingField == false)
            index = model.Students.get().length-1;
        field.fieldIndex.set(index);
        student.StField.set(field);
        if (model.Students.get() != null && model.Students.get()[model.Students.get().length-1] != null)
        {
            if (isExistingField == true)
            {
                model.Students.get()[sf.fieldIndex.get()][model.Students.get()[sf.fieldIndex.get()].length-1] = student;
            }
            else
            {
                model.Fields.get()[model.Fields.get().length-1] = field;
                model.Students.get()[model.Students.get().length-1][model.Students.get()[model.Students.get().length-1].length-1] = student;
            }
        }
        return model;
    }



    private static void Init(StudentModel model) throws InstanceNotFoundException {
        System.out.printf("Students V %d.%d\n", getMajorVer(), getMinorVer());
        ShowMenu(model);
        sayGoodBye();
    }
    private static void sayGoodBye()
    {
        System.out.println("GoodBye!");
    }
    private static void ShowMenu(StudentModel model) throws InstanceNotFoundException {
        String[] messages = {"1.Show Help(This) Page.",
                            "2.Show Students List.",
                            "3.Insert Student.",
                            "4.Exit.",
                            "5.ShowFields"};
        while (true)
        {
            if (SIGEXIT)
                break;
            for (String messsage : messages)
            {
                System.out.println(messsage);
            }
            int input = readInputInt();
            switch (input)
            {
                case 1:
                    ShowMenu(model);
                    break;
                case 2:
                    ShowStudents(model.Students.get());
                    break;
                case 3:
                    model = addStudent(model);
                    ShowMenu(model);
                case 4:
                    SIGEXIT = true;
                    break;
                case 5:
                    ShowFields(model.Fields.get());
                    ShowMenu(model);
                default:
                    throw new IllegalStateException("Unexpected value: " + input);
            }
        }

    }

    private static int getMajorVer() {
        return 1;
    }

    private static int getMinorVer() {
        return 0;
    }
    private static long readInputLong()
    {
        Scanner sc = new Scanner(System.in);
        return sc.nextLong();
    }
    private static int readInputInt()
    {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    private static String readInputString()
    {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    private static Double readInputDouble()
    {
        Scanner sc = new Scanner(System.in);
        return sc.nextDouble();
    }
}
