package com.gfg;

import java.util.ArrayList;
import java.util.List;

public class EncapsulationDemo {
    public static void main(String[] args) {

        GFGCourse gfgCourse = new GFGCourse();
        gfgCourse.addCourse("java");
        System.out.println(gfgCourse);

        Address address = new Address();
        address.city="Delhi";
        address.pincode="110222";
    }
}
