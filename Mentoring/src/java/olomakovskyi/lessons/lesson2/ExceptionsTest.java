package olomakovskyi.lessons.lesson2;

/**
 * Created by olomakovskyi on 8/29/2014.
 */
public class ExceptionsTest {

    public static void main(String[] args) throws InterruptedException {

        try{
           runtimeExceptionThrowing();
        } catch (RuntimeException e){
            System.out.println("Runtime exception was cached");
        }

        try{
            errorThrowing();
        } catch (Error error){
            System.out.println("Error was cached");
        }

        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();

        do{
            System.out.println(String.format("Max memory: %s, free memory: %s, total memory: %s", runtime.maxMemory(), freeMemory, runtime.totalMemory()));
            System.gc();
            Thread.sleep(1000);
            freeMemory = runtime.freeMemory();
        } while (freeMemory < 1000000);

        try{
            customExceptionThrowing();
        } catch (CustomException e){
            System.out.println("Custom exception was cached");
        }

        try {
            customExceptionCaching();
        } catch (Throwable throwable){
            System.out.println("Throwable was caught");
        }

        try{
            exceptionFinally();
        } catch (Exception e){
            System.out.println("Exception was cached");
            e.printStackTrace();
        }
    }

    public static void runtimeExceptionThrowing() {
        int runtime = 2 / 0;
    }

    public static void errorThrowing(){
        String s = " ";
        while (true){
            s = s + s;
        }
    }

    public static void customExceptionThrowing() throws CustomException {
        throw new  CustomException();
    }

    public static void customExceptionCaching(){
        try {
            throw  new CustomException();
        } catch (CustomException e){
            System.out.println(e.toString());

            return;
        } finally {
            System.out.println("finally is called");
        }

    }

    public static void exceptionFinally(){
        try {
            String a = null;
            a.toUpperCase();
        } finally {
            System.out.println("throwing another exception");
            int i = 2 / 0;
        }
    }

    public static void finallyNeverRun(){
        try {
            String a = null;
            System.exit(0);
            a.toUpperCase();
        } finally {
            System.out.println("throwing another exception");
            int i = 2 / 0;
        }

    }

}
