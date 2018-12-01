package com.clearbases.threadexperiments.synchronization;

/**
 * Using the keyword synchronized in our increment method every thread that enter it
 * will grab a lock and any other threads can access it before current thread holds
 * the lock.
 * So, the operation inside increment method will work as an atomic operation.
 */
public class AtomicOperation {

    private int count = 0;

    private synchronized void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    private void doWork() {

        Thread t1 = new Thread(this::increment);

        Thread t2 = new Thread(this::increment);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is : " + count);
    }

    public static void main(String[] args) {
        AtomicOperation wrongAtomicOperation = new AtomicOperation();
        wrongAtomicOperation.doWork();
    }

}
