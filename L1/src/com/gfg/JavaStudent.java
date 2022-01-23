package com.gfg;

import java.util.List;

public class JavaStudent extends Student implements Elegible{

    public JavaStudent(String name, int age, int rollNumber, List<String> subjects, String std) {
        super(name, age, rollNumber, subjects, std);
    }

    @Override
    void study() {
        System.out.println("Java Student is studying");
    }

    void study(String subject){
        System.out.println("Java Student is studying "+subject);
    }
}
