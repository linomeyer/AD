package ch.hslu.ad.sw13.quicksearch;

import java.util.Arrays;

public class Quicksearch {

    private static final int UNICODE_RANGE = 65536;

    public static int quickSearch(final String input, final String pattern) {
        final int[] shift = generateShiftArray(pattern);
        return quickSearch(input, pattern, shift);
    }


    public static int quickSearchOptimalMismatch(String input, String pattern) {
        final int[] shift = generateShiftArray(pattern);
        return quickSearchOptimalMismatch(input, pattern, shift);
    }

    private static int quickSearch(String input, String pattern, int[] shift) {
        final int inputLength = input.length();
        final int patternLength = pattern.length();

        int inputIndex = 0;
        int patternIndex = 0;

        do {
            if (input.charAt(inputIndex + patternIndex) == pattern.charAt(patternIndex)) {
                patternIndex++;
            } else if (inputIndex + patternIndex < inputLength) { // check if jump would be out of bounds
                inputIndex += shift[input.charAt(inputIndex + patternLength)];
                patternIndex = 0;
            } else {
                break;
            }

        } while (patternIndex < patternLength && inputIndex + patternLength < inputLength);

        if (patternIndex == patternLength) {
            return inputIndex;
        } else {
            return -1;
        }
    }

    private static int quickSearchOptimalMismatch(String input, String pattern, int[] shift) {
        final int inputLength = input.length();
        final int patternLength = pattern.length();

        String optimalPattern = pattern;
        int inputIndex = 0;
        int patternIndex = 0;

        do {
            if (input.charAt(inputIndex + pattern.indexOf(optimalPattern.charAt(patternIndex))) == optimalPattern.charAt(patternIndex)) {
                patternIndex++;
            } else if (inputIndex + patternIndex < inputLength) { // check if jump would be out of bounds
                optimalPattern = swap(optimalPattern, pattern.charAt(patternIndex));
                inputIndex += shift[input.charAt(inputIndex + patternLength)];
                patternIndex = 0;
            } else {
                break;
            }

        } while (patternIndex < patternLength && inputIndex + patternLength < inputLength);

        if (patternIndex == patternLength) {
            return inputIndex;
        } else {
            return -1;
        }
    }

    private static String swap(String optimalPattern, char c) {
        int indexOfMismatch = optimalPattern.indexOf(c);
        char[] optimalPatternArray = optimalPattern.toCharArray();
        if (indexOfMismatch != 0) {
            char temp = optimalPatternArray[indexOfMismatch];
            optimalPatternArray[indexOfMismatch] = optimalPatternArray[indexOfMismatch - 1];
            optimalPatternArray[indexOfMismatch - 1] = temp;
        }

        return new String(optimalPatternArray);
    }

    private static int[] generateShiftArray(final String pattern) {
        final int[] shift = new int[UNICODE_RANGE];
        final int patternLength = pattern.length();

        Arrays.fill(shift, patternLength + 1);

        for (int i = 0; i < patternLength; i++) {
            shift[pattern.charAt(i)] = patternLength - i;
        }

        return shift;
    }
}
