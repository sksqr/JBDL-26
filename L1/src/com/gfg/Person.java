package com.gfg;

import java.util.Date;
import java.util.List;

public class Person extends Human{
    static int personCount;
    private String name;
    private Date dob;
    int age;

    List<Address> address;

    public void walk(){
        System.out.println(name+" is walking");
    }

    public Person() {
        personCount++;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        personCount++;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
