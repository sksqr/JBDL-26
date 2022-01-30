package com.gfg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class Printer{
   synchronized public void print(String content, int line){
        for(int i=0; i<line; i++){
            System.out.println(content +" "+Thread.currentThread().getName());
        }
    }
}
class VisitorCounter{
    private AtomicInteger total=new AtomicInteger(0);
   public void incrementVisitor(){
        total.incrementAndGet();
        /*
        CPU read value
        increment value
        write value
         */
    }

    public int getTotal() {
        return total.get();
    }
}

public class PrinterDemo {

    public static void main(String[] args) throws InterruptedException {
        String data1 = "JBDL-26 is going on";
        String data2 = "SDL-26 is going on";
        String data3 = "C++ is going on";
        String data4 = "JS is going on";
        Printer printer = new Printer();

        VisitorCounter visitorCounter = new VisitorCounter();


        Thread t1 = new Thread(() ->{
            for(int i=0; i<1000; i++){
                visitorCounter.incrementVisitor();
            }
        });
        Thread t2 = new Thread(() ->{
            for(int i=0; i<1000; i++){
                visitorCounter.incrementVisitor();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(visitorCounter.getTotal());


//
//        Thread t1 = new Thread(() ->{
//           printer.print(data1,10);
//        });
//        Thread t2 = new Thread(() ->{
//            printer.print(data2,10);
//        });
//
//        t1.start();
//        t2.start();





//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        executorService.submit(() ->{
//           printer.print(data1,10);
//        });
//        executorService.submit(() ->{
//            printer.print(data2,10);
//        });
//        executorService.submit(() ->{
//            printer.print(data3,10);
//        });
//        executorService.submit(() ->{
//            printer.print(data4,10);
//        });
//
//        executorService.shutdown();
    }
}
