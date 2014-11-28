package com.vl.training.sample;
import java.util.Scanner;
public final class StudentData {
    private StudentData() {
    }
    private Course[] studentdata = {};
    public static void main(final String[] args) {
        int NUMBER_OF_STUDENTS = 0, NUMBER_OF_SUBJECTS = 0, i = 0;
        StudentData sd = new StudentData();
        NUMBER_OF_STUDENTS = Integer.parseInt(args[0]);
        NUMBER_OF_SUBJECTS = Integer.parseInt(args[1]);
        Course.readMe(i, NUMBER_OF_STUDENTS, NUMBER_OF_SUBJECTS);
    }
}
final class Course {
    private Course() {
    }
    private static Student[] allstudents;
    public static Course readMe(int i, final int NUMBER_OF_STUDENTS, final int NUMBER_OF_SUBJECTS) {
        Course c = new Course();
        c.allstudents = new Student[NUMBER_OF_STUDENTS];
        while (i < allstudents.length) {
            System.out.println("enter the details for the student" + i);
            c.allstudents[i] = Student.readMe(NUMBER_OF_SUBJECTS);
            i++;
        }
        System.out.println("name     subject     marks");
        for (int j = 0; j < NUMBER_OF_STUDENTS; j++) {
            System.out.print(c.allstudents[j].name);
            for (int k = 0; k < NUMBER_OF_SUBJECTS; k++) {
                if (NUMBER_OF_SUBJECTS >= 1) {
                    System.out.println("\t" + c.allstudents[j].allmarks[k].sub + "\t" + c.allstudents[j].allmarks[k].marks + "\t");
                    // + c.allstudents[j].total1);
                }
            }
        }
        int highest = 0;
        String name = "";
        for (int p = 0; p < NUMBER_OF_SUBJECTS; p++) {
            if (c.allstudents[p].total1 > highest) {
                highest = c.allstudents[p].total1;
                name = c.allstudents[p].name;
            }
        }
        System.out.println("student who got high total marks is" + name);
        String indname = " ";
        for (int q = 0; q < NUMBER_OF_SUBJECTS; q++) {
            int highestmarks = 0;
            for (int r = 0; r < NUMBER_OF_STUDENTS; r++) {
                if (c.allstudents[r].allmarks[q].marks > highestmarks) {
                    highestmarks = c.allstudents[r].allmarks[q].marks;
                    indname = c.allstudents[r].name;
                }
            }
            System.out.println(indname + " got higest marks of " + highestmarks + "  in " + c.allstudents[q].allmarks[q].sub);
        }
        return c;
    }
}
class Student {
    public String name;
    public Marks[] allmarks;
    public int  total1;
    public static Student readMe(final int NUMBER_OF_SUBJECTS) {
        int total = 0;
        Student s = new Student();
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the student name");
        s.name = sc.next();
        s.allmarks = new Marks[NUMBER_OF_SUBJECTS];
        for (int i = 0; i < NUMBER_OF_SUBJECTS; i++) {
            System.out.println("enter the marks details here");
            s.allmarks[i] = Marks.readMe();
            total = total + s.allmarks[i].marks;
        }
        s.total1 = total;
        return s;
    }
}
class Marks {
    public String sub;
    public int marks;
    public static Marks readMe() {
        Marks m = new Marks();
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the subject name");
        m.sub = sc.next();
        System.out.println("enter the " + m.sub + "marks");
        m.marks = sc.nextInt();
        return m;
    }
}
