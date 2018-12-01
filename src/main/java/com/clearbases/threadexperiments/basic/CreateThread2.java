package com.clearbases.threadexperiments.basic;

public class CreateThread2 implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Running...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new CreateThread2()).start();
    }
}
