package ch.hslu.ad.sw11.quicksort;

import java.util.concurrent.ForkJoinPool;

public class Quicksort {

    public static void quicksortSequential(final int[] a, int low, int high) {
        while (low < high) {
            // Check if array size on which we will be working is less than 10
            if (high - low < 20) {
                insertionSort(a, low, high);
                break;
            } else {
                int pivot = partition(a, low, high);

                if (pivot - low < pivot - high) {
                    quicksortSequential(a, low, pivot - 1);
                    low = pivot + 1;
                } else {
                    quicksortSequential(a, pivot + 1, high);
                    high = pivot - 1;
                }
            }
        }
    }

    public static void quickSortParallel(final int[] a) {
        try (final ForkJoinPool pool = new ForkJoinPool()) {
            final QuicksortTask sortTask = new QuicksortTask(a);
            pool.invoke(sortTask);
        }
    }

    private static void insertionSort(int[] a, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            for (int j = i - 1; j >= low; j--) {
                if (a[j] > a[j + 1]) {
                    exchange(a, j, j + 1);
                } else
                    break;
            }
        }
    }

    private static void exchange(final int[] a, final int firstIndex, final int secondIndex) {
        int tmp;
        tmp = a[firstIndex];
        a[firstIndex] = a[secondIndex];
        a[secondIndex] = tmp;
    }

    protected static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        int j = low;

        while (i <= high) {
            if (arr[i] > pivot)
                i++;
            else {
                exchange(arr, i, j);
                i++;
                j++;
            }
        }
        return j - 1;
    }
}
