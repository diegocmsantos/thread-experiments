package com.clearbases.threadexperiments.synchronization.multiples_locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Here we have two operations (two methods).
 * First these methods needs to be synchronized because add list method is not an atomic operation.
 * Running the process method in a single-threaded app will take about 2 seconds.
 * We would want to running this in a multi-threaded app to take advantage of multi-core system and
 * do these operations faster.
 *
 * If we synchronize both stageOne and stageTwo methods we solve the race condition problem but
 * surprisingly it takes longer to process. About 4 minutes.
 *
 * Why is that?
 * When a thread enters a synchronized method it takes a lock and others threads have to wait the
 * first one release the lock to do its operations.
 *
 * We can solve this using more than one lock.
 * Any object can actuate as a lock in Java.
 *
 */
public class Worker {

    private final Random random = new Random();
    private final List<Integer> list1 = new ArrayList<>();
    private final List<Integer> list2 = new ArrayList<>();
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private void stageOne() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
                list1.add(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void stageTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
                list2.add(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    void main() {
        System.out.println("Starting...");

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(this::process);
        Thread t2 = new Thread(this::process);

        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("List1 size: " + list1.size() + ", List2 size: " + list2.size());
    }

}
