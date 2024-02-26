package ch.hslu.e04;

import java.util.Iterator;
import java.util.LinkedList;

public class SymmetricalNumbers {
    public static boolean isSymetricalNumber(int number) {
        String strNumber = String.valueOf(number);
        String strNumberReverse = new StringBuilder(strNumber).reverse().toString();

        return strNumber.equals(strNumberReverse);
    }

    public static int nextSymmetricalNumber(int number) {
        LinkedList<Object> objects = new LinkedList<>();
        Iterator<Object> iterator = objects.iterator();
        while (true) {
            number++;
            if (isSymetricalNumber(number)) {
                return number;
            }
        }
    }
}
