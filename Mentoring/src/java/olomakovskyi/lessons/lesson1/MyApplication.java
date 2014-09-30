package olomakovskyi.lessons.lesson1;

/**
 * Created by olomakovskyi : 8/7/14 6:35 PM
 */
public class MyApplication {
    public static void main(String[] args) {
        String s = "";
        long before = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            s += "a";
            System.out.println(String.format("iteration : %d, result : %s", i, s)); //String.format is used when we need to concatenate some definite count of strings
        }

        System.out.println(System.currentTimeMillis() - before);

        StringBuilder builder = new StringBuilder(); // the faster way to concatenate indefinite amount of strings but shouldn't be used in multithreading
        before = System.currentTimeMillis();
        for (int i = 0; i < 3000000; i++) {
            builder.append("a");
        }
        System.out.println(System.currentTimeMillis() - before);

        System.out.println(s.equals(builder.toString()));

        StringBuffer buffer = new StringBuffer(); // safe way to concatenate indefinite amount of strings in multithreading
        before = System.currentTimeMillis();
        for (int i = 0; i < 3000000; i++) {
            buffer.append("a");
        }
        System.out.println(System.currentTimeMillis() - before);

        System.out.println(s.equals(buffer.toString()));

    }

}