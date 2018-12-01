package com.clearbases.threadexperiments.basic;

/**
 * Classes that extends from {@link Thread}
 */
public class CreateThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);
                System.out.println("Running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new CreateThread().start();
    }

}
