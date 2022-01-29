package com.gfg.colections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsArraysDemo {

    public static void main(String[] args) {
//        Arrays

        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(1);
        list.add(6);
        list.add(6);
        list.add(10);
        System.out.println(list);
        Collections.sort(list);

        System.out.println(list);
        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));
        System.out.println(Collections.frequency(list,6));

    }


}
