package com.gfg;

import java.util.Scanner;

public class AbstractDemo {

    public static void main(String[] args) {

//        Car car = new Car(null,null) ; We can not instantiate abstract class.

//        CarInIndia car = new CarInIndia(null,null);
//
//        if(car instanceof EnvironmentalContact){
//            System.out.println("Pass");
//        }

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student student;
        if(n==1){
            student = new Student(1);
        }
        else{
            student = new JavaStudent("shashi",28,12,null,"B.tech");
        }
        student.study();
        
//
//        Student
//        if(student instanceof Elegible){
//            System.out.println("ShortListed");
//        }


//        student.study("OS");

    }
}
