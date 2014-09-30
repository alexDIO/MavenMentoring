package olomakovskyi.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by olomakovskyi on 9/18/2014.
 */
public class Executor {
    private static final ExecutorService service = Executors.newCachedThreadPool();
    private static final ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<String>> futureList = new LinkedList<>();

        for (int i = 0; i<10; i++){
            futureList.add(scheduledService.schedule(new MyCallable(),i*3,TimeUnit.SECONDS));
        }

        for (Future<String> future : futureList){
            System.out.println("**" + future.get());
        }

        scheduledService.shutdown();

    }


}

class MyCallable implements Callable<String>{
    private static int threadsCounter;
    private int myCount;

    public MyCallable(){
        myCount = threadsCounter++;
    }
    @Override
    public String call() throws Exception {
        Random rn = new Random();

        int wait = rn.nextInt(5000);

        long startTime = System.currentTimeMillis();

        System.out.println(String.format("Thread number %s started at %s",myCount,startTime));

        while (System.currentTimeMillis() < startTime + wait){

        }

        System.out.println(String.format("Thread number %s finished at %s", myCount, System.currentTimeMillis()));

        return String.format("Thread number %s slept %s seconds",myCount,wait);
    }
}