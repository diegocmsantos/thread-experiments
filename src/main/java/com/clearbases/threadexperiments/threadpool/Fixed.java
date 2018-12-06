package com.clearbases.threadexperiments.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Process implements Runnable {

    private final int id;

    public Process(final int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Thread " + id + " starting...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + id + " completes");
    }
}

public class Fixed {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Process(i));
        }

        System.out.println("All process submitted");

        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("All process completed");

    }

}
