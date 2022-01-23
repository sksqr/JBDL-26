package com.gfg;

import java.util.List;

public class Student extends Person{
    private int rollNumber;
    private List<String> subjects;
    private String std;

    void study(){
        System.out.println("Student is studying");
    }


    public Student(String name, int age, int rollNumber, List<String> subjects, String std) {
        super(name, age);
        this.rollNumber = rollNumber;
        this.subjects = subjects;
        this.std = std;
    }

    public Student(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    @Override
    public String toString() {

        return "Student{" +
                "rollNumber=" + rollNumber +
                ", subjects=" + subjects +
                ", std='" + std + '\'' +
                '}'+super.toString();
    }

    @Override
    public void walk() {
        System.out.println("Student is walking");
    }
}
