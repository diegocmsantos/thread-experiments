package com.clearbases.threadexperiments.synchronization;

import java.util.Scanner;

/**
 * This thread could ever stopping its running because
 * in some cases the running variable value can be cached by thread
 * and never changed.
 */
class ProcessorVolatile extends Thread {

    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            try {
                System.out.println("Hello!!!");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class VolatileVariable {

    public static void main(String[] args) {
        ProcessorVolatile processor = new ProcessorVolatile();

        processor.start();

        System.out.println("Press return to stop....");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor.shutdown();
    }

}
