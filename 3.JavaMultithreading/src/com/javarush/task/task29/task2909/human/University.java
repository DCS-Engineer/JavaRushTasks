package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University{
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        for (int i = 0; i < students.size(); i++){
            if (students.get(i).getAverageGrade() == averageGrade)
                return students.get(i);
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student student = null;
        double maxAverageGrade = 0;
        for (int i = 0; i < students.size(); i++){
            if (i == 0) {
                maxAverageGrade = students.get(i).getAverageGrade();
                student = students.get(i);
            }
            else {
                if (students.get(i).getAverageGrade() > maxAverageGrade) {
                    maxAverageGrade = students.get(i).getAverageGrade();
                    student = students.get(i);
                }
            }
        }
        return student;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        Student student = null;
        double minAverageGrade = 0;
        for (int i = 0; i < students.size(); i++){
            if (i == 0) {
                minAverageGrade = students.get(i).getAverageGrade();
                student = students.get(i);
            }
            else {
                if (students.get(i).getAverageGrade() < minAverageGrade) {
                    minAverageGrade = students.get(i).getAverageGrade();
                    student = students.get(i);
                }
            }
        }
        return student;
    }

    public void expel(Student student){
        students.remove(student);
    }
}