package com.gfg.colections;

import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {
        /*
        parenthesis matching
        Reverse String or Reverse linkedList
         */

        Stack<String> stack = new Stack<>();
        stack.push("book1");
        stack.push("book2");
        stack.push("book3");

        System.out.println(stack.peek());
        System.out.println(stack.peek());

        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.peek());



    }
}
