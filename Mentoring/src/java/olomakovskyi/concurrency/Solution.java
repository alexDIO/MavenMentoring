package olomakovskyi.concurrency;

import java.io.*;

/**
 * Created by olomakovskyi on 9/18/2014.
 */
public class Solution {
    public static void main(String[] args) {
        Thread thr = new Thread2();

        System.out.println(System.currentTimeMillis() % 100000L);
        thr.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() % 100000L);
        thr.interrupt();

        System.out.println("Exitting...");
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread1 started...");
        while (!isInterrupted()) {
        }
        System.out.println("Thread1 finished.");
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        while (!this.isInterrupted()){
        for (long i = System.currentTimeMillis(); System.currentTimeMillis() - i< 1000;);

        System.out.println("*" + isInterrupted());

        File counter = new File("counter.txt");

        if (!counter.exists()) {
            try {
                counter.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("counter.txt", true)));
            writer.write(String.valueOf(isInterrupted()));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        System.out.println("While finished");
    }
}

