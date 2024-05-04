/*
 * Copyright 2024 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.ad.commons;

import java.security.SecureRandom;
import java.util.concurrent.RecursiveAction;

public final class IntRandomizerTask extends RecursiveAction {

    private static final int THRESHOLD = 50;
    private final int[] array;
    private final int min;
    private final int max;
    private SecureRandom secureRandom;

    /**
     * Erzeugt einen Array-Sortier Task.
     *
     * @param array Interger-Array.
     */
    public IntRandomizerTask(final int[] array) {
        this(array, 0, array.length);
    }

    private IntRandomizerTask(final int[] array, final int min, final int max) {
        secureRandom = new SecureRandom();
        this.array = array;
        this.min = min;
        this.max = max;
    }

    @Override
    protected void compute() {
        if (max - min < THRESHOLD) {
            for (int i = min; i < max; i++) {
                array[i] = Math.abs(secureRandom.nextInt());
            }
        } else {
            final int mid = min + (max - min) / 2;
            invokeAll(new IntRandomizerTask(array, min, mid), new IntRandomizerTask(array, mid, max));
        }
    }
}
