package com.gfg.colections;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueDemo {
    public static void main(String[] args) {
        /*
        MessageQueue
        TaskQueue
        RequestQueue
        FIFO
         */

        Queue queue = new LinkedList();
        queue.offer("book1");
        queue.offer("book2");
        queue.offer("book3");

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.size());


        Queue<Employee> pq = new PriorityQueue<>(new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e2.exp - e1.exp;
            }
        });
        pq.offer(new Employee("shashi",7));
        pq.offer(new Employee("Ravi",10));
        pq.offer(new Employee("Rani",5));

        System.out.println("promoting "+pq.poll());
        System.out.println("promoting "+pq.poll());


    }
}
