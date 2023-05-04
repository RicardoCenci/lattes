import java.util.Scanner;

public class IO {
    private static Scanner scannerInstance;

    public static void write(String str){
        System.out.println(str);
    }
    public static void write(String str, int tabIndex){
        System.out.println(Utils.padLeft(str, tabIndex * 4));
    }

    private static Scanner getScannerInstance(){
        if(IO.scannerInstance == null){
            IO.scannerInstance = new Scanner(System.in);
        }
        return IO.scannerInstance;
    }
    public static String read(){
        Scanner scanner = IO.getScannerInstance();
        return scanner.nextLine();
    }
}
