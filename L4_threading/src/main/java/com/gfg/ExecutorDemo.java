package com.gfg;

import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args) {

        int  corePoolSize  =    5;
        int  maxPoolSize   =   10;
        long keepAliveTime = 5000;

        ExecutorService threadPoolExecutor =
                new ThreadPoolExecutor(
                        corePoolSize,
                        maxPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>()
                );





        ExecutorService executorService = Executors.newFixedThreadPool(2);


//
//        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Running in "+Thread.currentThread().getName());
            }
        };
        executorService.submit(task);
        executorService.submit(task);
        executorService.submit(task);
        executorService.submit(task);
        executorService.submit(task);
        executorService.submit(()->{
            for(int i=0; i<10; i++){
                System.out.println("value:"+i+" "+Thread.currentThread().getName());
            }
        });
        executorService.shutdown();
        executorService.submit(task);

    }
}
