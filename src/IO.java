import java.util.Iterator;
import java.util.Scanner;

public class IO {
    private static Scanner scannerInstance;

    public static void write(String str){
        System.out.println(str);
    }
    public static void write(int str){
        System.out.println(str);
    }

    private static Scanner getScannerInstance(){
        if(IO.scannerInstance == null){
            IO.scannerInstance = new Scanner(System.in);
        }
        return IO.scannerInstance;
    }
    private static void clear(){
        System.out.print("\033\143");
    }
    public static String read(){
        Scanner scanner = IO.getScannerInstance();
        return scanner.nextLine();
    }
}
