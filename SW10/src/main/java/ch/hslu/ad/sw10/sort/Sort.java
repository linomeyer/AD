package ch.hslu.ad.sw10.sort;

public class Sort {
    private Sort() {
    }

    public static void quickSortB(char[] a) {
        quickSortB(a, 0, a.length - 1);
    }

    public static void quickSortC(char[] a) {
        quickSortC(a, 0, a.length - 1);
    }

    private static void quickSortB(final char[] a, final int left, final int right) {
        int up = left; // linke Grenze
        int down = right - 1; // rechte Grenze (ohne Trennelement)
        char t = a[right]; // rechtes Element als Trennelement
        boolean allChecked = false;
        do {
            while (a[up] < t) {
                up++; // suche grösseres (>=) Element von links an
            }
            while ((a[down] >= t) && (down > up)) {
                down--; // suche echt kleineres (<) Element von rechts an
            }
            if (down > up) { // solange keine Überschneidung
                exchange(a, up, down);
                up++;
                down--; // linke und rechte Grenze verschieben
            } else {
                allChecked = true; // Austauschen beendet
            }
        } while (!allChecked);
        exchange(a, up, right); // Trennelement an endgültige Position (a[up])
        if (left < (up - 1)) quickSortB(a, left, (up - 1)); // linke Hälfte
        if ((up + 1) < right) quickSortB(a, (up + 1), right);
    }

    private static void quickSortC(final char[] a, final int left, final int right) {
        int up = left; // linke Grenze
        int down = right - 1; // rechte Grenze (ohne Trennelement)
        char t = a[right]; // rechtes Element als Trennelement
        boolean allChecked = false;
        do {
            while (a[up] < t) {
                up++; // suche grösser gleiches (>=) Element von links an
            }
            while ((a[down] > t) && (down > up)) { // a[down] > instead of >= is the difference
                down--; // suche kleiner gleiches (<=) Element von rechts an
            }
            if (down > up) { // solange keine Überschneidung
                exchange(a, up, down);
                up++;
                down--; // linke und rechte Grenze verschieben
            } else {
                allChecked = true; // Austauschen beendet
            }
        } while (!allChecked);
        exchange(a, up, right); // Trennelement an endgültige Position (a[up])
        if (left < (up - 1)) quickSortC(a, left, (up - 1)); // linke Hälfte
        if ((up + 1) < right) quickSortC(a, (up + 1), right);
    }

    public static void quickInsertionSort(final char[] a, final int left, final int right) {
        if (right - left > 20) {
            int up = left; // linke Grenze
            int down = right - 1; // rechte Grenze (ohne Trennelement)
            char t = a[right]; // rechtes Element als Trennelement
            boolean allChecked = false;
            do {
                while (a[up] < t) {
                    up++; // suche grösser gleiches (>=) Element von links an
                }
                while ((a[down] > t) && (down > up)) {
                    down--; // suche kleiner gleiches (<=) Element von rechts an
                }
                if (down > up) { // solange keine Überschneidung
                    exchange(a, up, down);
                    up++;
                    down--; // linke und rechte Grenze verschieben
                } else {
                    allChecked = true; // Austauschen beendet
                }
            } while (!allChecked);
            exchange(a, up, right); // Trennelement an endgültige Position (a[up])
            if (left < (up - 1)) quickInsertionSort(a, left, (up - 1)); // linke Hälfte
            if ((up + 1) < right) quickInsertionSort(a, (up + 1), right);
        } else {
            insertionSort(a, left, right);
        }
    }

    private static void insertionSort(char[] array, int left, int right) {
        char element;
        int cursor;

        for (int i = left; i < right; i++) {
            element = array[i];
            cursor = i;
            while ((cursor > 0 && cursor >= left) && array[cursor - 1] < element) {
                array[cursor] = array[cursor - 1];
                cursor--;
            }
        }
    }

    private static void exchange(final char[] a, final int firstIndex, final int secondIndex) {
        char tmp;
        tmp = a[firstIndex];
        a[firstIndex] = a[secondIndex];
        a[secondIndex] = tmp;
    }
}