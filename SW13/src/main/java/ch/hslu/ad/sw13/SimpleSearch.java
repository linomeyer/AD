package ch.hslu.ad.sw13;

public class SimpleSearch {
    public static int simpleSearch(String input, String pattern) {
        final int maxIndex = input.length() - pattern.length();

        for (int i = 0; i < maxIndex; i++) {
            boolean found = true;
            for (int j = 0; j < pattern.length(); j++) {
                if (input.charAt(i + j) != pattern.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found) return i;
        }

        return -1;
    }
}
