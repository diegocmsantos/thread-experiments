package com.clearbases.threadexperiments.synchronization;

/**
 * Here we'll see that two threads accessing the same increment instance method.
 * count++ seems to be a single atomic operation but it doesn't.
 * count++ is equal to count = count + 1
 * So, above we have two operations:
 * read count variable then plus 1 to it.
 * Running this method without synchronization leads to a possible race condition.
 */
public class WrongAtomicOperation {

    private int count = 0;

    private void increment() {
        count++;
    }

    public void doWork() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

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
        WrongAtomicOperation wrongAtomicOperation = new WrongAtomicOperation();
        wrongAtomicOperation.doWork();
    }

}
