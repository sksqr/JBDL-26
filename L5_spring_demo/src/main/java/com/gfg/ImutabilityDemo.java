package com.gfg;

import java.util.Date;

public class ImutabilityDemo {

    public static void main(String[] args) {
        String a = "GFG";
        String b =a.toLowerCase();
        System.out.println(a);
        System.out.println(b);
        Date date = new Date();
        String name="shashi";
        StudentImutable s1 = new StudentImutable(1,name,date);
        System.out.println(s1);
        date.setMonth(8);
        s1.getDob().setMonth(8);
        System.out.println(s1);


    }
}
