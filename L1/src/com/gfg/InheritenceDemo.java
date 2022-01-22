package com.gfg;

import java.util.ArrayList;

public class InheritenceDemo {

    public static void main(String[] args) {
        Student student = new Student("Pritam",24, 1,new ArrayList<>(),"B.tech");
        System.out.println(student);
        student.walk();

        Person person = new Student("Pritam",24, 1,new ArrayList<>(),"B.tech");
        person.walk();

//        Student s1 = (Student) new Person("shashi");
//        s1.walk();



    }
}
