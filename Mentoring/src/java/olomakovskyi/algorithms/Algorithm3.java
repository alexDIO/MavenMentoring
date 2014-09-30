package olomakovskyi.algorithms;

import java.util.Scanner;

/**
 * Created by olomakovskyi : 8/5/14 5:25 PM
 */
public class Algorithm3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String inString1 = scanner.next();
        String inString2 = scanner.next();

        scanner.close();

        char[] array1 = inString1.toCharArray();
        char[] array2 = inString2.toCharArray();

        if (array1.length > array2.length) {

            for (int i = 0; i < array2.length; i++) {
                array1[i] = (char) (array1[i] + array2[i]);
            }

            for (char elem : array1)
                System.out.print(elem);
        } else {
            for (int i = 0; i < array1.length; i++) {
                array2[i] = (char) (array2[i] + array1[i]);
            }

            for (char elem : array2)
                System.out.print(elem);

        }

    }
}