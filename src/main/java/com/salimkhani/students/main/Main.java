package com.salimkhani.students.main;

import com.salimkhani.students.models.Field;
import com.salimkhani.students.models.Student;
import com.salimkhani.tools.Property;

import javax.management.InstanceNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static boolean SIGEXIT = false;
    public static void main(String[] args) {
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
                for (int j = 0; j < students[i].length; j++) {
                    System.out.println(students[i][j]);
                }
            }
        }

    }
    private static Student[][] addStudent(Student[][] students, Field[] fields) throws InstanceNotFoundException {
        //Field field = null;
        Student student = new Student();
        student.StudentId.set(readInputInt());
        student.StudentCode.set(readInputLong());
        // TODO complete addStudent
        //if(null == students)
        //{
        //students = new Student[1][];
        //students[0] = new Student[1];
        //students[0][0] = student;
        //}
        //more...
        return students;
    }



    private static void Init(Student[][] students, Field[] fields)
    {
        System.out.printf("Students V %d.%d\n", getMajorVer(), getMinorVer());
        ShowMenu(students, fields);
        sayGoodBye();
    }
    private static void sayGoodBye()
    {
        System.out.println("GoodBye!");
    }
    private static void ShowMenu(Student[][] students, Field[] fields) {
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
    private static int readInputInt()
    {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    private static long readInputLong()
    {
        Scanner sc = new Scanner(System.in);
        return sc.nextLong();
    }
}
