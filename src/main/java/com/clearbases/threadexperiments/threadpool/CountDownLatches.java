package com.clearbases.threadexperiments.threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ProcessCountDown implements Runnable {

    private final CountDownLatch countDownLatch;

    public ProcessCountDown(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        System.out.println("Started");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countDownLatch.countDown();
        System.out.println("Finished");

    }
}

public class CountDownLatches {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executorService.submit(new ProcessCountDown(countDownLatch));
        }

        executorService.shutdown();
        countDownLatch.await();

        System.out.println("Completed");

    }

}
