package olomakovskyi.collections.lists;

import java.util.*;

/**
 * Created by olomakovskyi on 8/21/2014.
 */

/*разобраться какой из листов быстрее для каждой из 8 операций и почему. Разобраться с итератором в листах
* дописать кастом лист через дженерик.*/

public class ListsPerformance {
    public static void main(String[] args) {
        //checkCustomList();
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.next());

        scanner.close();

        System.out.println("LinkedList:");
        listsPerformanceMeasure(new LinkedList<Double>(), N);
        System.out.println("ArrayList:");
        listsPerformanceMeasure(new ArrayList<Double>(), N);
        System.out.println("CustomList:");
        listsPerformanceMeasure(new CustomList<Double>(), N);

    }

    static void checkCustomList() {
        CustomList list = new CustomList();
        for (int i = 1; i < 99; i++) {
            list.add(i);
        }
        for (int i = 100; i < 115; i++) {
            list.add(4, i);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

    static void listsPerformanceMeasure(List<Double> inList, int N) {
        Random rn = new Random();
        long startTime = System.currentTimeMillis();
        long curTime;

        System.out.print("Adding N elements to end of the List: ");
        for (int i = 0; i < N; i++) {
            inList.add(rn.nextDouble());
        }
        curTime = System.currentTimeMillis();
        System.out.println(curTime - startTime);

        int halfOfList = N / 2;
        System.out.print("Adding N elements to middle of the List: ");
        for (int i = 0; i < N; i++) {
            inList.add(halfOfList + 1, rn.nextDouble());
        }
        startTime = curTime;
        curTime = System.currentTimeMillis();
        System.out.println(curTime - startTime);

        ListIterator<Double> iterator = inList.listIterator(halfOfList);
        if (iterator != null) {
            System.out.print("Adding N elements to middle of the List thru iterator: ");
            for (int i = 0; i < N; i++) {
                iterator.add(rn.nextDouble());
            }
            startTime = curTime;
            curTime = System.currentTimeMillis();
            System.out.println(curTime - startTime);
        }

        System.out.print("Adding N elements to begin of the List: ");
        for (int i = 0; i < N; i++) {
            inList.add(0, rn.nextDouble());
        }
        startTime = curTime;
        curTime = System.currentTimeMillis();
        System.out.println(curTime - startTime);
        System.out.print("Sorting of the List: ");
        if (inList instanceof CustomList) {
            ((CustomList) inList).sort();
        } else {
            Collections.sort(inList);
        }

        startTime = curTime;
        curTime = System.currentTimeMillis();
        System.out.println(curTime - startTime);
        System.out.print("Removing N elements from the begin List: ");
        for (int i = 0; i < N; i++) {
            inList.remove(0);
        }
        startTime = curTime;
        curTime = System.currentTimeMillis();
        System.out.println(curTime - startTime);

        halfOfList = inList.size() / 2;
        System.out.print("Removing N elements from the middle List: ");
        for (int i = 0; i < N; i++) {
            inList.remove(halfOfList);
        }
        startTime = curTime;
        curTime = System.currentTimeMillis();
        System.out.println(curTime - startTime);

        halfOfList = inList.size() / 2;
        iterator = inList.listIterator(halfOfList);
        if (iterator != null) {
            System.out.print("Removing N elements from middle of the List thru iterator: ");
            for (int i = 0; i < N; i++) {
                iterator.next(); //while using iterator without method .next() any method except .add() throws IllegalStateException
                iterator.remove();
            }
            startTime = curTime;
            curTime = System.currentTimeMillis();
            System.out.println(curTime - startTime);
        }

        System.out.print("Removing N elements from the end of the List: ");

        for (int i = N - 1; i >= 0; i--) {
            inList.remove(i);
        }
        startTime = curTime;
        curTime = System.currentTimeMillis();
        System.out.println(curTime - startTime);
    }
}