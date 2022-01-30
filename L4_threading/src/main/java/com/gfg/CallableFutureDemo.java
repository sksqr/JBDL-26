package com.gfg;

import com.gfg.analyzer.KeywordAnalyserImplementation;
import com.gfg.analyzer.KeywordAnalyzerInterface;
import com.gfg.analyzer.KeywordAndFrequency;

import java.util.List;
import java.util.concurrent.*;

class MyTask implements Callable<List<KeywordAndFrequency>>{

    private KeywordAnalyzerInterface keywordAnalyzerInterface;

    @Override
    public List<KeywordAndFrequency> call() throws Exception {
        return keywordAnalyzerInterface.getAllKeywordWithFreq();
    }

    public MyTask(KeywordAnalyzerInterface keywordAnalyzerInterface) {
        this.keywordAnalyzerInterface = keywordAnalyzerInterface;
    }
}

public class CallableFutureDemo {
    public static void main(String[] args) {
        KeywordAnalyzerInterface keywordAnalyzerInterface = new KeywordAnalyserImplementation();
        keywordAnalyzerInterface.recordKeyword("Java");
        keywordAnalyzerInterface.recordKeyword("Java");
        keywordAnalyzerInterface.recordKeyword("C++");

        MyTask task = new MyTask(keywordAnalyzerInterface);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<List<KeywordAndFrequency>> listFuture = executorService.submit(task);
        executorService.shutdown();
        System.out.println(listFuture.isDone());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(listFuture.isDone());
//        if(listFuture.isDone()){
            try {
//                System.out.println(listFuture.get()); // get will block the main thread
                System.out.println(listFuture.get(200,TimeUnit.MILLISECONDS)); // get will block the main thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("SLA breach ");
                e.printStackTrace();
            }

//        }








    }
}
