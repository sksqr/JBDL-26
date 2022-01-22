package com.gfg;

public class TestGFG {

    public static void main(String[] args) {
        System.out.println("Hello!");
        String name = "GFG";
        printName(name,5);
        System.out.println("Done");
    }

    public  static  void printName(String name, int n){
        if(n==0){
            return;
        }
        System.out.println(name);
        printName(name,n-1);
    }



}
