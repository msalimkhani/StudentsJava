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
        int debug = 0;
        int index = 0;
        Field field = new Field();
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

        if (model.Students.get() == null)
        {
            System.out.println("if 58 -> model.Students.get()="+model.Students.get());
            model.Students.set(new Student[1][]);
            model.Students.get()[0] = new Student[1];
        }
        else
        {
            if (model.Students.get()[model.Students.get().length-1] == null)
            {
                System.out.println("if 65 -> model.Students.get()[model.Students.get().length-1]="+model.Students.get()[model.Students.get().length-1]);
                model.Students.get()[model.Students.get().length-1] = new Student[1];
            }
            else
            {
                model.Students.get()[model.getStLength() - 1] = Arrays.copyOf(model.Students.get()[model.getStLength() - 1], model.Students.get()[model.getStLength() - 1].length + 1);
            }
            for (int i = 0;i < model.getStLength(); i++)
            {
                var sts = model.Students.get()[i];
                assert sts != null;
                assert sts[0].StField.get() != null;
                assert sts[0].StField.get().fieldName.get() != null;
                if (sts[0].StField.get().fieldName.get().equalsIgnoreCase(field.fieldName.get()))
                {
                   isExistingField = true;
                   index = i;
                   break;
                }
                else {
                    break;
                }
            }
            System.out.println("isExistingField = " + isExistingField);
            if (!isExistingField)
            {
                System.out.println("isExistingField = " + isExistingField);
                var arr = model.Students.get();
                arr = Arrays.copyOf(arr, model.getStLength() + 1);
                model.Students.set(arr);
            }
        }
        student.StField.set(field);
        if (isExistingField)
        {
            model.Students.get()[index][model.Students.get()[index].length - 1] = student;
        }
        else
        {
            System.out.println("line 105 entered!");
            if (model.Students.get()[model.getStLength()-1] == null)
                model.Students.get()[model.getStLength()-1] = new Student[1];
            model.Students.get()[model.getStLength()-1][model.Students.get()[model.getStLength() - 1].length-1] = student;
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
                            "4.Exit."};
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
