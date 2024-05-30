package ch.hslu.ad.sw13.kmp;

public class Kmp {
    public static int[] initNext(final String pattern) {
        final int patternLength = pattern.length();
        final int[] next = new int[patternLength];
        int i = 0;
        int nextIndex = -1;
        next[0] = -1;
        do {
            if ((nextIndex == -1) || (pattern.charAt(i) == pattern.charAt(nextIndex))) {
                i++;
                nextIndex++;
                next[i] = nextIndex;
            } else {
                nextIndex = next[nextIndex];
            }
        } while (i < (patternLength - 1));
        return next;
    }

    public static int kmpSearch(final String inputString, final String pattern) {
        final int[] next = initNext(pattern);
        final int inputLength = inputString.length();
        final int patternLength = pattern.length();

        int inputIndex = 0;
        int patternIndex = 0;

        do {
            if (patternIndex == -1 || inputString.charAt(inputIndex) == pattern.charAt(patternIndex)) {
                inputIndex++;
                patternIndex++;
            } else {
                patternIndex = next[patternIndex];
            }
        } while (inputIndex < inputLength && patternIndex < patternLength);

        if (patternIndex == patternLength) {
            return inputIndex - patternLength;
        }
        return -1;
    }
}
