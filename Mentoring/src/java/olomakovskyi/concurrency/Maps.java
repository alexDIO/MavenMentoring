package olomakovskyi.concurrency;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by olomakovskyi on 9/12/2014.
 */
public class Maps {

    public static void main(String[] args){
//        testThread(new HashMap<String, String>());
        testThread(Collections.synchronizedMap(new HashMap<String, String>()));
        testThread(new ConcurrentHashMap<String, String>());
        testThread(Collections.unmodifiableMap(new HashMap<String, String>()));
    }

    private static void testThread(Map<String, String> inMap){
        Thread firstThread = new MyThreadHash(inMap);
        Thread secondThread = new MyThreadHash(inMap);


        long start = System.currentTimeMillis();

        firstThread.start();
        secondThread.start();


        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (firstThread.isAlive() || secondThread.isAlive());



        System.out.println(System.currentTimeMillis() - start);
        System.out.println(inMap.size());
    }

    static class MyThreadHash extends Thread{
        private Map<String, String> internalMap;

        public MyThreadHash(Map<String, String> map1){
            internalMap = map1;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++){
                internalMap.put(generateString(), generateString());
            }

        }
    }


        public static String generateString() {
                Random rn = new Random();
                return Integer.toHexString(rn.nextInt());
            }
}
