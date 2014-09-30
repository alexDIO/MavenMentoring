package olomakovskyi.algorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by olomakovskyi : 8/5/14 12:09 PM
 */
public class Algorithm1 {
    public static void main(String[] args) {

        Random rn = new Random();
        int[] array = new int[20];

        for (int i = 0; i < array.length; i++)
            array[i] = rn.nextInt(100);


        int firstMax = array[0];
        int secondMax = -1;
        int thirdMax = -1;

        for (int i = 1; i < array.length; i++) {
            if (array[i] < firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = array[i];
            } else if (array[i] < secondMax/* && array[i] != firstMax*/) {
                thirdMax = secondMax;
                secondMax = array[i];
            } else if (array[i] < thirdMax/* && array[i] != firstMax && array[i] != secondMax*/) {
                thirdMax = array[i];
            }

        }

        System.out.println(thirdMax);

        Arrays.sort(array);

        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");


    }
}