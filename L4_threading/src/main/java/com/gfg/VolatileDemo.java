package com.gfg;


class MyThread implements Runnable{
   volatile private boolean stop=false;
    public boolean isStop() {
        return stop;
    }
    public void setStop(boolean stop) {
        this.stop = stop;
    }
    @Override
    public void run() {
        while (stop== false){
            System.out.println("Running "+Thread.currentThread().getName());
        }
    }
}


public class VolatileDemo {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();

        Thread t1 = new Thread(myThread);
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.setStop(false);
        myThread.setStop(true);
        myThread.setStop(false);
        myThread.setStop(true);
        System.out.println("Stopped");

    }
}
