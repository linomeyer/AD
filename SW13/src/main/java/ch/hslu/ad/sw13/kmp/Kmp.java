package ch.hslu.ad.sw13.kmp;

public class Kmp {
    public static int[] initNext(final String p) {
        final int m = p.length();
        final int[] next = new int[m];
        int i = 0;
        int j = -1;
        next[0] = -1;
        // special value! (-1 = no reference to a following state)
        do {
            if ((j == -1) || (p.charAt(i) == p.charAt(j))) {
                // (j == -1) must be first operand!
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        } while (i < (m - 1));
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
