package com.clearbases.threadexperiments.synchronization;

import java.util.Scanner;

/**
 * Different from {@link RegularVariable} class this class will guarantee that
 * thread will stop looking to running variable using volatile keyword
 */
class Processor extends Thread {

    private boolean running = true;

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

public class RegularVariable {

    public static void main(String[] args) {
        Processor processor = new Processor();

        processor.start();

        System.out.println("Press return to stop....");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor.shutdown();
    }

}
