package storage;

import javax.servlet.jsp.tagext.TagSupport;
import java.util.Scanner;

/**
 * Created by olomakovskyi on 10/10/2014.
 */
public class WordsCounterTag extends TagSupport {
//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        while (true){
//            String string = scanner.nextLine();
//            System.out.println(countWords(string));
//        }
//    }


    public static int countWords(String inString){
        String pattern= "^[a-zA-Z0-9]*$";
        int count = 0;
        boolean isPreviousSymbolLetter = false;
        for (int i = 0; i < inString.length(); i++){
            String test = inString.substring(i, i+1);
            if (inString.substring(i, i+1).matches(pattern)){
                if (!isPreviousSymbolLetter) {
                    isPreviousSymbolLetter = true;
                    count++;
                }
            } else {
                isPreviousSymbolLetter = false;
            }
        }

        return count;
    }
}
