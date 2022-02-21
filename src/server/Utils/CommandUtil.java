package Utils;

import java.util.Arrays;

public class CommandUtil {
    public static String[] removeFirstElementOfStringArray(String[] array){
        return Arrays.copyOfRange(array, 1, array.length);
    }
}
