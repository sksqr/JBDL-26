package com.gfg.colections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayLimitationsDemo {


    public static void main(String[] args) {

        String[] studentsArr = new String[2];
        studentsArr[0]="Ravi";
        studentsArr[1]="Rahul";
//        studentsArr= new String[3];
        String[] temp = new String[3];
        for(int i=0; i<2; i++){
            temp[i]=studentsArr[i];
        }
        studentsArr = temp;
        studentsArr[2]="Prabhu";

//        =================

        List<String> list = new ArrayList<>();
//        ArrayList<String> list = new ArrayList<>();
        list.add("Ravi");
        list.add("Rahul");
        list.add("Prabhu");
        for(String name: list){
            System.out.println(name);
        }
    }

}
