package com.gfg;

import java.util.ArrayList;
import java.util.List;

public class ErrorDemo {
    public static void main(String[] args) {
        System.out.println("This program will generate Error");
//         uncomment following program for java.lang.OutOfMemoryError: Java heap space
//        methodToGenerateOutOfMemoryError();
//        uncomment following program for java.lang.StackOverflowError
//        methodToGenerateStackOverflowError(1);

    }

    static void  methodToGenerateOutOfMemoryError(){
        List<Object> objects = new ArrayList<>();
        while (true){
            objects.add(new Object());
        }
    }

    static void  methodToGenerateStackOverflowError(int n){
        if(n%100 == 0){
            System.out.println("Executing "+n+"th time.");
        }
        methodToGenerateStackOverflowError(n+1);
    }
}
