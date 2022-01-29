package com.gfg.threads;

class MyThread extends Thread{

    public MyThread() {
    }

    public MyThread(Runnable runnable) {
        super(runnable);
    }

    @Override
    public void run(){
        for(int i=0; i<10; i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}

class MyTask implements Runnable{
    @Override
    public void run(){
        for(int i=0; i<10; i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}

public class ThreadIntro {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        MyThread thread1 = new MyThread();
        thread1.setName("thread-1");
        MyThread thread2 = new MyThread();
        thread2.setName("thread-2");
        thread1.start();
        thread2.start();
//        MyTask task = new MyTask();
//        Thread thread3 = new Thread(task);
        Thread thread3 = new MyThread(()->{
            for (int i=0; i<10; i++){
                System.out.println(Thread.currentThread().getName()+": runnable task"+i);
            }
        });
        thread3.setName("My task thread");
        thread3.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Done");
    }
}
