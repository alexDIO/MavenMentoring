package olomakovskyi.collections.mapsandsets;

import java.util.*;

/**
 * Created by olomakovskyi on 8/26/2014.
 */
public class MapsAndSetsPerformance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.next());

        scanner.close();

        System.out.println("HashSet:");
        populateSet(new HashSet<String>(), N);
        System.out.println("TreeSet:");
        populateSet(new TreeSet<String>(), N);
        System.out.println("HashMap:");
        populateMap(new HashMap<String, String>(), N);
        System.out.println("TreeMap:");
        populateMap(new TreeMap<String, String>(), N);
//        System.out.println("CustomMap:");
//        populateMap(new CustomMap<String, String>(), N);
        System.out.println("CustomHashMap:");
        populateMap(new CustomHashMap<String, String>(N), N);

    }

    public static String generateString() {
        Random rn = new Random();
        return Integer.toHexString(rn.nextInt());
    }

    public static void populateSet(Set<String> inSet, int N) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < N; i++) {
            inSet.add(generateString());
        }

        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static void populateMap(Map<String, String> inMap, int N) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < N; i++) {
            inMap.put(generateString(), generateString());
        }

        System.out.println(System.currentTimeMillis() - startTime);

    }
}