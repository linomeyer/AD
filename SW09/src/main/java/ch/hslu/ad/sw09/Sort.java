package ch.hslu.ad.sw09;

public class Sort {
    private Sort() {
    }

    public static <T extends Comparable<? super T>> T[] insertionSort(final T[] array) {
        T element;
        int cursor;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            cursor = i;
            while (cursor > 0 && array[cursor - 1].compareTo(element) > 0) {
                array[cursor] = array[cursor - 1];
                cursor--;
            }
        }
        return array;
    }

    public static <T extends Comparable<? super T>> T[] binaryInsertionSort(final T[] array) {
        T element;
        int cursor;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            cursor = i - 1;

            int insertionLocation = binarySearch(array, element, cursor);

            // scooch all element that are larger than the new element one to the right
            while (cursor >= insertionLocation) {
                array[cursor + 1] = array[cursor];
                cursor--;
            }

        }
        return array;
    }

    public static <T extends Comparable<? super T>> T[] selectionSort(final T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int indexOfSmallest = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[indexOfSmallest]) < 0) {
                    indexOfSmallest = j;
                }
            }
            T smallest = array[indexOfSmallest];
            array[indexOfSmallest] = array[i];
            array[i] = smallest;

        }
        return array;
    }

    public static <T extends Comparable<? super T>> T[] bubbleSort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return array;
    }

    // how is this shit so fast wtf!???
    public static <T extends Comparable<? super T>> T[] shellSort(T[] array) {
        // start at a gap of half the array size and half the gap each iteration
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                T elementToInsert = array[i];
                int j = i;
                while (j >= gap && array[j - gap].compareTo(elementToInsert) > 0) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = elementToInsert;
            }
        }
        return array;
    }

    private static <T extends Comparable<? super T>> int
    binarySearch(final T[] array, final T element, final int endOfSortedPart) {
        int low = 0;
        int high = endOfSortedPart;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (element.compareTo(array[mid]) == 0) {
                // if element equal to existing element, insert one after
                return mid + 1;
            }
            if (element.compareTo(array[mid]) > 0) {
                // if element is larger, go to higher half of array
                low = mid + 1;
            } else {
                // if element is smaller, go to lower half of array
                high = mid - 1;
            }
        }
        return low;
    }
}