package olomakovskyi.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by olomakovskyi : 8/5/14 12:09 PM
 */
public class Algorithm2 {
    public static void main(String[] args) {

        Random rn = new Random();
        Integer[] array = new Integer[20];

        for (int i = 0; i < array.length; i++) {
            array[i] = rn.nextInt(100);
            System.out.print(array[i] + " ");
        }

        List<Integer> firstList = new ArrayList<Integer>();
        List<Integer> secondList = new ArrayList<Integer>();

        firstList.add(array[0]);

        for (int i = 1; i < array.length; i++) {
            if (array[i] >= array[i - 1]) {
                firstList.add(array[i]);
            } else {
                if (firstList.size() >= secondList.size()) {
                    if (firstList.size() > secondList.size()) {
                        secondList.clear();
                        secondList.addAll(firstList);
                    } else {
                        int sumFirstList = sumListInt(firstList);
                        int sumSecondList = sumListInt(secondList);

                        if (sumFirstList > sumSecondList || (sumFirstList == sumSecondList && getMaxListIntElement(firstList) > getMaxListIntElement(secondList))) {
                            secondList.clear();
                            secondList.addAll(firstList);
                        }
                    }
                }
                firstList.clear();
                firstList.add(array[i]);
            }
        }

        System.out.println("");

        for (int i = 0; i < secondList.size(); i++) {
            System.out.print(secondList.get(i) + " ");
        }
    }

    static Integer sumListInt(List<Integer> list) {
        Integer sum = 0;

        for (int elem : list) {
            sum = sum + elem;
        }

        return sum;
    }

    static Integer getMaxListIntElement(List<Integer> list) {
        Integer max;
        try {
            max = list.get(0);
        } catch (NullPointerException e) {
            throw new NullPointerException("List doesn't contain any elements");
        }

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max)
                max = list.get(i);
        }

        return max;
    }
}