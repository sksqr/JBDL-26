package com.gfg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoServer {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println("testing server");
        executorService.submit(()->{System.out.println("Task processing");});
        executorService.shutdown();
    }
}
/*

while(true){

// Accept http request
// executorService.submit(httRequest);

}
 */
