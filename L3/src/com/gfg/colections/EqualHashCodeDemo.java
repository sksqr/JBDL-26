package com.gfg.colections;

import java.util.HashSet;
import java.util.Set;

public class EqualHashCodeDemo {
    public static void main(String[] args) {
        Set<Employee> set = new HashSet<>();
        set.add(new Employee("shashi",7));
        set.add(new Employee("ravi",7));
        set.add(new Employee("ravi",7));

        System.out.println(set.size());

        System.out.println(set);
    }
}
