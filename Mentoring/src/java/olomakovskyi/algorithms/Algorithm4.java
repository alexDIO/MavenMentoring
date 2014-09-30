package olomakovskyi.algorithms;

import java.util.*;

/**
 * Created by olomakovskyi : 8/5/14 5:25 PM
 */
public class Algorithm4 {

    static char[] inArray1;
    static char[] inArray2;
    static char[] inArray3;
    static Set<Integer> exceptions = new HashSet<Integer>();
    static List<String> solutions = new LinkedList<String>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String inString1 = scanner.next();
        String inString2 = scanner.next();
        String inString3 = scanner.next();

        scanner.close();

        inArray1 = inString1.toCharArray();
        inArray2 = inString2.toCharArray();
        inArray3 = inString3.toCharArray();

        Set<Character> uniqueSymbols = new HashSet<Character>();

        for (char elem : inArray1)
            uniqueSymbols.add(elem);

        for (char elem : inArray2)
            uniqueSymbols.add(elem);

        for (char elem : inArray3)
            uniqueSymbols.add(elem);

        if (inArray3.length < inArray1.length || inArray3.length < inArray2.length || uniqueSymbols.size() > 10) {
            System.out.println("Incorrect input data");
        } else {

            Character[] symbols = uniqueSymbols.toArray(new Character[uniqueSymbols.size()]);
            Integer[] numbers = new Integer[symbols.length];

            for (int i = 0; i < symbols.length; i++) {
                if (symbols[i].equals(inArray1[0]) || symbols[i].equals(inArray2[0]) || symbols[i].equals(inArray3[0])) {
                    exceptions.add(i);
                }
            }

            findSolution(symbols, numbers, 0);

            if (solutions.size() > 0) {
                for (String solution : solutions) {
                    System.out.println(solution);
                }
            } else {
                System.out.println("There are no solutions");
            }
        }
    }

    private static int getIntegerFromCharArray(char[] charArray, Character[] symbols, Integer[] numbers) {
        String tempString = "";

        for (char aCharArray : charArray) {
            for (int j = 0; j < symbols.length; j++) {
                if (aCharArray == symbols[j]) {
                    tempString = tempString + numbers[j];
                    break;
                }
            }
        }

        return Integer.parseInt(tempString);
    }

    private static void findSolution(Character[] inSymbols, Integer[] curSolution, int curSymbol) {
        if (curSymbol == inSymbols.length) {
            Set<Integer> uniqueChecker = new HashSet<Integer>(Arrays.asList(curSolution));
            if (curSolution.length == uniqueChecker.size()) {
                int outInt1 = getIntegerFromCharArray(inArray1, inSymbols, curSolution);
                int outInt2 = getIntegerFromCharArray(inArray2, inSymbols, curSolution);
                int outInt3 = getIntegerFromCharArray(inArray3, inSymbols, curSolution);
                if (outInt1 + outInt2 == outInt3) {
                    solutions.add("Result: " + outInt1 + " + " + outInt2 + " = " + outInt3);
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                if (!(i == 0 && exceptions.contains(curSymbol))) {
                    curSolution[curSymbol] = i;
                    findSolution(inSymbols, curSolution, curSymbol + 1);
                }
            }
        }
    }
}