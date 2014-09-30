package olomakovskyi.concurrency;

import java.io.*;

/**
 * Created by olomakovskyi on 9/4/2014.
 */
public class Counter {

    protected static volatile int Counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread firstThread = new Thread(new MyThread3());
//        Thread secondThread = new Thread(new MyInterruptedThread());
//        firstThread.setDaemon(true);
        firstThread.start();


//        secondThread.start();


//        firstThread.join();
//        System.out.println(Counter);
//
//        secondThread.join();
//        System.out.println(Counter);
        Thread.sleep(5000);
        System.out.println("Lets stop threads");


        System.out.println(firstThread.isInterrupted());
        firstThread.interrupt();
//        secondThread.interrupt();

        System.out.println(firstThread.isInterrupted());

        Thread.sleep(5000);
        System.out.println("Main thread finished");
        System.out.println(firstThread.isInterrupted());
    }

    private static class MyThread implements Runnable {
        private final Object monitor;

        private MyThread(Object monitor) {
            this.monitor = monitor;
        }

        public void run() {
            for(int i = 0; i < 5000000; i++) {
                increment(monitor);
            }
        }

        public static void increment(Object monitor){
            synchronized (monitor) {
                Counter++;
            }
        }
    }

    private static class MyThread2 implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 500000; i++){
                increment();
            }
        }

        public synchronized void increment(){
            Counter++;
        }
    }

    private static class MyThread3 implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 500000; i++){
                increment();
            }
        }

        public void increment(){
            synchronized (this.getClass()) {
                Counter++;
            }
        }
    }

    private static class MyThread4 implements Runnable{

        @Override
        public void run() {
            for (long i = 0; i < 500000000; i++){
                Counter++;
            }
        }

    }

    private static class DeadlockThread implements Runnable {
        private final Object monitor1;
        private final Object monitor2;

        private DeadlockThread(Object monitor1, Object monitor2) {
            this.monitor1 = monitor1;
            this.monitor2 = monitor2;
        }

        public void run() {
            for(int i = 0; i < 5000000; i++) {
                increment(monitor1, monitor2);
            }
        }

        public static void increment(Object monitor1, Object monitor2){
            synchronized (monitor1) {
                synchronized (monitor2) {
                    Counter++;
                }
            }
        }
    }


}

class MyInterruptedThread extends Thread{

    @Override
    public void run() {


        System.out.println("*" + isInterrupted());
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
