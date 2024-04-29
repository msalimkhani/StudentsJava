package com.salimkhani.students.main;

import com.salimkhani.students.models.Field;
import com.salimkhani.students.models.Student;
import com.salimkhani.tools.Property;

import javax.management.InstanceNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static boolean SIGEXIT = false;
    public static void main(String[] args) throws InstanceNotFoundException {
        Student[][] students = null;
        Field[] fields = null;
        Init(students, fields);
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
    private static Student[][] addStudent(Student[][] students, Field[] fields) throws InstanceNotFoundException {
        int index = 0;
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
        if (fields == null)
        {
            fields = new Field[1];
        }
        else
        {
            fields = Arrays.copyOf(fields, fields.length+1);
        }

        if (students == null)
        {
            students = new Student[1][];
            students[0] = new Student[1];
        }
        else
        {
            if (students[students.length-1] == null)
            {
                students[students.length-1] = new Student[1];
            }
            else
            {
                sf = new Field();
                if (fields != null)
                {
                    Field.search(field.fieldName.get(), fields, sf);
                    if(sf.fieldName.get()!= null && sf.fieldIndex.get()!= null)
                    {
                        //field = sf;
                        index = sf.fieldIndex.get();
                        field.fieldIndex.set(index);
                        students[index] = Arrays.copyOf(students[index], students[index].length+1);
                        isExistingField = true;
                    }
                    else {
                        index = students.length-1;
                        field.fieldIndex.set(index);
                        fields[fields.length-1] = field;
                        students[index] = Arrays.copyOf(students[index], students[index].length+1);
                        isExistingField = false;
                    }
                }
                else
                {
                    System.out.println("Error: fields is null!");
                    System.exit(-1);
                }
            }
        }
        if (isExistingField == false)
            index = students.length-1;
        field.fieldIndex.set(index);
        student.StField.set(field);
        if (students != null && students[students.length-1] != null)
        {
            if (isExistingField == true)
            {
                students[sf.fieldIndex.get()][students[sf.fieldIndex.get()].length-1] = student;
            }
            else
            {
                fields[fields.length-1] = field;
                students[students.length-1][students[students.length-1].length-1] = student;
            }
        }
        return students;
    }



    private static void Init(Student[][] students, Field[] fields) throws InstanceNotFoundException {
        System.out.printf("Students V %d.%d\n", getMajorVer(), getMinorVer());
        ShowMenu(students, fields);
        sayGoodBye();
    }
    private static void sayGoodBye()
    {
        System.out.println("GoodBye!");
    }
    private static void ShowMenu(Student[][] students, Field[] fields) throws InstanceNotFoundException {
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
                    ShowMenu(students, fields);
                    break;
                case 2:
                    ShowStudents(students);
                    break;
                case 3:
                    students = addStudent(students, fields);
                    ShowMenu(students, fields);
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
