package ch.hslu.ad.sw13.quicksearch;

import java.util.Arrays;

public class Quicksearch {

    public static final int ASCII_RANGE = 256;

    public static int quickSearch(final String input, final String pattern) {
        final int[] shift = generateShiftArray(pattern);

        quickSearch(input, pattern, shift);
        return -1;
    }

    private static void quickSearch(String input, String pattern, int[] shift) {
        final int inputLength = input.length();
        final int patternLength = pattern.length();

        int inputIndex = 0;
        int patternIndex = 0;

        do {
            if (input.charAt(inputIndex + patternIndex) == pattern.charAt(patternIndex)) {
                patternIndex++;
            } else if (inputIndex + patternIndex < inputLength) { // check if jump would be out of bounds
                inputIndex += shift[input.charAt(inputIndex + patternIndex)];
                patternIndex = 0;
            } else { // if it would be out of bounds
                break;
            }

        } while (patternIndex < patternLength && inputIndex + patternLength < inputLength);
    }

    private static int[] generateShiftArray(final String pattern) {
        final int[] shift = new int[ASCII_RANGE];
        final int patternLength = pattern.length();

        Arrays.fill(shift, patternLength + 1);

        for (int i = 0; i < patternLength; i++) {
            shift[pattern.charAt(i)] = patternLength - i;
        }

        return shift;
    }
}
