package com.gfg;

public class ObjectsAndClass {
    public static void main(String[] args) {
        Person p1 = new Person("shashi");

        p1.age=28;
        System.out.println(p1);
        Person p2 = new Person("pritam");
        p2.age=28;
        System.out.println(p2);
        Person p3 = new Person("Ravi",24);

        System.out.println(p2);
        System.out.println(p3);
        p3.walk();
//        System.out.println(Person.personCount);
        System.out.println(p3.personCount);
    }
}
