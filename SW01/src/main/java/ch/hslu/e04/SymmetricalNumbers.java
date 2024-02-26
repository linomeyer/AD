package ch.hslu.e04;

public class SymmetricalNumbers {
    public static boolean isSymetricalNumber(int number) {
        String strNumber = String.valueOf(number);
        String strNumberReverse = new StringBuilder(strNumber).reverse().toString();

        return strNumber.equals(strNumberReverse);
    }

    public static int nextSymmetricalNumber(int number) {
        while (true) {
            number++;
            if (isSymetricalNumber(number)) {
                return number;
            }
        }
    }
}
