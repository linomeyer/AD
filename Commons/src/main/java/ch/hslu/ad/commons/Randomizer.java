package ch.hslu.ad.commons;

import java.security.SecureRandom;
import java.util.concurrent.ForkJoinPool;

public class Randomizer {
    private static final int BITS_16 = 65_536;

    private Randomizer() {
    }

    public static char[] randomCharArray(final int length) {
        SecureRandom secureRandom = new SecureRandom();
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = (char) (secureRandom.nextInt(length) % BITS_16);
        }
        return chars;
    }

    public static int[] randomIntArray(final int length) {
        try (final ForkJoinPool pool = new ForkJoinPool()) {
            int[] ints = new int[length];
            IntRandomizerTask intRandomizerTask = new IntRandomizerTask(ints);
            pool.invoke(intRandomizerTask);
            return ints;
        }
    }
}
