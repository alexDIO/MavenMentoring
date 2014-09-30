package olomakovskyi.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by olomakovskyi on 9/18/2014.
 */
public class AtomicCounter {
    private static int counter;
    private static AtomicInteger atomicCounter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException {
        runThreads(MyThread.class);
        System.out.println(counter);
        runThreads(MyAtomicThread.class);
        System.out.println(atomicCounter);
    }

    public static class MyThread implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 50000000; i++){
                increment();
            }
        }

        public void increment(){
            synchronized (this.getClass()) {
                counter++;
            }
        }
    }

    public static class MyAtomicThread implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 50000000; i++){
                atomicCounter.incrementAndGet();
            }
        }
    }

    public static void runThreads(Class<? extends Runnable> inClass) throws InterruptedException, IllegalAccessException, InstantiationException {
        List<Thread> threadList = new LinkedList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i<2; i++){
            Thread thread = new Thread(inClass.newInstance());
            threadList.add(thread);
            thread.start();
        }

        for (Thread thread : threadList){
            thread.join();
        }


        System.out.println(System.currentTimeMillis() - startTime);

    }
}
