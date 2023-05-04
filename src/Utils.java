public class Utils {
    static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
            return true;

        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + (s.length() + n) + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + (s.length() + n) + "s", s);
    }

}
