package jobsity.bowling.util;

public class IntegerUtils {

    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ignore) {}
        return 0;
    }

    public static int sumStrings(String val1, String val2) {
        return parseInt(val1) + parseInt(val2);
    }
}
